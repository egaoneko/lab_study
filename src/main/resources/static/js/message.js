/**
 * Created by seodonghyeon on 6/18/15.
 */
function messageBox(){
    $("#message_box").toggle();
}

function messageAjax() {

    $("#message_form").unbind('submit').bind('submit',function(e) {

        if ($("#message").val() == "") {
            $("#message_warn").addClass("has-error");
            $("#message").focus();

            alert("Please input message for sending.");
            e.preventDefault(); //STOP default action
            return;
        }

        var postData = $(this).serializeArray();
        var formURL = $(this).attr("action");

        $.ajax({
            type: "POST",
            url: formURL,
            data: postData,
            success: function (data) {
                $("#message_warn").removeClass("has-error")
                alert("Sending the message was successful.");

                // Reset content
                $("#message").val("");
                $("#message_box").hide();
            },
            statusCode: {
                400: function () {
                    alert("An error occurred while sending the message.");
                },
                404: function () {
                    alert("An error occurred while sending the message.");
                },
                500: function () {
                    alert("An error occurred while sending the message.");
                }
            }
        });
        e.preventDefault(); //STOP default action
    });

    $("#message_form").submit();
}
