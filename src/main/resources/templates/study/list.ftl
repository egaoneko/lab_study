<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/27/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="studyList" type="java.util.List<net.study.domain.Study>" -->
<#-- @ftlvariable name="paging" type="net.study.util.Paging" -->
<#-- @ftlvariable name="hasStudy" type="java.lang.Boolean" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>Study/Study/List</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section>

            <br>

            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 panel panel-default">

                <legend class="text-center">Study List</legend>

                <table class="table table-striped table-hover">
                    <thead class="text-center">
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Posting Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#if hasStudy == false>
                        <tr>
                            <td colspan="4">
                                Can not found articles.
                            </td>
                        </tr>

                        <#else>
                            <#list studyList as list>
                            <tr>
                                <td>${list.id?c}</td>
                                <td>
                                    <a href="javascript:void(0)" onclick="load_page(${list.id?c})" data-toggle="modal" data-target="#read-dialog">
                                    ${list.title}
                                    </a>
                                </td>
                                <td>${list.user.getName()}</td>
                                <td>${list.getDifferentTime()}</td>
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
        <script type="text/javascript">

            contextPath = "${rc.getContextPath()}";

            function load_page(id) {

                // Loading 0.5 seconds
                setTimeout(function() {

                    $('#read').load(contextPath + "/study/read/"+id)
                }, 500);
            }
        </script>
    </@layout.put>
</@layout.extends>