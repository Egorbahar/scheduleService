function signOut(){
  $.ajax({
    type : "GET",
    url : "/authentication/signout?token="+localStorage.getItem("token"),
    success: function(response){
      localStorage.setItem("token", "")
      localStorage.setItem("role", "")
      localStorage.setItem("userId", "")
      $("#bth-recruiter").hide();
      $("#bth-department").hide();
      $("#bth-vacancy").hide();
      $("#bth-engineer").hide();
      $("#bth-schedule").hide();
      $("#bth-candidate").hide();
      $("#bth-company").hide();
    },
    error : function(e) {
      alert("ERROR: ", e);
      console.log("ERROR: ", e);
    }
  });
}