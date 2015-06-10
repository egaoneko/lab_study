<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Article Write</title>
</head>
<body>
    <form action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div>
            <label for="title">Title</label>
            <input type="text" name="title" id="title" size="100" required autofocus/>
        </div>

        <div>
            <label for="content">Content</label>
            <textarea name="content" id="content" cols="40" rows="5" required></textarea>
        </div>

        <input type="submit" value="Send">
        <input type="button" onclick="location.href='/article/list'" value="Cancel"/>
    </form>
</body>
</html>