$(document).ready(function() {
if(localStorage.getItem("role")==="" || localStorage.getItem("role")===undefined){
    window.location.href = "/accessDenied.html";
}
    $('#hello').append("<b>Привет, "+ localStorage.getItem("username")+"</b>");
    if (localStorage.getItem("role")==="ROLE_ADMIN"){
        $("#bth-maker").show();
        $("#bth-tutor").show();
        $("#bth-studcourse").show();
        $("#bth-department").show();
    }
    if (localStorage.getItem("role")==="ROLE_MAKER"){
        $("#bth-studcourse").show();
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

    $("#bth-tutor").click(function (event) {
        event.preventDefault();
        window.location.href = "/tutors.html";

    });
    $("#bth-studcourse").click(function (event) {
        event.preventDefault();
        window.location.href = "/studentcourses.html";

    });
    $("#bth-maker").click(function (event) {
        event.preventDefault();
        window.location.href = "/recruiters.html";

    });
    $("#bth-event").click(function (event) {
        event.preventDefault();
        window.location.href = "/events.html";

    });
});