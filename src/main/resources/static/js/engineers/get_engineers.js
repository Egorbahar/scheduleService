$(document).ready(function () {
    if (localStorage.getItem("role") === "ROLE_ADMIN") {
        $("#add").show()
    } else {
        window.location.href = "/accessDenied.html";
    }
    $('#hello').append("<b>Привет, " + localStorage.getItem("username") + "</b>");
    (function () {
        $.ajax({
            type: "GET",
            url: "/engineers?token=" + localStorage.getItem("token"),
            success: function (response) {
                $.each(response, (i, engineer) => {
                    let deleteButton;
                    let get_More_Info_Btn;
                    deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + engineer.id + '\"' +
                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                        '>&times</button>';

                    get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + engineer.id + '\"' +
                        ' type="button" class="btn btn-info btn_id">' +
                        engineer.id +
                        '</button>';
                    let tr_id = 'tr_' + engineer.id;
                    let row = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_name\">' + engineer.name + '</td>' +
                        '<td class=\"td_surname\">' + engineer.surname + '</td>' +
                        '<td class=\"td_username\">' + engineer.username + '</td>' +
                        '<td class=\"td_email\">' + engineer.email + '</td>' +
                        '<td class=\"td_position\">' + engineer.position + '</td>'+
                        '<td class=\"td_department\">' + engineer.department + '</td>';
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
        if (pathname === "/engineers.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});