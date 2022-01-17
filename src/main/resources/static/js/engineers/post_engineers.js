$(document).ready(function() {

    $.ajax({
        type: "GET",
        url: "/departments?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, department) => {
                var option = "<option value = " + department.id + ">" + department.name + "</option>";
                $("#department").append(option);
            })
        }
    });

    $.ajax({
        type: "GET",
        url: "/positions?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, position) => {
                var option = "<option value = " + position.name + ">" + position.value + "</option>";
                $("#position").append(option);
            })
        }
    });

    $("#add_new").submit(function(evt) {
        evt.preventDefault();
        let formData = {
            name : $("#name").val(),
            surname : $("#surname").val(),
            username : $("#username").val(),
            password : $("#password").val(),
            position: $("#position").val(),
            email: $("#email").val(),
            department: $("#department").val()
        };

        $.ajax({
            url: '/engineers?token='+localStorage.getItem("token"),
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            success: function (response) {
                $("div.modal-body")
                    .text("Успешно сохранено");

                $("button.btn.btn-secondary").text("Close");
            },
            error: function (error) {
                alert(error["responseText"])
            }
        });
    });


    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname === "/engineers.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});