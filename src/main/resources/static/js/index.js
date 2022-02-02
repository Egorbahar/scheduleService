$(document).ready(function () {
    $("#login-form").submit(function (event) {

        event.preventDefault();
        localStorage.setItem("token", "")
        localStorage.setItem("role", "")
        localStorage.setItem("userId", "")
        localStorage.setItem("username", "")

        var loginForm = {}
        loginForm["username"] = $("#username").val();
        loginForm["password"] = $("#password").val();
        $("#btn-login").prop("disabled", true);


        let formData = {
            username : $("#username").val(),
            password: $("#password").val()
        };

        $.ajax({
            type : "POST",
            url: "/authentication/auth",
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            success: function (data) {
                localStorage.setItem("token", data["token"])
                localStorage.setItem("role", data["role"])
                localStorage.setItem("userId", data["userId"])
                localStorage.setItem("username", $("#username").val())

                console.log("SUCCESS : ", data);
                $("#btn-login").prop("disabled", false);
                window.location.href = "/menu.html";
            },
            error: function (e) {
                $('#feedback').html("Неверный логин или пароль");

                console.log("ERROR : ", e);
                $("#btn-login").prop("disabled", false);

            }
        });

    });

    $("#btn-reg").click(function (event) {
        event.preventDefault();
        window.location.href = "/registration.html";

    });

});