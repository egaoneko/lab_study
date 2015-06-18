<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 10:34 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="user" type="net.study.domain.User" -->
<#-- @ftlvariable name="messageStudy" type="net.study.domain.Study" -->


<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">Apply for this study</h3>
    </div>
    <div class="panel-body">
        <form action="/message/send" id="message_form" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="receiverId" id="receiverId" value="${user.getId()}"/>
            <input type="hidden" name="messageStatus" id="messageStatus" value="JOIN"/>
            <input type="hidden" name="studyId" id="studyId" value="${messageStudy.id}"/>

            <fieldset>
                <div id="message_warn" class="form-group">
                    <div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1">
                        <textarea class="form-control" rows="5" name="message" id="message" placeholder="Leave your message" required></textarea>
                    </div>
                </div>
            </fieldset>

            <div class="col-lg-12 text-center">
                <input type="button" class="btn btn-default" onclick="messageBox()" value="Cancel">
                <input type="button" class="btn btn-info" onclick="messageAjax()" value="Send" >
            </div>

        </form>
    </div>
</div>

