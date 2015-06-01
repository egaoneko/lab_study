<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/29/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>사용자 목록</title>
</head>
<body>
<table border="1">
<#if paging.totalPageCount &gt; 0>
    <tr>
        <td colspan="6">
        ${paging.firstRow}-${paging.endRow}
            [${paging.requestPage}/${paging.totalPageCount}]
        </td>
    </tr>
</#if>

    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Login</td>
        <td>Role</td>
        <td>Created Date</td>
        <td>Last Date</td>
    </tr>

<#if hasUser == false>
    <tr>
        <td colspan="6">
            사용자가 없습니다.
        </td>
    </tr>

<#else>
    <#list userList as list>
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <td>${list.login}</td>
            <td>
                <#if list.roles?exists>
                    <#list list.roles as role>
                        ${role.name}<#if role_has_next>, </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if list.createdDate?exists>
                    ${list.createdDate}
                </#if>
            </td>
            <td>
                <#if list.createdDate?exists>
                    ${list.lastDate}
                </#if>
            </td>
        </tr>
    </#list>

<#-- Paging -->
    <tr>
        <td colspan="6">
            <#if paging.beginPage &gt; 10>
                <a href="list?p=${pagingVO.beginPage-1}">이전</a>
            </#if>
            <#list paging.beginPage..paging.endPage as pno>
                <a href="list?p=${pno}">[${pno}]</a>
            </#list>
            <#if paging.endPage < paging.totalPageCount>
                <a href="list?p=${paging.endPage + 1}">다음</a>
            </#if>
        </td>
    </tr>
</#if>

</table>
</body>
</html>
