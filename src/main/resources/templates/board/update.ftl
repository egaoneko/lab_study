<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="board" type="net.study.domain.Board" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Article Update</title>
</head>
<body>
    <form action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="boardId" value="${board.id?c}"/>
        Title : <input type="text" name="title" size="20" value="${board.title}"/><br/>
        Content : <br/>
        <textarea name="content" cols="40" rows="5" >${board.content}</textarea>
        <br/>
        <input type="submit" value="Send"/>
        <input type="button" onclick="location.href='/article/read/${board.id?c}'" value="Cancel"/>
    </form>
</body>
</html>