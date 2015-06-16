<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/15/15 | 3:36 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->
<#-- @ftlvariable name="rc" type="javax.servlet.http.HttpServletRequest" -->
     
<div>
    <fieldset>
        <legend class="text-center">Search Area</legend>
        <button class="btn btn-flat btn-primary btn-xs navbar-right" id="search" name="search" alt="search"><div class="icon-preview"><i class="mdi-action-search"></i></div></button>
        <input type="text" name="query" id="query" accesskey="s" title="search_word" class="navbar-form navbar-right form-control col-lg-4" onkeypress="search_enter(event);" placeholder="Input a search word." required autofocus>
    </fieldset>

    <table summary="Book Search Api Result" class="table table-striped table-hover">
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
            <th scope="col">Add</th>
        </tr>
        </thead>
        <tbody id="list">
        <tr class="__oldlist" style="">
            <td colspan="7">Can not found.</td>
        </tr>
        <tr class="__template" style="display: none">
            <td><img src="#image#"  width="50px" height="70px" /></td>
            <td>#title#</td>
            <td>#author#</td>
            <td>#publisher#</td>
            <td>#pubdate#</td>
            <td>#price#</td>
            <td><input type="button" class="btn btn-primary btn-sm" onclick="callAddAjax('#image#','#title#', '#author#', '#publisher#', '#pubdate#', '#price#', '#isbn#')" value="Add"></td>
        </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript">

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    contextPath = "${rc.getContextPath()}";

    $('#search').click( function() {
        if($('#query').val() == ''){
            alert('Input a search word.');
            $('#query').focus();
        }else{
            callAjax($('#query').val());
            $('#name').val("");
        }
    });

    function callAjax(query) {
        $.ajax({
            url: contextPath + "/book/query",
            dataType:'json',
            data:{'query':query},
            type:'POST',
            success:function(result){
                $('#result').html('');
                if(result['channel']['display'] > 0){
                    $('.__oldlist').remove();
                    for(var i in result['channel']['item']){
                        var item = result['channel']['item'][i];
                        $html = template('__template', item);
                        $html.addClass('__oldlist');
                        $('#list').append($html);
                        $('.__oldlist').show();
                    }
                }else{
                    $('#result').html('');
                    $('#result').append("Can not found search data or load.");
                }
            }
        });
    };

    function template(template_id, params){
        var c = $('.' + template_id).clone();
        var html = $('<div>').append(c).html();
        for(var key in params){
            html = html.replace(new RegExp('#' + key + '#', 'g'), params[key]);

            // For add study and block crash
            if(key == 'id'){
                html = html.replace(new RegExp('#__' + key + '#', 'g'), '<input type="hidden" name="books" value="' + params[key] + '">');
            }
        }

        html = html.replace(/#template_([^#]*)#/g, '$1');
        var $html = $(html).removeClass(template_id).removeClass('__template');
        return $html;
    }

    function search_enter(event){
        var keycode = event.keyCode || window.event.keyCode;
        if(keycode == 13) $("#search").click();
    }

    function callAddAjax(image, title, author, publisher, pubdate, price, isbn){
        $.ajax({
            url: contextPath + "/book/insert",
            dataType:'json',
            data:{
                'image':image,
                'title':title,
                'author':author,
                'publisher':publisher,
                'pubdate':pubdate,
                'price':price,
                'isbn':isbn
            },
            type:'POST',
            success:function(result){
                $('#result').html('');
                if(result != null){
                    if($('#list_study_book tr').length < 3){
                        $('.__emptylist__study').hide();
                    }
                    $html = template('__template__study', result);
                    $html.addClass('__oldlist__study');
                    $('#list_study_book').append($html);
                    $('.__oldlist__study').show();
                }else{
                    $('#result').html('');
                    $('#result').append("Can not found search data or load.");
                }
            }
        });
    }
</script>