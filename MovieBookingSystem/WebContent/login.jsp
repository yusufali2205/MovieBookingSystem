<%@ include file="includes/remcaching.jsp" %>
<%    
    if (session.getAttribute("username") != null) {
        response.sendRedirect("home.jsp");
    }
    if (session.getAttribute("admin") != null) {
        response.sendRedirect("admin.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>cinema booking - Login</title>
        <link rel="stylesheet" href="css/login.css">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script>
            jQuery.fn.center = function() {
                this.css("position", "absolute");
                this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) +
                        $(window).scrollTop()) + "px");
                this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) +
                        $(window).scrollLeft()) + "px");
                return this;
            }
            $(document).ready(function() {
                $(".loginform").center();

                $(window).resize(function() {
                    $(".loginform").center();
                });

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
        <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
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
