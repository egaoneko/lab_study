<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="net.study.domain.form.StudyCreateForm" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->
<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Study Write</title>
</head>
<body>
<form action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="userId" id="userId" value="${currentUser.getId()}"/>

    <div>
        <label for="title">Title</label>
        <input type="text" name="title" id="title" size="100" value="${form.title}" required autofocus/>
    </div>

    <div>
        <label for="category">Category</label>
        <select name="category" id="category" required>
        <#list form.getCategoryList() as list>
            <option value="${list}" <#if form.category == list>selected</#if>>${list.title}</option>
        </#list>
        </select>
    </div>

    <div>
        <label for="area">Area</label>
        <select name="area" id="area" required>
        <#list form.getAreaList() as list>
            <option value="${list}" <#if form.area == list>selected</#if>>${list.title}</option>
        </#list>
        </select>
    </div>

    <div>
        <p>Charge</p>
    <#list form.getChargeList() as list>
        <input type="radio" name="charge" id="charge_${list_index}" value="${list}" <#if form.charge == list >checked</#if>/>
        <label for="charge_${list_index}">${list.title}</label>
    </#list>
    </div>

    <div>
        <p>On-Off Line</p>
    <#list form.getOnOffLineList() as list>
        <input type="radio" name="onOffLine" id="onOffLine_${list_index}" value="${list}" <#if form.onOffLine == list >checked</#if>/>
        <label for="onOffLine_${list_index}">${list.title}</label>
    </#list>
    </div>

    <div>
        <p>Way</p>
    <#list form.getWayList() as list>
        <input type="radio" name="way" id="way_${list_index}" value="${list}" <#if form.way == list >checked</#if>/>
        <label for="way_${list_index}">${list.title}</label>
    </#list>
    </div>

    <div>
        <label for="price">Price</label>
        <input type="text" name="price" id="price" value="${form.price}" required/>
    </div>

    <div>
        <label for="participant">Participant</label>
        <input type="text" name="participant" id="participant" value="${form.participant}" required/>
    </div>

    <div>
        <label for="content">Content</label>
        <textarea name="content" id="content" cols="40" rows="5" required>${form.content}</textarea>
    </div>

    <input type="submit" value="Send">
    <input type="button" onclick="location.href='/study/list'" value="Cancel"/>
</form>

<@spring.bind "form" />
<#if spring.status.error>
<ul>
    <#list spring.status.errorMessages as error>
        <li>${error}</li>
    </#list>
</ul>
</#if>
</body>
</html>