<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="form" type="net.study.domain.UserCreateForm" -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>글쓰기</title>
</head>
<body>
    <form action="write" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        제목 : <input type="text" name="title" size="20"/><br/>
        글내용: <br/>
        <textarea name="content" cols="40" rows="5"></textarea>
        <br/>
        <input type="submit" value="전송">
    </form>
</body>
</html>