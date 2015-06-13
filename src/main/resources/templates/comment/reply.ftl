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

<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-horizontal" name="${parent.id?c}_ajaxForm_reply" id="${parent.id?c}_ajaxForm_reply" action="/comment/reply" method="post">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="parentCommentId" value="${parent.id?c}"/>

            <br>

            <fieldset>
                <div id="comment_reply_warn" class="form-group">
                    <div class="col-lg-12">
                        <textarea class="form-control" rows="5" name="content" id="${parent.id}_content_reply" required></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-12 text-center">
                        <input type="button" id="${parent.id?c}_ajaxForm_cancel_reply" class="btn btn-danger btn-xs" onclick="toggleReply('${parent.id?c}')" value="Cancel"/>
                        <input type="button" id="${parent.id?c}_ajaxForm_submit_reply" class="btn btn-success btn-xs" onclick="ajaxForm_reply('${parent.id?c}')" value="Submit"/>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>