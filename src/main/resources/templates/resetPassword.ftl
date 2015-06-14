<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/15/15 | 4:45 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="net.study.domain.form.ForgotPasswordForm" -->
<#import "/spring.ftl" as spring>

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
    <title>Study/Forgot/Password</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
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

                    <#if error?contains("Passwords") || error?contains("Password")><#global errorPassword=true></#if>
                    <#if error?contains("Passwords") || error?contains("PasswordRepeated")><#global errorPasswordRepeated=true></#if>
                </#list>
            </div>
        </#if>
    </section>

    <section class="vertical-center">
        <div class="col-xs-9 col-sm-6 col-md-5 col-lg-4 well">
            <section>
                <form role="form" action="" method="post" class="form-horizontal">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <fieldset>
                        <legend class="text-center">Reset Your Password</legend>

                        <div class="form-group <#if errorPassword??>has-error</#if>">
                            <div class="col-lg-12">
                                <input type="password" class="form-control" name="password" id="password" placeholder="Password" required autofocus>
                            </div>
                        </div>

                        <div class="form-group <#if errorPasswordRepeated??>has-error</#if>">
                            <div class="col-lg-12">
                                <input type="password" class="form-control" name="passwordRepeated" id="passwordRepeated" placeholder="Repeat Password" required>
                            </div>
                        </div>

                        <div class="form-group text-center">
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-primary btn-lg col-lg-12">Reset your password</button>
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