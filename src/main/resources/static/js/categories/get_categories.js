$(document).ready(function(){
    if(localStorage.getItem("role")==="ROLE_ADMIN") {
        $("#add").show()
    }
    else if (localStorage.getItem("role")!=="ROLE_RECRUITER") {window.location.href = "/accessDenied.html";
    }
    $('#hello').append("<b>Привет, "+ localStorage.getItem("username")+"</b>");
    (function(){
        $.ajax({
            type : "GET",
            url : "/categories?token="+localStorage.getItem("token"),
            success: function(response){
                $.each(response, (i, category) => {
                    let deleteButton;
                    let get_More_Info_Btn;
                    deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + category.id + '\"' +
                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                        '>&times</button>';

                    get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + category.id + '\"' +
                        ' type="button" class="btn btn-info btn_id">' +
                        category.id +
                        '</button>';
                    let tr_id = 'tr_' + category.id;
                    let row = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_name\">' + category.name + '</td>' +
                        '<td class=\"td_duration\">' + category.duration + '</td>';
                    row = row + '<td>' + deleteButton + '</td>';

                    row = row +
                        '</tr>';
                    $('#customerTable tbody').append(row);
                });
            },
            error : function(e, textStatus, errorThrown) {
                if(e.status===403)
                    window.location.href = "/accessDenied.html";
                else{
                    console.log("ERROR : ", textStatus);
                    alert(e["responseText"]);
                }
            }
        });
    })();

    (function(){
        let pathname = window.location.pathname;
        if (pathname === "/categories.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});