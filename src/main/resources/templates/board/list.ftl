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


<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Article List</title>
</head>
<body>
<table border="1">
<#if paging.totalPageCount &gt; 0>
    <tr>
        <td colspan="5">
        ${paging.firstRow?c}-${paging.endRow?c}
            [${paging.requestPage?c}/${paging.totalPageCount?c}]
        </td>
    </tr>
</#if>

    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Author</td>
        <td>Posting Date</td>
        <td>Read Count</td>
    </tr>

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
            <td>${list.user.getName()}</td>
            <td>${list.getDifferentTime()}</td>
            <td>${list.readCount}</td>
        </tr>
    </#list>

<#-- Paging -->
    <tr>
        <td colspan="5">
            <#if paging.beginPage &gt; 10>
                <a href="list?p=${(paging.beginPage - 1)?c}">previous</a>
            </#if>
            <#list paging.beginPage..paging.endPage as pno>
                <a href="list?p=${pno?c}">[${pno?c}]</a>
            </#list>
            <#if paging.endPage &lt; paging.totalPageCount>
                <a href="list?p=${(paging.endPage + 1)?c}">next</a>
            </#if>
        </td>
    </tr>
</#if>

<#if currentUser??>
    <tr>
        <td colspan="5">
            <a href="write">Write</a>
        </td>
    </tr>
</#if>
</table>
</body>
</html>
