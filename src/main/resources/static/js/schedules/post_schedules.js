$(document).ready(function() {

    $.ajax({
        type: "GET",
        url: "/categories?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, category) => {
                var option = "<option value = " + category.id + ">" + category.name + "</option>";
                $("#category").append(option);
            })
        }
    });

    $.ajax({
        type: "GET",
        url: "/recruiters?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, recruiter) => {
                var option = "<option value = " + recruiter.id + ">" + recruiter.name + " " + recruiter.surname + "</option>";
                $("#recruiter").append(option);
            })
        }
    });

    $.ajax({
        type: "GET",
        url: "/engineers?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, engineer) => {
                var option = "<option value = " + engineer.id + ">" + engineer.name + " " + engineer.surname + " " + engineer.position + "</option>";
                $("#engineer").append(option);
            })
        }
    });

    $.ajax({
        type: "GET",
        url: "/candidatevacancies?token=" + localStorage.getItem("token"),
        success: function (response) {
            $.each(response, (i, candidate_vacancy) => {
                var option = "<option value = " + candidate_vacancy.id + ">" + candidate_vacancy.candidate + " " + candidate_vacancy.vacancy + "</option>";
                $("#candidate_vacancy").append(option);
            })
        }
    });

    $("#add_new").submit(function(evt) {
        evt.preventDefault();
        let formData = {
            date : $("#input_date").val().split(" ")[0],
            time : $("#input_date").val().split(" ")[1],
            categoryId : parseInt($("#category").val()),
            recruiterId : parseInt($("#recruiter").val()),
            engineerId : parseInt($("#engineer").val()),
            candidateVacancyId : parseInt($("#candidate_vacancy").val())
        };

        $.ajax({
            url: '/schedules?token='+localStorage.getItem("token"),
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
        } else if (pathname === "/schedules.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});