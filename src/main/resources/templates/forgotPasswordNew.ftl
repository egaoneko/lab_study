<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/14/15 | 6:21 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="message" type="java.lang.String" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>Study/Reset/Password</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
    <#--<@layout.extends name="layouts/header.ftl">-->
    <#--</@layout.extends>-->
    </@layout.put>


    <@layout.put block="contents">
        <section>
            <#if error??>
                <div class="alert alert-dismissable alert-danger text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>${error}</p>
                </div>
            </#if>

            <#if message??>
                <div class="alert alert-dismissable alert-success text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>${message}</p>
                </div>
            </#if>
        </section>

        <section class="vertical-center">
            <div class="col-xs-9 col-sm-6 col-md-5 col-lg-4 well">
                <section>
                    <form role="form" action="" method="post" class="form-horizontal">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <fieldset>
                            <legend class="text-center">Reset Password</legend>
                            <div class="form-group <#if error??>has-error</#if>">
                                <div class="col-lg-12">
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email Address" required autofocus>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <div class="col-lg-12">
                                    <button type="submit" class="btn btn-primary btn-lg col-lg-12">Reset my password</button>
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