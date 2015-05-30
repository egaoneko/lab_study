<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/29/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->
<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>

    <title>사용자 등록</title>

    <script type="text/javascript">

        function validateEmail(email) {
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        function isEmail(email) {
            $.ajax({
                type:"POST",
                url:"${rc.contextPath}/user/checkEmail.do",
                data:email,
                async:false,
                success:function(result){
                    if(result === "404"){
                        alert("Failed! Try again");
                        $("#login").focus();
                        $("#login").val('');
                        return false;
                    }else if(result === "400"){
                        alert("This email is used");
                        $("#login").focus();
                        $("#login").val('');
                        return false;
                    }else{
                        return true;
                    }
                }
            });
        }

        $(function(){
            $("#submit_form").on("submit",function(){

                var email = $("#email").val();

                if(email === ""){
                    $("#login").focus();
                    alert("Input Email");
                    return false;
                }

                if(!validateEmail(email)){
                    $("#login").focus();
                    $("#login").val('');
                    alert("Please write down available form of email");
                    return false;
                }

                if(isEmail(email)){
                    return false;
                }
            });
        });
    </script>

</head>
<body>
    <form action="" method="post" enctype="multipart/form-data" id="submit_form" name="submit_form">
        <input type="text" name="name" maxlength="100" required="required" placeholder="Your Name" autocomplete="off"><br>
        <input type="text" id="login" name="login" maxlength="250" required="required" placeholder="Email " autocomplete="off"><br>
        <input type="password" name="password" maxlength="250" required="required" placeholder="Password" autocomplete="off"><br>
        <button type="submit" value="submit">Submit</button>
    </form>
</body>
</html>
