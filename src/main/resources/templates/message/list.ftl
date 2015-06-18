<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 11:46 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="messageList" type="java.util.List<net.study.domain.Message>" -->
<#-- @ftlvariable name="paging" type="net.study.util.Paging" -->
<#-- @ftlvariable name="hasMessage" type="java.lang.Boolean" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->
<#-- @ftlvariable name="byWhom" type="java.lang.String" -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
    <title>${myApp.name}/Message List</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
    <section>

        <br>

        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 panel panel-default">

            <ul class="nav nav-pills">
                <li class="<#if byWhom == "receiver">active</#if>"><a href="receive">Receive Messages</a></li>
                <li class="<#if byWhom == "sender">active</#if>"><a href="send">Send Messages</a></li>
            </ul>

            <table class="table table-striped table-hover">
                <colgroup>
                    <col width="40%">
                    <col width="25%">
                    <col width="20%">
                    <col width="15%">
                </colgroup>
                <thead>
                <tr>
                    <th class="text-center">Message</th>
                    <th class="text-center"><#if byWhom == "receiver">Sender<#else>Receiver</#if></th>
                    <th class="text-center"><#if byWhom == "receiver">Receive Date<#else>Send Date</#if></th>
                    <th class="text-center">Request</th>
                </tr>
                </thead>
                <tbody>
                    <#if hasMessage == false>
                    <tr>
                        <td colspan="4">
                            Can not found articles.
                        </td>
                    </tr>

                    <#else>
                        <#list messageList as list>
                        <tr class="<#if list.studyRequest.name() == "ACCEPT" || list.studyRequest.name() == "REJECT">${list.studyRequest.color}</#if>">
                            <td>
                                <a href="javascript:void(0)" onclick="load_page(${list.id?c})" data-toggle="modal" data-target="#read-dialog">
                                ${list.message}
                                </a>
                            </td>
                            <td class="text-center"><#if byWhom == "receiver">${list.sender.name}<#else>${list.receiver.name}</#if></td>
                            <td class="text-center"><#if byWhom == "receiver">${list.getReceiveDifferentTime()}<#else>${list.getSendDifferentTime()}</#if></td>
                            <td class="text-center"><span class="label label-${list.studyRequest.color}">${list.studyRequest.title}</span></td>
                        </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>

            <div class="text-center">
                <ul class="pagination">
                    <#if paging.beginPage &gt; 10>
                        <li><a href="?p=${(paging.beginPage - 1)?c}">«</a></li>
                    <#else>
                        <li class="disabled"><a href="javascript:void(0)">«</a></li>
                    </#if>

                    <#list paging.beginPage..paging.endPage as pno>
                        <#if paging.requestPage == pno>
                            <li class="active"><a href="?p=${pno?c}">${pno?c}</a></li>
                        <#else>
                            <li><a href="?p=${pno?c}">${pno?c}</a></li>
                        </#if>
                    </#list>

                    <#if paging.endPage &lt; paging.totalPageCount>
                        <li><a href="?p=${(paging.endPage + 1)?c}">»</a></li>
                    <#else>
                        <li class="disabled"><a href="javascript:void(0)">»</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </section>

    <section>
        <div>
            <div id="read-dialog" class="modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div id="read" class="modal-body"></div>
                    </div>
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
    <script type="text/javascript">

        contextPath = "${rc.getContextPath()}";

        function load_page(id) {

            // Loading 0.5 seconds
            setTimeout(function() {

                $('#read').load(contextPath + "/message/read/"+id)
            }, 500);
        }
    </script>
    </@layout.put>
</@layout.extends>