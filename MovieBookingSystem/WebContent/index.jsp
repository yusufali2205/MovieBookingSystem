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
        <title>Movie booking - Sign Up</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body style="background:#323232">
        <div id="mainwrap">
            <div class="loginwrap">
                <div class="logo"></div>
                <div class="loginbutton"><a href="login.jsp">Login</a></div>
            </div>
            <div id="contentwrap">
                <div class="signupform">
                    <h1>SIGN UP</h1>
                    <form method="post" action="register" class="login">
                        <p>
                            <label for="firstname">Firstname:</label>
                            <input type="text" name="firstname" id="firstname" />
                            <span class="error" id="ferror"></span>
                        </p>
                        <p>
                            <label for="lastname">Lastname:</label>
                            <input type="text" name="lastname" id="lastname" />
                            <span class="error" id="lerror"></span>
                        </p>
                        <p>
                            <label for="username">Username:</label>
                            <input type="text" name="username" id="username" />
                            <span class="error" id="usererror"></span>
                        </p>
                        <p>
                            <label for="password">Password:</label>
                            <input type="password" name="password" id="password" />
                            <span class="error" id="perror"></span>
                        </p>
                        <p>
                            <label for="cpassword">CPassword:</label>
                            <input type="password" name="cpassword" id="cpassword" />
                            <span class="error" id="cperror"></span>
                        </p>
                        <p>
                            <label for="email">Email:</label>
                            <input type="text" name="email" id="email" />
                            <span class="error" id="merror"></span>
                        </p> 
                        <p>
                            <input type="submit" value="SignUp" class="login-button"/>
                        </p>
                        <p class="error" style="display: block">
                        <% 
                            if(session.getAttribute("message") != null){
                                out.print(session.getAttribute("message")); 
                            }
                            session.setAttribute("message",null);
                        %>
                    </p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
