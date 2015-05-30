<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<!DOCTYPE html>
<html>
    <head>
        <title>Spring Security Example </title>
    </head>
<body>
    <#if error?exists>
        <div>
            Invalid username and password.
        </div>
    </#if>
    <#if logout?exists>
    <div>
        You have been logged out.
    </div>
    </#if>
    <form action="/login" method="post">
        <div>
            <label for="email">Email address</label>
            <input type="email" name="login" id="login" required autofocus/>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" required/>
        </div>

        <div><input type="submit" value="Sign In"/></div>
    </form>
</body>
</html>