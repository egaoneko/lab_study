<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 6:53 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->


<div>
    <form method="POST" enctype="multipart/form-data" action="/upload">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        File to upload: <input type="file" name="file" id="file"><br />
        <input type="button" id="upload" value="Upload"> Press here to upload the file!
    </form>
</div>

<script type="text/javascript">
    $(function () {
        $('#upload').on("click", function(){
            var size = document.getElementById('file').files[0].size;
            var maxFileSize = ${myApp.maxFileSize?c};

            if (size > maxFileSize) {
                alert(size);
            }
            console.log(size);
        });
    });
</script>