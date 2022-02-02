$(document).ready(function(){
    let id = 0;

    $(document).on("click", "#div_customer_table table button.btn_delete", function() {
        let btn_id = (event.srcElement.id);
        id = btn_id.split("_")[2];

        $("div.modal-body")
            .text("Действительно удалить отдел с id = " + id + " ?");
        $("#model-delete-btn").css({"display": "inline"});
    });

    $(document).on("click", "#model-delete-btn", function() {
        $.ajax({
            url: '/departments/' + id +"?token="+localStorage.getItem("token"),
            type: 'DELETE',
            success: function(response) {
                $("div.modal-body")
                    .text("Успешно удалено");

                $("#model-delete-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                let row_id = "tr_" + id;
                $("#" + row_id).remove();
                $("#div_customer_updating").css({"display": "none"});
            },
            error: function(error){
                $("#div_customer_updating").css({"display": "none"});
                alert(error["responseText"]);
            }
        });
    });
});