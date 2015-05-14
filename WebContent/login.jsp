<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Movie booking - Login</title>
        <link rel="stylesheet" href="css/login.css">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script>
            $(document).ready(function() {
                $(".login").on("submit", function() {
                    if ($("#username").val() === "") {
                        $(".error").show();
                        $(".error").html("please enter your username");
                        return false;
                    }
                    if ($("#password").val() === "") {
                        $(".error").show();
                        $(".error").html("please enter your password");
                        return false;
                    }
                });
            });

        </script>
    </head>
    <body style="background:#323232">
        <div id="mainwrap">
            <div class="loginwrap">
                <div class="logo"></div>
                <div class="loginbutton"><a href=".">Sign Up</a></div>
            </div>
            <div class="signupform">
                <h1>Login form</h1>
                <form method="post" action="login" class="login">
                    <p>
                        <label for="username">Username:</label>
                        <input type="text" name="username" id="username" />
                    </p>

                    <p>
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" />
                    </p>

                    <p>
                        <input type="submit" value="Login" class="login-button"/>
                    </p>
                    <p class="error" style="display: block">
                        <%
                            if (session.getAttribute("message") != null) {
                                out.print(session.getAttribute("message"));
                            }
                            session.setAttribute("message", null);
                        %>
                    </p>
                </form>
            </div>
        </div>                 
    </body>
</html>
