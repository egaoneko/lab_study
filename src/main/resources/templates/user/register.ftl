
<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="net.study.domain.form.UserCreateForm" -->
<#import "/spring.ftl" as spring>

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/SignUp</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
    <style>
        body{
            background: #009688;
        }
    </style>
    <#--<@layout.extends name="layouts/header.ftl">-->
    <#--</@layout.extends>-->
    </@layout.put>

    <@layout.put block="contents">
        <section>
            <@spring.bind "form" />
            <#if spring.status.error>
                <div class="alert alert-dismissable alert-danger text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <#list spring.status.errorMessages as error>
                        <p>${error}</p>

                        <#if error?contains("email") || error?contains("Email")><#global errorEmail=true></#if>
                        <#if error?contains("Name")><#global errorName=true></#if>
                        <#if error?contains("Passwords") || error?contains("Password")><#global errorPassword=true></#if>
                        <#if error?contains("Passwords") || error?contains("PasswordRepeated")><#global errorPasswordRepeated=true></#if>
                    </#list>
                </div>
            </#if>
        </section>

        <section class="vertical-center">
            <div class="col-xs-9 col-sm-6 col-md-5 col-lg-4 panel panel-default">
                <h1 class="text-primary text-center">${myApp.name}</h1>
                <section>
                    <form role="form" action="" method="post" class="form-horizontal">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <fieldset>
                            <legend class="text-center">Sign Up</legend>
                            <div class="form-group <#if errorEmail??>has-error</#if>">
                                <div class="col-lg-12">
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email Address" value="${form.email}" required autofocus>
                                </div>
                            </div>

                            <div class="form-group <#if errorName??>has-error</#if>">
                                <div class="col-lg-12">
                                    <input type="text" class="form-control" name="name" id="name" placeholder="Name" size="100" value="${form.name}" required>
                                </div>
                            </div>

                            <div class="form-group <#if errorPassword??>has-error</#if>">
                                <div class="col-lg-12">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                                </div>
                            </div>

                            <div class="form-group <#if errorPasswordRepeated??>has-error</#if>">
                                <div class="col-lg-12">
                                    <input type="password" class="form-control" name="passwordRepeated" id="passwordRepeated" placeholder="Repeat Password" required>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <div class="col-lg-12">
                                    <button type="submit" class="btn btn-primary btn-lg col-lg-12">Sign Up</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </section>
            </div>
        </section>
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script">
    </@layout.put>
</@layout.extends>