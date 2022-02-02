$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/candidates?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, candidate) => {
                var option = "<option value = " + candidate.id + ">" + candidate.name + " " + candidate.surname + ", " + candidate.company + ", " + candidate.email + "</option>";
                $("#candidate").append(option);
            })
        }
    });
    $.ajax({
        type: "GET",
        url: "/vacancies?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, vacancy) => {
                var option = "<option value = " + vacancy.id + ">" + vacancy.name + "</option>";
                $("#vacancy").append(option);
            })
        }
    });
    $("#add_new").submit(function(evt) {
        evt.preventDefault();
        let formData = {
            candidateId:  parseInt($("#candidate").val()),
            vacancyId:  parseInt($("#vacancy").val())
        };

        $.ajax({
            url: '/candidatevacancies?token='+localStorage.getItem("token"),
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