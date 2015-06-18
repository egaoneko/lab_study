<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="user" type="net.study.domain.User" -->
<#-- @ftlvariable name="ownStudyList" type="java.util.List<net.study.domain.Study>" -->
<#-- @ftlvariable name="partStudyList" type="java.util.List<net.study.domain.Study>" -->


<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/Profile</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
    <section>
        <div class="col-xs-12 col-sm-8 col-md-6 col-lg-6 col-sm-offset-2 col-md-offset-3 col-lg-offset-3">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">User Profile</h3>
                </div>
                <div class="panel-body">

                    <#include "author.ftl">

                    <hr>

                    <legend class="text-center">Own Study List</legend>

                    <table class="table table-striped table-hover">
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="50%">
                            <col width="15%">
                            <col width="15%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th class="text-center">Area</th>
                            <th class="text-center">Category</th>
                            <th class="text-center">Title</th>
                            <th class="text-center">Author</th>
                            <th class="text-center">Posting Date</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if ownStudyList?has_content == false>
                            <tr>
                                <td colspan="5">
                                    Can not found articles.
                                </td>
                            </tr>

                            <#else>
                                <#list ownStudyList as list>
                                <tr class="<#if list.status.name() == "EXCESS" || list.status.name() == "CLOSE">${list.status.color}</#if>">
                                    <td class="text-center">${list.area.title}</td>
                                    <td class="text-center">${list.category.title}</td>
                                    <td>
                                        <a href="javascript:void(0)" onclick="load_page(${list.id?c})" data-toggle="modal" data-target="#read-dialog">
                                        ${list.title}
                                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <span class="label label-${list.status.color}">${list.status.title}</span>
                                        <span class="label label-${list.way.color}">${list.way.title}</span>
                                        <span class="label label-${list.onOffLine.color}">${list.onOffLine.title}</span>
                                        <span class="label label-${list.charge.color}">${list.charge.title}</span>
                                    </td>
                                    <td class="text-center">${list.user.name}</td>
                                    <td class="text-center">${list.getDifferentTime()}</td>
                                </tr>
                                </#list>
                            </#if>
                        </tbody>
                    </table>

                    <hr>

                    <legend class="text-center">Part in Study List</legend>

                    <table class="table table-striped table-hover">
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="50%">
                            <col width="15%">
                            <col width="15%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th class="text-center">Area</th>
                            <th class="text-center">Category</th>
                            <th class="text-center">Title</th>
                            <th class="text-center">Author</th>
                            <th class="text-center">Posting Date</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if partStudyList?has_content == false>
                            <tr>
                                <td colspan="5">
                                    Can not found articles.
                                </td>
                            </tr>

                            <#else>
                                <#list partStudyList as list>
                                <tr class="<#if list.status.name() == "EXCESS" || list.status.name() == "CLOSE">${list.status.color}</#if>">
                                    <td class="text-center">${list.area.title}</td>
                                    <td class="text-center">${list.category.title}</td>
                                    <td>
                                        <a href="javascript:void(0)" onclick="load_page(${list.id?c})" data-toggle="modal" data-target="#read-dialog">
                                        ${list.title}
                                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <span class="label label-${list.status.color}">${list.status.title}</span>
                                        <span class="label label-${list.way.color}">${list.way.title}</span>
                                        <span class="label label-${list.onOffLine.color}">${list.onOffLine.title}</span>
                                        <span class="label label-${list.charge.color}">${list.charge.title}</span>
                                    </td>
                                    <td class="text-center">${list.user.name}</td>
                                    <td class="text-center">${list.getDifferentTime()}</td>
                                </tr>
                                </#list>
                            </#if>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>
    </section>
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script">
    </@layout.put>
</@layout.extends>