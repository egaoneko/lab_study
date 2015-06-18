<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->
<#-- @ftlvariable name="studyList" type="java.util.List<net.study.domain.Study>" -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/Article/Write</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section>

            <br>

            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 panel panel-default">
                <section>
                    <form role="form" action="" method="post" class="form-horizontal">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <fieldset>
                            <legend class="text-center">Article Write</legend>

                            <div class="form-group">
                                <label for="title" class="col-lg-2 control-label">Title</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" name="title" id="title" placeholder="Title" size="100" required autofocus>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="study" class="col-lg-2 control-label">Study</label>
                                <div class="col-lg-10">
                                    <select class="form-control" name="study" id="study" required>
                                        <option disabled selected> -- select a study -- </option>
                                        <#if studyList??>
                                            <#list studyList as list>
                                                <option value="${list.id}">${list.title}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-lg-2 control-label">Content</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control ckeditor" rows="20" name="content" id="content" placeholder="Write your content" required></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-12 text-center">
                                    <input type="button" class="btn btn-danger" onclick="location.href='/article/list'" value="Cancel"/>
                                    <input type="submit" class="btn btn-success" value="Submit"/>
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
        <script type="text/javascript">

            contextPath = "${rc.getContextPath()}";

            $(function(){
                $('#study').change(function(){
                    $.ajax({
                        url: contextPath + "/study/json",
                        dataType:'json',
                        data:{'id':$("#study option:selected").val()},
                        type:'GET',
                        success:function(result){
                            $('#title').attr('value', result['title']);
                            $('#content').text(result['content']);
                        }
                    });
                })
            })
        </script>
    </@layout.put>
</@layout.extends>