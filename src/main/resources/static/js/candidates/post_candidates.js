$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/companies?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, company) => {
                var option = "<option value = " + company.id + ">" + company.name + "</option>";
                $("#company").append(option);
            })
        }
    });
    $("#add_new").submit(function(evt) {
        evt.preventDefault();
        let formData = {
            name : $("#name").val(),
            surname: $("#surname").val(),
            email: $("#email").val(),
            companyId:  parseInt($("#company").val())
        };

        $.ajax({
            url: '/candidates?token='+localStorage.getItem("token"),
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
        } else if (pathname === "/candidates.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});