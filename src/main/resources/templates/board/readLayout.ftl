<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/14/15 | 2:08 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="board" type="net.study.domain.Board" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/Article/Read</title>

        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
    <section>

        <br>

        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1">

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
        </div>
    </section>
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script">
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
    </@layout.put>
</@layout.extends>