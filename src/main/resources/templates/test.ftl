<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/11/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head">
        <title>Test</title>
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section>
            <a href="javascript:void(0)" class="btn btn-default">Default</a>
            <a href="javascript:void(0)" class="btn btn-primary">Primary</a>
            <a href="javascript:void(0)" class="btn btn-success">Success</a>
            <a href="javascript:void(0)" class="btn btn-info">Info</a>
            <a href="javascript:void(0)" class="btn btn-warning">Warning</a>
            <a href="javascript:void(0)" class="btn btn-danger">Danger</a>
            <a href="javascript:void(0)" class="btn btn-link">Link</a>
        </section>
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script">
    </@layout.put>
</@layout.extends>