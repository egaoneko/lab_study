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

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>List of Users</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/user/create">Create a new user</a></li>
    </ul>
</nav>

<h1>List of Users</h1>

<table border="1">
    <#if paging.totalPageCount &gt; 0>
        <tr>
            <td colspan="5">
            ${paging.firstRow}-${paging.endRow}
                [${paging.requestPage}/${paging.totalPageCount}]
            </td>
        </tr>
    </#if>

        <tr>
            <td>Email</td>
            <td>Name</td>
            <td>Role</td>
            <td>Created Date</td>
            <td>Last Date</td>
        </tr>

    <#if hasUser == false>
        <tr>
            <td colspan="5">
                사용자가 없습니다.
            </td>
        </tr>

    <#else>
        <#list userList as list>
            <tr>
                <td><a href="/user/${list.id}">${list.email}</a></td>
                <td>${list.name}</td>
                <td>${list.role}</td>
                <td>${list.createdDate?string("yyyy-MM-dd HH:mm")}</td>
                <td>${list.lastDate?string("yyyy-MM-dd HH:mm")}</td>
            </tr>
        </#list>

    <#-- Paging -->
        <tr>
            <td colspan="5">
                <#if paging.beginPage &gt; 10>
                    <a href="users?p=${pagingVO.beginPage-1}">이전</a>
                </#if>
                <#list paging.beginPage..paging.endPage as pno>
                    <a href="users?p=${pno}">[${pno}]</a>
                </#list>
                <#if paging.endPage < paging.totalPageCount>
                    <a href="users?p=${paging.endPage + 1}">다음</a>
                </#if>
            </td>
        </tr>
    </#if>
</table>

</body>
</html>