
<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/1/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>Delete User</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Delete User</h1>

<form role="form" name="form" action="delete" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required autofocus/>
    </div>

    <button type="submit">Send</button>
</form>

<#if error??>
    ${error}
</#if>

</body>
</html>