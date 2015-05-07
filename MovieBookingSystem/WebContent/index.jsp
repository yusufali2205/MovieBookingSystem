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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script>
            $(document).ready(function() {
            	$(".login").on("submit", function() {
                    if ($("#firstname").val() === "") {
                        $("#ferror").show().html("please enter your firstname");
                        return false;
                    }else{
                        $("#ferror").hide();
                    }
                    
                    if ($("#lastname").val() === "") {
                        $("#lerror").show().html("please enter your lastname");
                        return false;
                    }else{
                        $("#lerror").hide();
                    }
                    
                    if ($("#username").val() === "") {
                        $("#usererror").show().html("please enter your firstname");
                        return false;
                    }else{
                        $("#usererror").hide();
                    }
                    
                    if ($("#password").val() === "") {
                        $("#perror").show().html("please enter your firstname");
                        return false;
                    }
                    else{
                        $("#perror").hide();
                    }
                    
                    if ($("#cpassword").val() === "") {
                        $("#cperror").show().html("please enter your firstname");
                        return false;
                    }else{
                        $("#cperror").hide();
                    }
                    
                    if ($("#password").val() != $("#cpassword").val() ) {
                        $("#cperror").show().html("password doesnot match");
                        return false;
                    }else{
                        $("#cperror").hide();
                    }
                    
                    if ($("#email").val() === "") {
                        $("#merror").show().html("please enter your email");
                        return false;
                    }else{
                        $("#merror").hide();
                    }
                    
                    if(!/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/.test($("#email").val())){
                        $("#merror").show().html("please enter a valid email");
                        return false;
                    }
                });
            });
            
            </script>
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
