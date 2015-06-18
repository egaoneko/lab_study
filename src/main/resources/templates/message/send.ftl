<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 8:23 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="user" type="net.study.domain.User" -->

<div class="text-right">
    <button class="btn btn-info btn-raised btn-lg" onclick="messageBox()">Apply</button>
</div>

<div id="message_box" style="display: none">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Send message</h3>
        </div>
        <div class="panel-body">
            <form action="/message/send" id="message_form" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="receiverId" id="receiverId" value="${user.getId()}"/>
                <input type="hidden" name="messageStatus" id="messageStatus" value="JOIN"/>

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
</div>

<script src="/js/message.js"></script>