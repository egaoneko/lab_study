<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 5:35 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->

<br><br>

<div class="panel panel-default">
    <div class="panel-heading"><h3>Author</h3></div>
    <div class="panel-body">
        <div class="list-group">
            <div class="list-group-item">
                <div class="row-picture">
                    <img class="circle" src="<#if currentUser.assets??>${currentUser.assets.realPath}<#else>/img/user.png</#if>" alt="icon">
                </div>
                <div class="row-content">
                    <h4 class="list-group-item-heading">${currentUser.name}</h4>
                    <p class="list-group-item-text">
                    <#switch currentUser.contact.contactType.name()>
                        <#case "EMAIL">
                            <button type="button" class="btn btn-default btn-sm" id="contact_tooltip" data-container="body" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="${currentUser.contact.content}"></button>
                            <#break>
                        <#case "PHONE">
                            <button type="button" class="btn btn-default btn-sm" id="contact_tooltip" data-container="body" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="${currentUser.contact.content}"><div class="icon-preview"><i class="mdi-communication-call"></i></div></button>
                            <#break>
                        <#case "KAKAO">
                            <button type="button" class="btn btn-default btn-sm" id="contact_tooltip" data-container="body" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="${currentUser.contact.content}"><div class="icon-preview"><i class="mdi-communication-messanger"></i></div></button>
                            <#break>
                    </#switch>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="/webjars/jquery/2.1.4/jquery.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="/webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('#contact_tooltip').tooltip();
    });
</script>