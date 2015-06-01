<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15
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
    <title>Log in</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Log in</h1>

<p>You can use: demo@localhost / demo</p>

<form role="form" action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="email">Email address</label>
        <input type="email" name="email" id="email" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <div>
        <label for="remember-me">Remember me</label>
        <input type="checkbox" name="remember-me" id="remember-me"/>
    </div>
    <input type="submit" value="Sign in"/>
    <input type="button" onclick="location.href='/user/register'" value="Sign up"/>
</form>

<#if error.isPresent()>
<p>The email or password you have entered is invalid, try again.</p>
</#if>
</body>
</html>