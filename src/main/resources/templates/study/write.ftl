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
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->

<#import "/spring.ftl" as spring>

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>${myApp.name}/Study/Write</title>

        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section>
            <@spring.bind "form" />
            <#if spring.status.error>
                <div class="alert alert-dismissable alert-danger text-center">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <#list spring.status.errorMessages as error>
                        <p>${error}</p>

                        <#if error?contains("price")><#global errorPrice=true></#if>
                        <#if error?contains("participant")><#global errorParticipant=true></#if>
                    </#list>
                </div>
            </#if>
        </section>

        <section>

            <br>

            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 panel panel-default">
                <section>
                    <form role="form" action="" method="post" class="form-horizontal">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="userId" id="userId" value="${currentUser.getId()}"/>

                        <fieldset>
                            <legend class="text-center">Study Write</legend>

                            <div class="form-group">
                                <label for="title" class="col-lg-2 control-label">Title</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" name="title" id="title" placeholder="Title" size="100" value="${form.title}" required autofocus>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="category" class="col-lg-2 control-label">Category</label>
                                <div class="col-lg-10">
                                    <select class="form-control" name="category" id="category" required>
                                        <#list form.getCategoryList() as list>
                                            <option value="${list}" <#if form.category == list>selected</#if>>${list.title}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="area" class="col-lg-2 control-label">Area</label>
                                <div class="col-lg-10">
                                    <select class="form-control" name="area" id="area" required>
                                        <#list form.getAreaList() as list>
                                            <option value="${list}" <#if form.area == list>selected</#if>>${list.title}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label">Charge</label>
                                <div class="col-lg-10">
                                    <#list form.getChargeList() as list>
                                        <div class="radio radio-primary">
                                            <label>
                                                <input type="radio" name="charge" id="charge_${list_index}" value="${list}" <#if form.charge == list >checked</#if>/>
                                            ${list.title}
                                            </label>
                                        </div>
                                    </#list>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label">On-Off Line</label>
                                <div class="col-lg-10">
                                    <#list form.getOnOffLineList() as list>
                                        <div class="radio radio-primary">
                                            <label>
                                                <input type="radio" name="onOffLine" id="onOffLine_${list_index}" value="${list}" <#if form.onOffLine == list >checked</#if>/>
                                            ${list.title}
                                            </label>
                                        </div>
                                    </#list>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label">Way</label>
                                <div class="col-lg-10">
                                    <#list form.getWayList() as list>
                                        <div class="radio radio-primary">
                                            <label>
                                                <input type="radio" name="way" id="way_${list_index}" value="${list}" <#if form.way == list >checked</#if>/>
                                            ${list.title}
                                            </label>
                                        </div>
                                    </#list>
                                </div>
                            </div>

                            <div class="form-group <#if errorPrice??>has-error</#if>">
                                <label for="price" class="col-lg-2 control-label">Price</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" name="price" id="price" value="${form.price}" required>
                                </div>
                            </div>

                            <div class="form-group <#if errorParticipant??>has-error</#if>">
                                <label for="participant" class="col-lg-2 control-label">Participant</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" name="participant" id="participant" value="${form.participant}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label">Status</label>
                                <div class="col-lg-10">
                                    <#list form.getStatusList() as list>
                                        <#if list.name() != "EXCESS">
                                            <div class="radio radio-primary">
                                                <label>
                                                    <input type="radio" name="status" id="status_${list_index}" value="${list}" <#if form.status == list >checked</#if>/>
                                                    ${list.title}
                                                </label>
                                            </div>
                                        </#if>
                                    </#list>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-lg-2 control-label">Description</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control" rows="10" name="content" id="content" placeholder="Description" required></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-lg-2 control-label">Book</label>
                                <div class="col-lg-10">
                                    <input type="button" onclick="load_page()" class="btn btn-info btn-sm" data-toggle="modal" data-target="#book_search-dialog" value="Book Search"/>

                                    <br>
                                    <br>

                                    <div class="col-lg-12">
                                        <table class="table table-striped table-hover">
                                            <colgroup>
                                                <col width="10%">
                                                <col width="25%">
                                                <col width="15%">
                                                <col width="15%">
                                                <col width="15%">
                                                <col width="10%">
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
                                                    <th scope="col">Del</th>
                                                </tr>
                                            </thead>
                                            <tbody id="list_study_book">
                                            <tr class="__emptylist__study" style="<#if form.books?has_content>display: none</#if>">
                                                <td colspan="7">Books are not exist.</td>
                                            </tr>
                                            <tr id="book_#id#" class="__template__study" style="display: none">
                                                <td><img src="#image#"  width="50px" height="70px" /></td>
                                                <td>#title#</td>
                                                <td>#author#</td>
                                                <td>#publisher#</td>
                                                <td>#pubdate#</td>
                                                <td>#price#</td>
                                                <td><input type="button" class="btn btn-primary btn-sm" onclick="del_book('#id#')" value="Del"></td>
                                                <td style="display: none">#__id#</td>
                                            </tr>
                                            <#if form.books?has_content>
                                                <#list form.bookSet as book>
                                                    <tr id="book_${book.id}" class="__oldlist__study">
                                                        <td><img src="${book.image}"  width="50px" height="70px" /></td>
                                                        <td>${book.title}</td>
                                                        <td>${book.author}</td>
                                                        <td>${book.publisher}</td>
                                                        <td>${book.pubdate}</td>
                                                        <td>${book.price}</td>
                                                        <td><input type="button" class="btn btn-primary btn-sm" onclick="del_book('${book.id}')" value="Del"></td>
                                                        <td style="display: none"><input type="hidden" name="books" value="${book.id}"></td>
                                                    </tr>
                                                </#list>
                                            </#if>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-12 text-center">
                                    <input type="button" class="btn btn-danger" onclick="location.href='/study/list'" value="Cancel"/>
                                    <input type="submit" class="btn btn-success" value="Submit"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </section>
            </div>
        </section>

        <section>
            <div>
                <div id="book_search-dialog" class="modal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div id="book_search" class="modal-body"></div>
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

                    $('#book_search').load(contextPath + "/book/search")
                }, 500);
            }

            function del_book(id){
                if($('#list_study_book tr').length == 3){
                    $('.__emptylist__study').show();
                }

                $('#book_'+id).remove();
            }
        </script>
    </@layout.put>
</@layout.extends>