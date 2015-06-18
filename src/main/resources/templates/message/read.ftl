<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/19/15 | 1:14 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="message" type="net.study.domain.Message" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->


<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 class="modal-title"><span class="label label-info col-xs-2 col-sm-2 col-md-2 col-lg-2">From</span>&nbsp;&nbsp;&nbsp;${message.sender.name}<small>(${message.getSendDifferentTime()})</small></h4>
    <h4 class="modal-title"><span class="label label-warning col-xs-2 col-sm-2 col-md-2 col-lg-2">To</span>&nbsp;&nbsp;&nbsp;${message.receiver.name}<small>(${message.getReceiveDifferentTime()})</small></h4>
    <h4 class="modal-title"><span class="label label-success col-xs-2 col-sm-2 col-md-2 col-lg-2">Study</span>&nbsp;&nbsp;&nbsp;<a href="/study/read/${message.study.id}">${message.study.title}</a></h4>
    <hr>
</div>
<div class="modal-body">
    <p>${message.message}</p>
</div>
<div class="modal-footer">
    <#if message.studyRequest.name() == "ACCEPT" || message.studyRequest.name() == "REJECT">
        <button type="button" class="btn btn-${message.studyRequest.color}">${message.studyRequest.title}</button>
    <#else>
        <button type="button" class="btn btn-danger" onclick="messageAjax('reject','${message.id}')">Reject</button>
        <#if message.study.participants?size &gt;= message.study.participant>
            <button type="button" class="btn btn-info disabled" onclick="">Accept(Excess)</button>
        <#else>
            <button type="button" class="btn btn-info" onclick="messageAjax('accept','${message.id}')">Accept</button>
        </#if>
    </#if>
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div>

<form action="/message/accept" id="accept" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="studyId" value="${message.study.id}">
    <input type="hidden" name="userId" value="${message.sender.id}">
    <input type="hidden" name="messageId" value="${message.id}">
</form>

<form action="/message/reject" id="reject" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="messageId" value="${message.id}">
</form>

<script type="text/javascript">
    contextPath = "${rc.getContextPath()}";

    function messageAjax(id, messageId) {

        $("#"+id).unbind('submit').bind('submit',function(e) {

            var postData = $(this).serializeArray();
            var formURL = $(this).attr("action");

            $.ajax({
                type: "POST",
                url: formURL,
                data: postData,
                success: function (data) {
                    console.log(data)
                    if(data != null){
                        alert("Accepting the user was successful.");
                        $('#read').load(contextPath + "/message/read/"+messageId)
                    } else {
                        alert("An error occurred while accepting the user.");
                    }
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

        $("#"+id).submit();
    }
</script>