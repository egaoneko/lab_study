<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->
<!DOCTYPE html>
<html>
<head>
    <title>글 읽기</title>
</head>
<body>
    <table>
        <tr>
            <td>제목</td>
            <td>${board.title}</td>
        </tr>
        <#--<tr>-->
            <#--<td>작성자</td>-->
            <#--<td>${board.writerName}</td>-->
        <#--</tr>-->
        <tr>
            <td>작성일</td>
            <td>${board.postingDate}</td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                ${board.content}
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="button" onclick="location.href='/article/list'" value="목록보기"/>
                <input type="button" onclick="location.href='/article/update/${board.id}'" value="수정하기"/>
                <form action="/article/delete" method="post" style="display: inline;">
                    <input type="hidden" name="boardId" value="${board.id}"/>
                <input type="submit" value="삭제하기" >
                </form>

            </td>
        </tr>
    </table>

</body>
</html>