<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="userList" type="java.util.List<net.study.domain.User>" -->
<#-- @ftlvariable name="paging" type="net.study.util.Paging" -->
<#-- @ftlvariable name="hasUser" type="java.lang.Boolean" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>Study/Users</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section>

            <br>

            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 panel panel-default">

                <legend class="text-center">Users</legend>

                <table class="table table-striped table-hover">
                    <thead class="text-center">
                        <tr>
                            <th>Email</th>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Created Date</th>
                            <th>Last Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#if hasUser == false>
                        <tr>
                            <td colspan="5">
                                Can not found users.
                            </td>
                        </tr>

                        <#else>
                            <#list userList as list>
                            <tr>
                                <td><a href="/user/${list.id?c}">${list.email}</a></td>
                                <td>${list.name}</td>
                                <td>${list.role}</td>
                                <td>${list.createdDate?string("yyyy-MM-dd HH:mm")}</td>
                                <td>${list.lastDate?string("yyyy-MM-dd HH:mm")}</td>
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
                        <a href="/user/create" class="btn btn-primary btn-raised">Create</a>
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