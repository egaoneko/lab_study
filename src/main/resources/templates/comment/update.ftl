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

<form name="${comment.id?c}_ajaxForm_update" id="${comment.id?c}_ajaxForm_update" action="/comment/update" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="commentId" value="${comment.id?c}"/>

    <div>
        <label for="content">Content</label>
        <textarea name="content" id="${comment.id?c}_content_update" cols="40" rows="5" required>${comment.content}</textarea>
    </div>
</form>

<button id="${comment.id?c}_ajaxForm_cancel_update" onclick="showComment(1)" >Write</button>
<button id="${comment.id?c}_ajaxForm_submit_update" onclick="ajaxForm_update('${comment.id?c}')" >Send</button>

