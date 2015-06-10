<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/3/15 | 11:33 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="parent" type="net.study.domain.Comment" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->

<table style="border: 2px solid; margin: 30px 10px; width: 50%;">
    <tr>
        <td>
            <form name="${parent.id?c}_ajaxForm_reply" id="${parent.id?c}_ajaxForm_reply" action="/comment/reply" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="parentCommentId" value="${parent.id?c}"/>

                <div>
                    <label for="content">Content</label>
                    <textarea name="content" id="${parent.id}_content_reply" cols="40" rows="5" required></textarea>
                </div>
            </form>
            <button id="${parent.id?c}_ajaxForm_cancel_reply" onclick="toggleReply('${parent.id?c}')" >Cancel</button>
            <button id="${parent.id?c}_ajaxForm_submit_reply" onclick="ajaxForm_reply('${parent.id?c}')" >Send</button>
        </td>
    </tr>
</table>