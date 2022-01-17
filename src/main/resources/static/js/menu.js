$(document).ready(function() {
if(localStorage.getItem("role")==="" || localStorage.getItem("role")===undefined){
    window.location.href = "/accessDenied.html";
}
    $('#hello').append("<b>Привет, "+ localStorage.getItem("username")+"</b>");
    if (localStorage.getItem("role")==="ROLE_ADMIN"){
        $("#bth-maker").show();
        $("#bth-department").show();
        $("#bth-vacancy").show();
        $("#bth-engineer").show();
        $("#bth-candidate").show();
        $("#bth-candidateVacancy").show();
        $("#bth-company").show();



    }
    if (localStorage.getItem("role")==="ROLE_MAKER"){
        $("#bth-vacancy").show();
        $("#bth-department").show();
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
    $("#bth-maker").click(function (event) {
        event.preventDefault();
        window.location.href = "/recruiters.html";

    });
    $("#bth-candidate").click(function (event) {
        event.preventDefault();
        window.location.href = "/candidates.html";
    });
    $("#bth-candidateVacancy").click(function (event) {
        event.preventDefault();
        window.location.href = "/candidateVacancies.html";
    });
    $("#bth-company").click(function (event) {
        event.preventDefault();
        window.location.href = "/companies.html";
    });
});