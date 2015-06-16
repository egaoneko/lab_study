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

<div class="panel panel-primary">
    <div class="panel-heading">
        <h1>${study.title}</h1><h4 class="text-right">${study.getDifferentTime()}</h4>
    </div>
    <div class="panel-body">

        <table class="table table-striped table-hover ">
            <tbody>
                <tr>
                    <th>Category</th>
                    <td><span class="label label-default">${study.category}</span></td>
                </tr>
                <tr>
                    <th>Area</th>
                    <td><span class="label label-default">${study.area}</span></td>
                </tr>
                <tr>
                    <th>Charge</th>
                    <td><span class="label label-default">${study.charge}</span></td>
                </tr>
                <tr>
                    <th>On-Off Line</th>
                    <td><span class="label label-default">${study.onOffLine}</span></td>
                </tr>
                <tr>
                    <th>Way</th>
                    <td><span class="label label-default">${study.way}</span></td>
                </tr>
                <tr>
                    <th>Price</th>
                    <td><span class="label label-default">${study.price?c}</span></td>
                </tr>
                <tr>
                    <th>Participant</th>
                    <td><span class="label label-default">${study.participant?c}</span></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><span class="label label-default">${study.status}</span></td>
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
