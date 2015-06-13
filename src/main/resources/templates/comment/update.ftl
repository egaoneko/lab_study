<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/3/15 | 11:43 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="comment" type="net.study.domain.Comment" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->

<br>

<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-horizontal" name="${comment.id?c}_ajaxForm_update" id="${comment.id?c}_ajaxForm_update" action="/comment/update" method="post">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="commentId" value="${comment.id?c}"/>

            <fieldset>
                <div id="comment_update_warn" class="form-group">
                    <div class="col-lg-12">
                        <textarea class="form-control" rows="5" name="content" id="${comment.id?c}_content_update" required>${comment.content}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-12 text-center">
                        <input type="button" id="${comment.id?c}_ajaxForm_cancel_update" class="btn btn-danger btn-xs" onclick="showComment(1)" value="Cancel"/>
                        <input type="button" id="${comment.id?c}_ajaxForm_submit_update" class="btn btn-success btn-xs" onclick="ajaxForm_update('${comment.id?c}')" value="Submit"/>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>