<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/11/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <@layout.block name="head">
        <meta charset="utf-8">

        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.4/css/bootstrap.min.css" />

        <!-- Material Design for Bootstrap -->
        <link href="/Material/css/roboto.min.css" rel="stylesheet">
        <link href="/Material/css/material-fullpalette.min.css" rel="stylesheet">
        <link href="/Material/css/ripples.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="/css/main.css" rel="stylesheet">
    </@layout.block>
</head>
<body>
    <@layout.block name="header">
    </@layout.block>

    <@layout.block name="contents">
    </@layout.block>

    <@layout.block name="footer">
    </@layout.block>

    <@layout.block name="script">
        <!-- jQuery -->
        <script src="/webjars/jquery/2.1.4/jquery.min.js"></script>

        <!-- Bootstrap Core JS -->
        <script src="/webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>

        <!-- Material Design for Bootstrap -->
        <script src="/Material/js/ripples.min.js"></script>
        <script src="/Material/js/material.min.js"></script>
        <script>
            $(document).ready(function() {
                // This command is used to initialize some elements and make them work properly
                $.material.init();
            });
        </script>
    </@layout.block>
</body>
</html>