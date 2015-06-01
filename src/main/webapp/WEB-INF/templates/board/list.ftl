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

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시글 목록</title>
</head>
<body>
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
        <td>글 번호</td>
        <td>제목</td>
        <td>작성자</td>
        <td>작성일</td>
        <td>조회수</td>
    </tr>

<#if hasBoard == false>
    <tr>
        <td colspan="5">
            게시글이 없습니다.
        </td>
    </tr>

<#else>
    <#list boardList as list>
        <tr>
            <td>${list.id}</td>
            <td>
                <a href="read/${list.id}?p=${paging.requestPage}">
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
                <a href="list?p=${paging.beginPage-1}">이전</a>
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

    <tr>
        <td colspan="5">
            <a href="write">글쓰기</a>
        </td>
    </tr>
</table>
</body>
</html>
