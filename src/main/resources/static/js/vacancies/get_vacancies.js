$(document).ready(function(){
    if(localStorage.getItem("role")==="ROLE_ADMIN" || localStorage.getItem("role")==="ROLE_ENGINEER") {
        $("#add").show()
    }
    else if (localStorage.getItem("role")!=="ROLE_RECRUITER")
    {window.location.href = "/accessDenied.html";
    }

    $('#hello').append("<b>Привет, "+ localStorage.getItem("username")+"</b>");
    (function(){
        $.ajax({
            type : "GET",
            url : "/vacancies?token="+localStorage.getItem("token"),
            success: function(response){
                $.each(response, (i, vacancy) => {
                    let deleteButton;
                    let get_More_Info_Btn;
                    deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + vacancy.id + '\"' +
                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                        '>&times</button>';

                    get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + vacancy.id + '\"' +
                        ' type="button" class="btn btn-info btn_id">' +
                        vacancy.id +
                        '</button>';
                    let tr_id = 'tr_' + vacancy.id;
                    let row = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_name\">' + vacancy.name + '</td>' +
                        '<td class=\"td_date\">' + vacancy.date + '</td>' +
                        '<td class=\"td_position\">' + vacancy.position + '</td>' +
                        '<td class=\"td_department\">' + vacancy.department + '</td>';
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
        if (pathname === "/vacancies.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});