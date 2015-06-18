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

            <div class="panel panel-${board.study.status.color}">
                <div class="panel-heading">
                    <h1>${board.title}</h1><h4 class="text-right">${board.getDifferentTime()}</h4>
                </div>
                <div class="panel-body">

                    <table class="table table-striped table-hover hidden-xs hidden-sm">
                        <colgroup>
                            <col width="15%">
                            <col width="15%">
                            <col width="10%">
                            <col width="10%">
                            <col width="10%">
                            <col width="10%">
                            <col width="10%">
                            <col width="10%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="text-center">Category</th>
                                <th class="text-center">Area</th>
                                <th class="text-center">Status</th>
                                <th class="text-center">Way</th>
                                <th class="text-center">On-Off Line</th>
                                <th class="text-center">Charge</th>
                                <th class="text-center">Price</th>
                                <th class="text-center">Participant</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="${board.study.status.color}">
                                <td class="text-center"><span class="label label-default">${board.study.category.title}</span></td>
                                <td class="text-center"><span class="label label-default">${board.study.area.title}</span></td>
                                <td class="text-center"><span class="label label-${board.study.status.color}">${board.study.status.title}</span></td>
                                <td class="text-center"><span class="label label-${board.study.way.color}">${board.study.way.title}</span></td>
                                <td class="text-center"><span class="label label-${board.study.onOffLine.color}">${board.study.onOffLine.title}</span></td>
                                <td class="text-center"><span class="label label-${board.study.charge.color}">${board.study.charge.title}</span></td>
                                <td class="text-center"><span class="label label-default">${board.study.price?c}</span></td>
                                <td class="text-center"><span class="label label-${board.study.status.color}">${board.study.participant?c}</span></td>
                                </tr>
                            </tbody>
                    </table>

                    <table class="table table-striped table-hover hidden-md hidden-lg">
                        <tbody>
                            <tr>
                                <th class="text-center">Category</th>
                                <td class="text-center"><span class="label label-default">${board.study.category.title}</span></td>
                            </tr>
                            <tr>
                                <th class="text-center">Area</th>
                                <td class="text-center"><span class="label label-default">${board.study.area.title}</span></td>
                            </tr>
                            <tr>
                                <th class="text-center">Status</th>
                                <td class="text-center"><span class="label label-${board.study.status.color}">${board.study.status.title}</span></td>
                            </tr>
                            <tr>
                                <th class="text-center">Way</th>
                                <td class="text-center"><span class="label label-${board.study.way.color}">${board.study.way.title}</span></td>
                            </tr>
                            <tr>
                                <th class="text-center">On-Off Line</th>
                                <td class="text-center"><span class="label label-${board.study.onOffLine.color}">${board.study.onOffLine.title}</span></td>
                            </tr>
                            <tr>
                                <th class="text-center">Charge</th>
                                <td class="text-center"><span class="label label-${board.study.charge.color}">${board.study.charge.title}</span></td>
                            </tr>
                            <tr>
                                <th class="text-center">Price</th>
                                <td class="text-center"><span class="label label-default">${board.study.price?c}</span></td>
                            </tr>
                            <tr>
                                <th class="text-center">Participant</th>
                                <td class="text-center"><span class="label label-${board.study.status.color}">${board.study.participant?c}</span></td>
                            </tr>
                        </tbody>
                    </table>

                    <table class="table table-striped table-hover">
                        <caption>Book</caption>
                        <colgroup>
                            <col width="10%">
                            <col width="30%">
                            <col width="20%">
                            <col width="15%">
                            <col width="15%">
                            <col width="10%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">Cover</th>
                                <th scope="col">Title</th>
                                <th scope="col">Author</th>
                                <th scope="col">Publisher</th>
                                <th scope="col">Publish Date</th>
                                <th scope="col">Price</th>
                            </tr>
                        </thead>
                        <tbody id="list">
                            <#if board.study.bookSet?has_content>
                                <#list board.study.bookSet as book>
                                <tr id="book_${book.id}">
                                    <td><img src="${book.image}"  width="50px" height="70px" /></td>
                                    <td>${book.title}</td>
                                    <td>${book.author}</td>
                                    <td>${book.publisher}</td>
                                    <td>${book.pubdate}</td>
                                    <td>${book.price}</td>
                                </tr>
                                </#list>
                            <#else>
                                <tr class="__oldlist" style="">
                                    <td colspan="6">Can not found.</td>
                                </tr>
                            </#if>
                        </tbody>
                    </table>

                    <hr>

                    <h3>${board.content}</h3>

                    <#include "../user/author.ftl">

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