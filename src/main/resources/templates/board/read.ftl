<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="board" type="net.study.domain.Board" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->

<div class="panel panel-primary">
    <div class="panel-heading">
        <h1>${board.title}</h1><h4 class="text-right">${board.getDifferentTime()}</h4>
    </div>
    <div class="panel-body">
        <h3>${board.content}</h3>

        <#if board.checkUser(currentUser.user)>
            <div class="text-right">
                <input type="button" class="btn btn-info" onclick="location.href='/article/update/${board.id?c}'" value="Update"/>
                <form action="/article/delete" method="post" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="boardId" value="${board.id?c}"/>
                    <input type="submit" class="btn btn-danger" value="Delete" >
                </form>
            </div>
        </#if>

        <hr>

        <!-- For Reply -->
        <div id="reply"></div>
    </div>
</div>

<script src="/js/comment.js"></script>
<script type="text/javascript">

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    var isWriting = false;		// If reply is written, set true
    var isReplying = false;		// If reply is replied, set true
    var isUpdating = false;		// If reply is updated, set true

    contextPath = "${rc.getContextPath()}";
    boardId = "${board.id?c}";

    showComment(1);

</script>