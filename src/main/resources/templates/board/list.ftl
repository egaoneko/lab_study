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

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>Study/Article/List</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section>

            <br>

            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 well">

                <legend class="text-center">Article List</legend>

                <table class="table table-striped table-hover">
                    <thead class="text-center">
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Posting Date</th>
                            <th>Read Count</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#if hasBoard == false>
                        <tr>
                            <td colspan="5">
                                Can not found articles.
                            </td>
                        </tr>

                        <#else>
                            <#list boardList as list>
                            <tr>
                                <td>${list.id?c}</td>
                                <td>
                                    <a href="read/${list.id?c}?p=${paging.requestPage?c}">
                                    ${list.title}
                                    </a>
                                </td>
                                <td>${list.user.name}</td>
                                <td>${list.getDifferentTime()}</td>
                                <td>${list.readCount}</td>
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
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script">
    </@layout.put>
</@layout.extends>