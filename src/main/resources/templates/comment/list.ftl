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

<#if hasComment == false>
    <div style="border: 1px solid; width: 50%;">
        <table>
            <tr>
                <td>
                    Can not found comments.
                </td>
            </tr>
        </table>
    </div>
<#else>
    <#list commentList as list>

        <div id="${list.id?c}" style="border: 1px solid; margin-left: ${list.level * 30}px; width: 50%;">
            <table>
                <tr>
                    <td>Author</td>
                    <td>${list.user.getName()}</td>
                </tr>

                <tr>
                    <td>Posting Date</td>
                    <td>${list.getDifferentTime()}</td>
                </tr>

                <tr>
                    <td>Content</td>
                    <td>${list.content}</td>
                </tr>

                <tr>
                    <td colspan="2">
                        <#if currentUser??>
                            <#if list.level &lt; 1>
                                <button id="${list.id?c}_reply_btn" onclick="toggleReply('${list.id?c}', '${list.board.getId()?c}')" >Reply</button>
                            </#if>
                            <#if list.checkUser(currentUser.user)>
                                <button id="${list.id?c}_update_btn" onclick="updateComment('${list.id?c}')" >Update</button>
                                <button id="${list.id?c}_delete_btn" onclick="deleteComment('${list.id?c}', '${list.board.getId()?c}')" >Delete</button>
                            </#if>
                        </#if>
                    </td>
                </tr>
            </table>
            <div id="${list.id?c}_reply" style="display: none;"></div>
        </div>
        <br/>
    </#list>

    <#if commentList?has_content>
        <#-- Paging -->
        <tr>
            <td colspan="5">
                <#if paging.beginPage &gt; 10>
                    <button onclick="showComment(${(paging.beginPage-1)?c})">previous</button>
                </#if>
                <#list paging.beginPage..paging.endPage as pno>
                    <button onclick="showComment(${pno?c})">${pno?c}</button>
                </#list>
                <#if paging.endPage &lt; paging.totalPageCount>
                    <button onclick="showComment(${(paging.endPage+1)?c})">next</button>
                </#if>
            </td>
        </tr>
    </#if>
</#if>

<#if currentUser??>
    <div id="write" style="display: none;">
        <table style="border: 2px solid; margin: 30px 10px; width: 50%;">
            <tr>
                <td>
                    <form name="comment_ajaxForm" id="comment_ajaxForm" action="/comment/write" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="boardId" value="${boardId?c}">

                        <div>
                            <label for="content">Content</label>
                            <textarea name="content" id="content" cols="40" rows="5" required></textarea>
                        </div>
                    </form>
                    <button id="comment_ajaxForm_cancel" onclick="toggleWrite()" >Cancel</button>
                    <button id="comment_ajaxForm_submit" onclick="ajaxForm()" >Send</button>
                </td>
            </tr>
        </table>
    </div>

    <div id="write_btn">
        <button onclick="toggleWrite()">Write</button>
    </div>
</#if>
