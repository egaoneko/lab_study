<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/3/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="commentList" type="java.util.List<net.study.domain.Comment>" -->
<#-- @ftlvariable name="paging" type="net.study.util.Paging" -->
<#-- @ftlvariable name="hasComment" type="java.lang.Boolean" -->
<#-- @ftlvariable name="boardId" type="java.lang.Integer" -->
<#-- @ftlvariable name="currentUser" type="net.study.domain.CurrentUser" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->

<div class="list-group" xmlns="http://www.w3.org/1999/html">
<#if hasComment == false>
    <div class="list-group-item">
        <div class="row-content">
            <h4 class="list-group-item-heading">Can not found comments.</h4>
        </div>
    </div>
    <hr>
<#else>
    <#list commentList as list>

        <div id="${list.id?c}" class="list-group-item" style="margin-left: ${list.level * 30}px;">
            <div class="row-picture">
                <img class="circle" src="<#if currentUser.assets??>${currentUser.assets.realPath}<#else>/img/user.png</#if>" alt="icon">
            </div>
            <div class="row-content">
                <h4 class="list-group-item-heading">${list.user.name}<small>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;${list.getDifferentTime()}</small></h4>
                <p class="list-group-item-text">${list.content}</p>
                <div class="text-right">
                    <#if currentUser??>
                        <#if list.level &lt; 1>
                            <button id="${list.id?c}_reply_btn" class="btn btn-success btn-xs" onclick="toggleReply('${list.id?c}', '${list.board.getId()?c}')" >Reply</button>
                        </#if>
                        <#if list.checkUser(currentUser.user)>
                            <button id="${list.id?c}_update_btn" class="btn btn-info btn-xs" onclick="updateComment('${list.id?c}')" >Update</button>
                            <button id="${list.id?c}_delete_btn" class="btn btn-danger btn-xs" onclick="deleteComment('${list.id?c}', '${list.board.getId()?c}')" >Delete</button>
                        </#if>
                    </#if>
                </div>
            </div>
        </div>

        <div id="${list.id?c}_reply" style="display: none;"></div>

        <div class="list-group-separator"></div>
    </#list>

    <#if commentList?has_content>
    <#-- Paging -->
        <div class="text-center">
            <ul class="pagination pagination-sm">
                <#if paging.beginPage &gt; 10>
                    <li><a href="javascript:void(0)" onclick="showComment(${(paging.beginPage-1)?c})">«</a></li>
                <#else>
                    <li class="disabled"><a href="javascript:void(0)">«</a></li>
                </#if>

                <#list paging.beginPage..paging.endPage as pno>
                    <#if paging.requestPage == pno>
                        <li class="active"><a href="javascript:void(0)" onclick="showComment(${pno?c})">${pno?c}</a></li>
                    <#else>
                        <li><a href="javascript:void(0)" onclick="showComment(${pno?c})">${pno?c}</a></li>
                    </#if>
                </#list>

                <#if paging.endPage &lt; paging.totalPageCount>
                    <li><a href="javascript:void(0)" onclick="showComment(${(paging.endPage+1)?c})">»</a></li>
                <#else>
                    <li class="disabled"><a href="javascript:void(0)">»</a></li>
                </#if>
            </ul>
        </div>
    </#if>
</#if>

<#if currentUser??>
    <div id="write_btn" class="text-center">
        <button class="btn btn-primary btn-xs" onclick="toggleWrite()">Write</button>
    </div>

    <div id="write" style="display: none;">
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-horizontal" name="comment_ajaxForm" id="comment_ajaxForm" action="/comment/write" method="post">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="boardId" value="${boardId?c}">

                    <br>

                    <fieldset>
                        <div id="comment_write_warn" class="form-group">
                            <div class="col-lg-12">
                                <textarea class="form-control" rows="5" name="content" id="content" required></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-12 text-center">
                                <input type="button" id="comment_ajaxForm_cancel" class="btn btn-danger btn-xs" onclick="toggleWrite()" value="Cancel"/>
                                <input type="button" id="comment_ajaxForm_submit" class="btn btn-success btn-xs" onclick="ajaxForm()" value="Submit"/>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</#if>
</div>
