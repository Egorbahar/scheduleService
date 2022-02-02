$(document).ready(function () {
    if (localStorage.getItem("role") === "ROLE_ADMIN" || localStorage.getItem("role") === "ROLE_RECRUITER") {
        $("#add").show();
        $("#assign").show();
    } else {
        window.location.href = "/accessDenied.html";
    }
    $('#hello').append("<b>Привет, " + localStorage.getItem("username") + "</b>");
    (function () {
        $.ajax({
            type: "GET",
            url: "/candidates?token=" + localStorage.getItem("token"),
            success: function (response) {
                $.each(response, (i, candidate) => {
                    let deleteButton;
                    let get_More_Info_Btn;
                    deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + candidate.id + '\"' +
                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                        '>&times</button>';

                    get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + candidate.id + '\"' +
                        ' type="button" class="btn btn-info btn_id">' +
                        candidate.id +
                        '</button>';
                    let tr_id = 'tr_' + candidate.id;
                    let row = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_name\">' + candidate.name + '</td>' +
                        '<td class=\"td_surname\">' + candidate.surname + '</td>' +
                        '<td class=\"td_email\">' + candidate.email + '</td>' +
                        '<td class=\"td_company\">' + candidate.company + '</td>'+
                        '<td class=\"td_vacancyId\">' + candidate.vacancyNameList + '</td>';
                    row = row + '<td>' + deleteButton + '</td>';

                    row = row +
                        '</tr>';
                    $('#customerTable tbody').append(row);
                });
            },
            error: function (e, textStatus, errorThrown) {
                if (e.status === 403)
                    window.location.href = "/accessDenied.html";
                else {
                    console.log("ERROR : ", textStatus);
                    alert(e["responseText"]);
                }
            }
        });
    })();

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/candidates.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});