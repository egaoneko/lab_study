
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

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>Settings</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
    <@layout.extends name="layouts/header.ftl">
    </@layout.extends>
    </@layout.put>


    <@layout.put block="contents">
        <section>
            <@spring.bind "form" />
            <#if spring.status.error>
                <#list spring.status.errorMessages as error>
                    <div class="alert alert-dismissable alert-danger">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <p>${error}</p>
                    </div>
                </#list>
            </#if>
        </section>

        <br>

        <section>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 well">
                <section>
                    <form role="form" action="password" method="post" class="form-horizontal">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="id" id="id" value="${currentUser.getId()?c}"/>

                        <fieldset>
                            <legend class="text-center">Change Password</legend>
                            <div class="form-group <#if spring.status.error><#list spring.status.errorMessages as error><#if error?contains("Old")>has-error</#if></#list></#if>">
                                <div class="col-lg-12">
                                    <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="Password" required autofocus>
                                </div>
                            </div>

                            <div class="form-group <#if spring.status.error><#list spring.status.errorMessages as error><#if error?contains("Passwords")>has-error</#if></#list></#if>">
                                <div class="col-lg-12">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="New Password" required>
                                </div>
                            </div>

                            <div class="form-group <#if spring.status.error><#list spring.status.errorMessages as error><#if error?contains("Passwords")>has-error</#if></#list></#if>">
                                <div class="col-lg-12">
                                    <input type="password" class="form-control" name="passwordRepeated" id="passwordRepeated" placeholder="Repeat Password" required>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <div class="col-lg-12">
                                    <button type="submit" class="btn btn-primary btn-lg">Update Password</button>
                                    <a href="javascript:void(0)" class="help-block">Forgot password?</a>
                                </div>
                            </div>
                        </fieldset>
                    </form>

                    <hr>
                    <legend class="text-center">Delete</legend>
                    <a href="delete">Delete</a>
                </section>
            </div>

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