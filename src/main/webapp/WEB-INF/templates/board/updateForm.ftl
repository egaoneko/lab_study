<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="board" type="net.study.domain.Board" -->
<#-- @ftlvariable name="form" type="net.study.domain.UserCreateForm" -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>수정하기</title>
</head>
<body>
    <form action="/article/update" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="boardId" value="${board.id}"/>
        제목 : <input type="text" name="title" size="20" value="${board.title}"/><br/>
        글내용 : <br/>
        <textarea name="content" cols="40" rows="5" >${board.content}</textarea>
        <br/>
        <input type="submit" value="전송"/>
    </form>
</body>
</html>