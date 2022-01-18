$(document).ready(function() {
if(localStorage.getItem("role")==="" || localStorage.getItem("role")===undefined){
    window.location.href = "/accessDenied.html";
}

    $("#bth-recruiter").hide();
    $("#bth-category").hide();
    $("#bth-department").hide();
    $("#bth-vacancy").hide();
    $("#bth-engineer").hide();
    $("#bth-schedule").hide();
    $("#bth-candidate").hide();
    $("#bth-company").hide();

    $('#hello').append("<b>Привет, "+ localStorage.getItem("username")+"</b>");
    if (localStorage.getItem("role")==="ROLE_ADMIN"){
        $("#bth-recruiter").show();
        $("#bth-department").show();
        $("#bth-vacancy").show();
        $("#bth-engineer").show();
        $("#bth-schedule").show();
        $("#bth-candidate").show();
        $("#bth-company").show();
        $("#bth-category").show();
    }
    if (localStorage.getItem("role")==="ROLE_RECRUITER"){
        $("#bth-candidate").show();
        $("#bth-vacancy").show();
        $("#bth-company").show();
        $("#bth-schedule").show();
        $("#bth-category").show();
    }

    if (localStorage.getItem("role")==="ROLE_ENGINEER"){
        $("#bth-vacancy").show();
        $("#bth-schedule").show();
    }

    $("#bth-course").click(function (event) {
        event.preventDefault();
        window.location.href = "/courses.html";

    });

    $("#bth-department").click(function (event) {
        event.preventDefault();
        window.location.href = "/departments.html";

    });

    $("#bth-engineer").click(function (event) {
        event.preventDefault();
        window.location.href = "/engineers.html";

    });
    $("#bth-vacancy").click(function (event) {
        event.preventDefault();
        window.location.href = "/vacancies.html";

    });
    $("#bth-recruiter").click(function (event) {
        event.preventDefault();
        window.location.href = "/recruiters.html";

    });
    $("#bth-candidate").click(function (event) {
        event.preventDefault();
        window.location.href = "/candidates.html";
    });
    $("#bth-schedule").click(function (event) {
        event.preventDefault();
        window.location.href = "/schedules.html";
    });
    $("#bth-company").click(function (event) {
        event.preventDefault();
        window.location.href = "/companies.html";
    });
    $("#bth-category").click(function (event) {
        event.preventDefault();
        window.location.href = "/categories.html";
    });
});