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
            url : "/recruiters?token="+localStorage.getItem("token"),
            success: function(response){
                $.each(response, (i, recruiter) => {
                    let deleteButton;
                    let get_More_Info_Btn;
                        deleteButton = '<button ' +
                            'id=' +
                            '\"' + 'btn_delete_' + recruiter.id + '\"' +
                            ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                            '>&times</button>';

                    get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + recruiter.id + '\"' +
                        ' type="button" class="btn btn-info btn_id">' +
                        recruiter.id +
                        '</button>';
                    let tr_id = 'tr_' + recruiter.id;
                    let row = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_name\">' + recruiter.name + '</td>' +
                        '<td class=\"td_surname\">' + recruiter.surname + '</td>' +
                        '<td class=\"td_username\">' + recruiter.username + '</td>' +
                        '<td class=\"td_email\">' + recruiter.email + '</td>' +
                        '<td class=\"td_phone_number\">' + recruiter.phoneNumber + '</td>';
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
        if (pathname === "/recruiters.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});