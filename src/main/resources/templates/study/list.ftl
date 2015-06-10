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


<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Study List</title>
</head>
<body>
<table border="1">
<#if paging.totalPageCount &gt; 0>
    <tr>
        <td colspan="4">
        ${paging.firstRow?c}-${paging.endRow?c}
            [${paging.requestPage?c}/${paging.totalPageCount?c}]
        </td>
    </tr>
</#if>

    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Posting Date</th>
    </tr>

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
                <a href="read/${list.id?c}?p=${paging.requestPage?c}">
                ${list.title}
                </a>
            </td>
            <td>${list.user.getName()}</td>
            <td>${list.getDifferentTime()}</td>
        </tr>
    </#list>

<#-- Paging -->
    <tr>
        <td colspan="4">
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
        <td colspan="4">
            <a href="write">Write</a>
        </td>
    </tr>
</#if>
</table>
</body>
</html>
