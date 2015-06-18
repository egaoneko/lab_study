<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="study" type="net.study.domain.Study" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->

<div class="panel panel-${study.status.color}">
    <div class="panel-heading">
        <h1>${study.title}</h1><h4 class="text-right">${study.getDifferentTime()}</h4>
    </div>
    <div class="panel-body">

        <table class="table table-striped table-hover hidden-xs hidden-sm">
            <colgroup>
                <col width="15%">
                <col width="15%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
            </colgroup>
            <thead>
                <tr>
                    <th class="text-center">Category</th>
                    <th class="text-center">Area</th>
                    <th class="text-center">Status</th>
                    <th class="text-center">Way</th>
                    <th class="text-center">On-Off Line</th>
                    <th class="text-center">Charge</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Participant</th>
                </tr>
            </thead>
            <tbody>
                <tr class="${study.status.color}">
                    <td class="text-center"><span class="label label-default">${study.category.title}</span></td>
                    <td class="text-center"><span class="label label-default">${study.area.title}</span></td>
                    <td class="text-center"><span class="label label-${study.status.color}">${study.status.title}</span></td>
                    <td class="text-center"><span class="label label-${study.way.color}">${study.way.title}</span></td>
                    <td class="text-center"><span class="label label-${study.onOffLine.color}">${study.onOffLine.title}</span></td>
                    <td class="text-center"><span class="label label-${study.charge.color}">${study.charge.title}</span></td>
                    <td class="text-center"><span class="label label-default">${study.price?c}</span></td>
                    <td class="text-center"><span class="label label-${study.status.color}">${study.participant?c}</span></td>
                </tr>
            </tbody>
        </table>

        <table class="table table-striped table-hover hidden-md hidden-lg">
            <tbody>
                <tr>
                    <th class="text-center">Category</th>
                    <td class="text-center"><span class="label label-default">${study.category.title}</span></td>
                </tr>
                <tr>
                    <th class="text-center">Area</th>
                    <td class="text-center"><span class="label label-default">${study.area.title}</span></td>
                </tr>
                <tr>
                    <th class="text-center">Status</th>
                    <td class="text-center"><span class="label label-${study.status.color}">${study.status.title}</span></td>
                </tr>
                <tr>
                    <th class="text-center">Way</th>
                    <td class="text-center"><span class="label label-${study.way.color}">${study.way.title}</span></td>
                </tr>
                <tr>
                    <th class="text-center">On-Off Line</th>
                    <td class="text-center"><span class="label label-${study.onOffLine.color}">${study.onOffLine.title}</span></td>
                </tr>
                <tr>
                    <th class="text-center">Charge</th>
                    <td class="text-center"><span class="label label-${study.charge.color}">${study.charge.title}</span></td>
                </tr>
                <tr>
                    <th class="text-center">Price</th>
                    <td class="text-center"><span class="label label-default">${study.price?c}</span></td>
                </tr>
                <tr>
                    <th class="text-center">Participant</th>
                    <td class="text-center"><span class="label label-${study.status.color}">${study.participant?c}</span></td>
                </tr>
            </tbody>
        </table>

        <table class="table table-striped table-hover">
            <caption>Book</caption>
            <colgroup>
                <col width="10%">
                <col width="30%">
                <col width="20%">
                <col width="15%">
                <col width="15%">
                <col width="10%">
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">Cover</th>
                    <th scope="col">Title</th>
                    <th scope="col">Author</th>
                    <th scope="col">Publisher</th>
                    <th scope="col">Publish Date</th>
                    <th scope="col">Price</th>
                </tr>
            </thead>
            <tbody id="list">
                <#if study.bookSet?has_content>
                    <#list study.bookSet as book>
                        <tr id="book_${book.id}">
                            <td><img src="${book.image}"  width="50px" height="70px" /></td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.publisher}</td>
                            <td>${book.pubdate}</td>
                            <td>${book.price}</td>
                        </tr>
                    </#list>
                <#else>
                    <tr class="__oldlist" style="">
                        <td colspan="6">Can not found.</td>
                    </tr>
                </#if>
            </tbody>
        </table>

        <hr>

        <h3>${study.content}</h3>

        <#include "../user/author.ftl">

        <#if study.checkUser(currentUser.user)>
            <div class="text-right">
                <input type="button" class="btn btn-info" onclick="location.href='/study/update/${study.id?c}'" value="Update"/>
                <form action="/study/delete" method="post" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="studyId" value="${study.id?c}"/>
                    <input type="submit" class="btn btn-danger" value="Delete" >
                </form>
            </div>
        </#if>
    </div>
</div>