<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <nav role="navigation">
            <ul>
                <#if !currentUser??>
                    <li><a href="/login">Log in</a></li>
                </#if>
                <#if currentUser??>
                    <li>
                        <form action="/logout" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit">Log out</button>
                        </form>
                    </li>
                    <li><a href="/user/${currentUser.id?c}">View myself</a></li>
                    <li><a href="/settings/admin">Account Settings</a></li>
                </#if>
                <#if currentUser?? && currentUser.role == "ADMIN">
                    <li><a href="/user/create">Create a new user</a></li>
                    <li><a href="/users">View all users</a></li>
                </#if>
                <li><a href="/article/list">Article list</a></li>
                <li><a href="/study/list">Study list</a></li>
                <li><a href="/message/list/receive">Receive Messages</a></li>
            </ul>
        </nav>
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>
</@layout.extends>