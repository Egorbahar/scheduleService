function signOut(){
  $.ajax({
    type : "GET",
    url : "/authentication/signout?token="+localStorage.getItem("token"),
    success: function(response){
      localStorage.setItem("token", "")
      localStorage.setItem("role", "")
      localStorage.setItem("userId", "")
    },
    error : function(e) {
      alert("ERROR: ", e);
      console.log("ERROR: ", e);
    }
  });
}