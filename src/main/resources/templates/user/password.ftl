
<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/1/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="net.study.domain.form.UserUpdatePasswordForm" -->
<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>Update Password</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Update Password</h1>

<form role="form" name="form" action="password" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" id="id" value="${currentUser.getId()?c}"/>

    <div>
        <label for="oldPassword">Old Password</label>
        <input type="password" name="oldPassword" id="oldPassword" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <div>
        <label for="passwordRepeated">Repeat</label>
        <input type="password" name="passwordRepeated" id="passwordRepeated" required/>
    </div>
    <button type="submit">Save</button>
</form>

<@spring.bind "form" />
<#if spring.status.error>
<ul>
    <#list spring.status.errorMessages as error>
        <li>${error}</li>
    </#list>
</ul>
</#if>

<h1>Delete User</h1>

<a href="delete">Delete</a>

</body>
</html>