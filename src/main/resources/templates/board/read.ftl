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

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>

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

    <title>Article Read</title>
</head>
<body>
    <table>
        <tr>
            <td>Title</td>
            <td>${board.title}</td>
        </tr>
        <tr>
            <td>Author</td>
            <td>${board.user.getName()}</td>
        </tr>
        <tr>
            <td>Posting Date</td>
            <td>${board.postingDate}</td>
        </tr>
        <tr>
            <td>Content</td>
            <td>
                ${board.content}
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="button" onclick="location.href='/article/list'" value="List"/>
                <input type="button" onclick="location.href='/article/update/${board.id?c}'" value="Update"/>
                <form action="/article/delete" method="post" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="boardId" value="${board.id?c}"/>
                <input type="submit" value="Delete" >
                </form>

            </td>
        </tr>
    </table>

    <!-- For Reply -->
    <div id="reply"></div>

</body>
</html>