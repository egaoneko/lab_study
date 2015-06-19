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

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
    <title>${myApp.name}/Sign In</title>
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
            <#if error.isPresent()>
                <div class="alert alert-dismissable alert-danger text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>The email or password you have entered is invalid, try again.</p>
                </div>
            </#if>
        </section>

        <section class="vertical-center">
            <div class="col-xs-9 col-sm-6 col-md-5 col-lg-4 panel panel-default">
                <h1 class="text-primary text-center">${myApp.name}</h1>
                <section>
                    <form role="form" action="/login" id="form" method="post" class="form-horizontal">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <fieldset>
                            <legend class="text-center">Sign In</legend>
                            <div class="form-group <#if error.isPresent()>has-error</#if>">
                                <div class="col-lg-12">
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email Address" required autofocus>
                                </div>
                            </div>

                            <div class="form-group <#if error.isPresent()>has-error</#if>">
                                <div class="col-lg-12">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                                    <br>
                                    <div class="togglebutton text-center">
                                        <label>
                                            <input type="checkbox" name="remember-me" id="remember-me"> Remember me
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <div class="col-lg-12">
                                    <button type="submit" class="btn btn-primary btn-lg col-lg-12">Sign In</button>
                                    <a href="/forgot_password/new" class="help-block">Forgot password?</a>
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