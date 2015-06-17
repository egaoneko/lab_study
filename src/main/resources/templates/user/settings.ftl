
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
<#-- @ftlvariable name="deleteError" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="nameError" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="nameSuccess" type="java.util.Optional<String>" -->
<#-- @ftlvariable name="avatarSuccess" type="java.util.Optional<String>" -->
<#import "/spring.ftl" as spring>

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/Settings</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section>
            <@spring.bind "form" />
            <#if spring.status.error>
                <div class="alert alert-dismissable alert-danger text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <#list spring.status.errorMessages as error>
                        <p>${error}</p>

                        <#if error?contains("Old")><#global errorOldPassword=true></#if>
                        <#if error?contains("Passwords") || error?contains("Password")><#global errorPassword=true></#if>
                        <#if error?contains("Passwords") || error?contains("PasswordRepeated")><#global errorPasswordRepeated=true></#if>
                    </#list>
                </div>
            </#if>
            <#if deleteError??>
                <div class="alert alert-dismissable alert-danger text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>${deleteError}</p>
                </div>
            </#if>
            <#if nameError??>
                <div class="alert alert-dismissable alert-danger text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>${nameError}</p>
                </div>
            </#if>
            <#if nameSuccess??>
                <div class="alert alert-dismissable alert-success text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>${nameSuccess}</p>
                </div>
            </#if>
            <#if avatarSuccess??>
                <div class="alert alert-dismissable alert-success text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>${avatarSuccess}</p>
                </div>
            </#if>
        </section>

        <br>

        <section>
            <div class="col-xs-12 col-sm-8 col-md-6 col-lg-6 col-sm-offset-2 col-md-offset-3 col-lg-offset-3">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Public Profile</h3>
                    </div>
                    <div class="panel-body">

                        <br>

                        <form role="form" action="admin" method="post" enctype="multipart/form-data" class="form-horizontal">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <fieldset>
                                <div class="form-group <#if nameError??>has-error</#if>">
                                    <label for="name" class="col-lg-2 control-label">Name</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Name" value="${currentUser.name}" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="inputFile" class="col-lg-2 control-label">Avatar</label>
                                    <div class="col-lg-10">
                                        <input type="text" readonly="" class="form-control floating-label" placeholder="Browse...">
                                        <input type="file" name="file" id="inputFile" multiple="">
                                    </div>
                                </div>

                                <div class="form-group text-right">
                                    <div class="col-lg-12">
                                        <button type="submit" class="btn btn-info">Update Profile</button>
                                    </div>
                                </div>
                            </fieldset>

                            <hr>

                            <div>
                                <img id="avatar" src="<#if currentUser.assets??>${currentUser.assets.realPath}<#else>/img/user.png</#if>" alt="picture" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            </div>
                        </form>
                    </div>
                </div>

                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title">Change Password</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="password" method="post" class="form-horizontal">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="id" id="id" value="${currentUser.getId()?c}"/>

                            <fieldset>
                                <div class="form-group <#if errorOldPassword??>has-error</#if>">
                                    <label for="oldPassword" class="col-lg-2 control-label">Old Password</label>
                                    <div class="col-lg-10">
                                        <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="Password" required>
                                    </div>
                                </div>

                                <div class="form-group <#if errorPassword??>has-error</#if>">
                                    <label for="password" class="col-lg-2 control-label">Password</label>
                                    <div class="col-lg-10">
                                        <input type="password" class="form-control" name="password" id="password" placeholder="New Password" required>
                                    </div>
                                </div>

                                <div class="form-group <#if errorPasswordRepeated??>has-error</#if>">
                                    <label for="passwordRepeated" class="col-lg-2 control-label">Repeat Password</label>
                                    <div class="col-lg-10">
                                        <input type="password" class="form-control" name="passwordRepeated" id="passwordRepeated" placeholder="Repeat Password" required>
                                    </div>
                                </div>

                                <div class="form-group text-right">
                                    <div class="col-lg-12">
                                        <button type="submit" class="btn btn-warning">Update Password</button>
                                        <a href="/forgot_password/new" class="help-block">Forgot password?</a>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>

                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title">Delete Account</h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12 text-right">
                            <button class="btn btn-danger btn-lg" data-toggle="modal" data-target="#delete-dialog">Delete</button>
                        </div>
                    </div>
                </div>

                <div id="delete-dialog" class="modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title">Delete Account</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form" name="form" id="user_delete" action="delete" method="post" class="form-horizontal">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                    <fieldset>
                                        <div class="form-group <#if deleteError??>has-error</#if>">
                                            <div class="col-lg-12">
                                                <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" onclick="document.getElementById('user_delete').submit();" class="btn btn-danger">Delete</button>
                            </div>
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
            $("input[name='file']").on("change", function(){

                // Get a reference to the fileList
                var files = !!this.files ? this.files : [];

                // If no files were selected, or no FileReader support, return
                if ( !files.length || !window.FileReader ) return;

                // Only proceed if the selected file is an image
                if ( /^image/.test( files[0].type ) ) {

                    // Create a new instance of the FileReader
                    var reader = new FileReader();

                    // Read the local file as a DataURL
                    reader.readAsDataURL( files[0] );

                    // When loaded, set image data as background of page
                    reader.onloadend = function(){
                        $("#avatar").attr("src", this.result);
                    }
                }
            });
        </script>
    </@layout.put>
</@layout.extends>