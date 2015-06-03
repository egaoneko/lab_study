/**
 * Created by seodonghyeon on 6/3/15.
 */

function showComment(page) {

    // Loading 0.5 seconds
    setTimeout(function() {

        $('#reply').load(
            contextPath + "/comment/list/"+boardId,
            {
                p:page
            }
        )
    }, 500);
}

function toggleWrite() {
    $('#write').toggle()
    $('#write_btn').toggle()
}

function toggleReply(id) {

    $('#'+id+'_reply').toggle()
    $('#'+id+'_reply').load(contextPath + "/comment/reply/"+id)
}

function updateComment(id) {

    $('#'+id).load(contextPath + "/comment/update/"+id)
}

function deleteComment(id) {

    $.ajax({
        type: "POST",
        url: contextPath + "/comment/delete",
        data: {
            commentId:id
        },
        success: function (data) {
            if (data === "404" || data === "400")
                alert("An error occurred while deleting the comment.");
            else {
                alert("Deleting the comment was successful.");

                // Reload reply
                showComment(1);
            }
        }
    });
}

function ajaxForm() {

    $("#comment_ajaxForm").submit(function (e) {
        if (isWriting == false) {
            isWriting = true;

            if ($("#content").val() == "") {
                alert("Please input content for writing.");
                isWriting = false;
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
                    alert("Writing the comment was successful.");

                    // Reload reply
                    showComment(1);

                    // Reset content
                    $("#content").val("");

                    // Reset
                    isWriting = false;
                },
                statusCode: {
                    400: function() {
                        alert("An error occurred while writing the comment.");
                    },
                    404: function() {
                        alert("An error occurred while writing the comment.");
                    },
                    500: function() {
                        alert("An error occurred while writing the comment.");
                    }
                }
            });

            e.preventDefault(); //STOP default action
        }
        ;
    });
    $("#comment_ajaxForm").submit();
}

function ajaxForm_reply(id) {

    $("#" + id + "_ajaxForm_reply").submit(function (e) {
        if (isReplying == false) {
            isReplying = true;

            if ($("#" + id + "_content_reply").val() == "") {
                alert("Please input content for replying.");
                isReplying = false;
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
                    alert("Replying the comment was successful.");

                    // Reload reply
                    showComment(1);

                    // Reset content
                    $("#" + id + "_content_reply").val("");

                    // Reset
                    isReplying = false;
                },
                statusCode: {
                    400: function() {
                        alert("An error occurred while replying the comment.");
                    },
                    404: function() {
                        alert("An error occurred while replying the comment.");
                    },
                    500: function() {
                        alert("An error occurred while replying the comment.");
                    }
                }
            });

            e.preventDefault(); //STOP default action
        }
        ;
    });
    $("#" + id + "_ajaxForm_reply").submit();
}

function ajaxForm_update(id) {

    $("#" + id + "_ajaxForm_update").submit(function (e) {
        if (isUpdating == false) {
            isUpdating = true;

            if ($("#" + id + "_content_update").val() == "") {
                alert("Please input content for updating.");
                isUpdating = false;
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
                    alert("Updating the comment was successful.");

                    // Reload reply
                    $("#" + id + "_content_update").val("");

                    // Reset content
                    showComment(1);

                    // Reset
                    isUpdating = false;
                },
                statusCode: {
                    400: function () {
                        alert("An error occurred while updating the comment.");
                    },
                    404: function () {
                        alert("An error occurred while updating the comment.");
                    },
                    500: function () {
                        alert("An error occurred while updating the comment.");
                    }
                }
            });

            e.preventDefault(); //STOP default action
        }
        ;
    });
    $("#" + id + "_ajaxForm_update").submit();
}