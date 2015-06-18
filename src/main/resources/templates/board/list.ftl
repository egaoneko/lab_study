<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/27/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="boardList" type="java.util.List<net.study.domain.Board>" -->
<#-- @ftlvariable name="paging" type="net.study.util.Paging" -->
<#-- @ftlvariable name="hasBoard" type="java.lang.Boolean" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/Article List</title>

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

            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 panel panel-default">

                <legend class="text-center">Article List</legend>

                <table class="table table-striped table-hover">
                    <colgroup>
                        <col width="10%">
                        <col width="10%">
                        <col width="50%">
                        <col width="10%">
                        <col width="15%">
                        <col width="5%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th class="text-center">Area</th>
                        <th class="text-center">Category</th>
                        <th class="text-center">Title</th>
                        <th class="text-center">Author</th>
                        <th class="text-center">Posting Date</th>
                        <th class="text-center">Read Cnt</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if hasBoard == false>
                        <tr>
                            <td colspan="6">
                                Can not found articles.
                            </td>
                        </tr>

                        <#else>
                            <#list boardList as list>
                                <tr class="<#if list.study.status.name() == "EXCESS" || list.study.status.name() == "CLOSE">${list.study.status.color}</#if>">
                                    <td class="text-center">${list.study.area.title}</td>
                                    <td class="text-center">${list.study.category.title}</td>
                                    <td>
                                        <a href="javascript:void(0)" onclick="load_page(${list.id?c})" data-toggle="modal" data-target="#read-dialog">
                                        ${list.title}
                                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <span class="label label-${list.study.status.color}">${list.study.status.title}</span>
                                        <span class="label label-${list.study.way.color}">${list.study.way.title}</span>
                                        <span class="label label-${list.study.onOffLine.color}">${list.study.onOffLine.title}</span>
                                        <span class="label label-${list.study.charge.color}">${list.study.charge.title}</span>
                                    </td>
                                    <td class="text-center">${list.user.name}</td>
                                    <td class="text-center">${list.getDifferentTime()}</td>
                                    <td class="text-center">${list.readCount}</td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>

                <div class="text-center">
                    <ul class="pagination">
                        <#if paging.beginPage &gt; 10>
                            <li><a href="list?p=${(paging.beginPage - 1)?c}">«</a></li>
                        <#else>
                            <li class="disabled"><a href="javascript:void(0)">«</a></li>
                        </#if>

                        <#list paging.beginPage..paging.endPage as pno>
                            <#if paging.requestPage == pno>
                                <li class="active"><a href="list?p=${pno?c}">${pno?c}</a></li>
                            <#else>
                                <li><a href="list?p=${pno?c}">${pno?c}</a></li>
                            </#if>
                        </#list>

                        <#if paging.endPage &lt; paging.totalPageCount>
                            <li><a href="list?p=${(paging.endPage + 1)?c}">»</a></li>
                        <#else>
                            <li class="disabled"><a href="javascript:void(0)">»</a></li>
                        </#if>
                    </ul>
                </div>

                <div class="text-right">
                    <#if currentUser??>
                        <a href="write" class="btn btn-primary btn-raised">Write</a>
                    </#if>
                </div>

            </div>
        </section>

        <section>
            <div>
                <div id="read-dialog" class="modal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div id="read" class="modal-body"></div>
                        </div>
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

            contextPath = "${rc.getContextPath()}";

            function load_page(id) {

                // Loading 0.5 seconds
                setTimeout(function() {

                    $('#read').load(contextPath + "/article/read/"+id)
                }, 500);
            }
        </script>
    </@layout.put>
</@layout.extends>