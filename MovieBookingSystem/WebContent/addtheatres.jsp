
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.cinema.dbutil.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="includes/remcaching.jsp" %>
<%    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
    }
    Database db = new Database();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>

        <script type="text/javascript" src="js/jcarousel.js"></script>
        <script type="text/javascript" src="js/jcarousel.responsive.js"></script>
        <script>
            jQuery.fn.center = function() {
                this.css("position", "fixed");
                this.css("top","0px");
                this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) +
                        $(window).scrollLeft()) + "px");
                return this;
            }
            $(function() {
                $("#message1").center();
                

            });
        </script>
    </head>
    <body>
        <div id="mainWrapper">
            <div id="header">
                <div class="logo"></div>
                <div class="welcome">
                    <ul>
                        <li><strong>Welcome</strong>, <%
                                out.print("Admin");
                            %>
                        </li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </div>
            </div>
            <div id="navigation">
                <ul>
                    <li><a href="admin.jsp">HOME</a></li>
                    <li><a href="addtheatres.jsp" class="current last">ADD THEATRES</a></li>
                </ul>
            </div>
            <div id="maincontent">
                <h1>ADD THEATRES</h1>
                <form class="booking" id="addtheatres" action="addtheatre" method="post">
                    <table>
                        <tr>
                            <td>THEATRE NAME</td>
                            <td><input id="theatres" type="text" name="theatre" value=""/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="ADD THEATRES" class="button_example"/></td>
                            <td></td>
                        </tr>
                    </table>
                   
                </form>
            </div>
        </div>
        <div id="footerWrap">
            <div id="footer"></div>
        </div>
        <div id="message1"></div>
    </body>
</html>

