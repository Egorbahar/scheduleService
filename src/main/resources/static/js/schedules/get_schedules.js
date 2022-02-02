$(document).ready(function () {
    let url;
    if (localStorage.getItem("role") === "ROLE_ADMIN") {
        $("#add").show()
        url = "/schedules?token=" + localStorage.getItem("token");
    } else
    if (localStorage.getItem("role") === "ROLE_RECRUITER") {
        $("#add").show()
        url = "/schedules?token=" + localStorage.getItem("token") + "&role=recruiter&userId=" + localStorage.getItem("userId");
    } else
    if (localStorage.getItem("role") === "ROLE_ENGINEER") {
        url = "/schedules?token=" + localStorage.getItem("token") + "&role=engineer&userId="+ localStorage.getItem("userId");
    }
    else {window.location.href = "/accessDenied.html";}

    $('#hello').append("<b>Привет, " + localStorage.getItem("username") + "</b>");

    (function () {
        $.ajax({
            type: "GET",
            url: url,
            success: function (response) {
                $.each(response, (i, schedule) => {
                    let deleteButton;
                    let get_More_Info_Btn;
                    deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + schedule.id + '\"' +
                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                        '>&times</button>';

                    get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + schedule.id + '\"' +
                        ' type="button" class="btn btn-info btn_id">' +
                        schedule.id +
                        '</button>';
                    let tr_id = 'tr_' + schedule.id;
                    let row = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_vacancy\">' + schedule.vacancy + '</td>' +
                        '<td class=\"td_start_date\">' + schedule.startTime + '</td>' +
                        '<td class=\"td_end_date\">' + schedule.endTime + '</td>' +
                        '<td class=\"td_candidate\">' + schedule.candidateName + " " +schedule.candidateSurname+ '</td>' +
                        '<td class=\"td_category\">' + schedule.category + '</td>';
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
        if (pathname === "/schedules.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});