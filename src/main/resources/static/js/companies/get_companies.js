$(document).ready(function(){
    if(localStorage.getItem("role")==="ROLE_ADMIN") {
        $("#add").show()
    }
    else{window.location.href = "/accessDenied.html";
    }
    $('#hello').append("<b>Привет, "+ localStorage.getItem("username")+"</b>");
    (function(){
        $.ajax({
            type : "GET",
            url : "/companies?token="+localStorage.getItem("token"),
            success: function(response){
                $.each(response, (i, company) => {
                    let deleteButton;
                    let get_More_Info_Btn;
                    deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + company.id + '\"' +
                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                        '>&times</button>';

                    get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + company.id + '\"' +
                        ' type="button" class="btn btn-info btn_id">' +
                        company.id +
                        '</button>';
                    let tr_id = 'tr_' + company.id;
                    let row = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_name\">' + company.name + '</td>';
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
        if (pathname === "/companies.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});