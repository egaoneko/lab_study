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

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Study Read</title>
</head>
<body>
    <table>
        <tr>
            <td>Title</td>
            <td>${study.title}</td>
        </tr>
        <tr>
            <td>Author</td>
            <td>${study.user.getName()}</td>
        </tr>
        <tr>
            <td>Posting Date</td>
            <td>${study.getDifferentTime()}</td>
        </tr>

        <tr>
            <td>Category</td>
            <td>${study.category}</td>
        </tr>

        <tr>
            <td>Area</td>
            <td>${study.area}</td>
        </tr>

        <tr>
            <td>Charge</td>
            <td>${study.charge}</td>
        </tr>

        <tr>
            <td>On-Off Line</td>
            <td>${study.onOffLine}</td>
        </tr>

        <tr>
            <td>Way</td>
            <td>${study.way}</td>
        </tr>

        <tr>
            <td>Price</td>
            <td>${study.price?c}</td>
        </tr>

        <tr>
            <td>Participant</td>
            <td>${study.participant?c}</td>
        </tr>

        <tr>
            <td>Status</td>
            <td>${study.status}</td>
        </tr>

        <tr>
            <td>Content</td>
            <td>${study.content}</td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="button" onclick="location.href='/study/list'" value="List"/>
                <input type="button" onclick="location.href='/study/update/${study.id?c}'" value="Update"/>
                <form action="/study/delete" method="post" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="studyId" value="${study.id?c}"/>
                <input type="submit" value="Delete" >
                </form>

            </td>
        </tr>
    </table>

    <!-- For Reply -->
    <div id="reply"></div>

</body>
</html>