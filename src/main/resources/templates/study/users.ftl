<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/19/15 | 5:39 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="users" type="java.util.Set<net.study.domain.User>" -->
<#-- @ftlvariable name="owner" type="net.study.domain.User" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/Study/Users</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">

    <br>

    <section>
        <div class="col-xs-12 col-sm-8 col-md-6 col-lg-6 col-sm-offset-2 col-md-offset-3 col-lg-offset-3">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Study User List</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <#list users as user>
                            <div class="list-group-item">
                                <div class="row-picture">
                                    <img class="circle" src="<#if user.assets??>${user.assets.realPath}<#else>/img/user.png</#if>" alt="icon">
                                </div>
                                <div class="row-content">
                                    <h4 class="list-group-item-heading">${user.name}</h4>
                                    <p class="list-group-item-text">
                                        <#if user.contact??>
                                            <#switch user.contact.contactType.name()>
                                                <#case "EMAIL">
                                                    <button type="button" class="btn btn-default btn-sm" id="contact_tooltip" data-container="body" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="${user.contact.content}"></button>
                                                    <#break>
                                                <#case "PHONE">
                                                    <button type="button" class="btn btn-default btn-sm" id="contact_tooltip" data-container="body" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="${user.contact.content}"><div class="icon-preview"><i class="mdi-communication-call"></i></div></button>
                                                    <#break>
                                                <#case "KAKAO">
                                                    <button type="button" class="btn btn-default btn-sm" id="contact_tooltip" data-container="body" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="${user.contact.content}"><div class="icon-preview"><i class="mdi-communication-messanger"></i></div></button>
                                                    <#break>
                                            </#switch>
                                        </#if>
                                        <#if user.id != owner.id>
                                            <#if user.id == currentUser.id || owner.id == currentUser.id>
                                                <form action="" method="post" style="display: inline;">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <input type="hidden" name="userId" value="${user.id?c}"/>
                                                    <input type="submit" class="btn btn-danger btn-xs" value="Leave" >
                                                </form>
                                            </#if>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                            <div class="list-group-separator"></div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script">
        <script type="text/javascript">
            $(function () {
                $('#contact_tooltip').tooltip();
            });
        </script>
    </@layout.put>
</@layout.extends>