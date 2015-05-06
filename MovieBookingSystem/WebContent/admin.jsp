
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
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
                    <li><a href="admin.jsp" class="current">HOME</a></li>
                    <li><a href="addtheatres.jsp" class="last">ADD THEATRES</a></li>
                </ul>
            </div>
            <div id="maincontent">
                <h1>ADD MOVIES</h1>
                <form class="booking" id="addmovies" action="addmovies" method="post">
                    <table>
                        <tr>
                            <td>Movie Name</td>
                            <td><input type="text" name="mname" id="mname"/><span class="error1" id="merror"></span></td>
                        </tr>
                        <tr>
                            <td>Release Date</td>
                            <td><input type="text" name="date" id="datepicker"/><span class="error1" id="dateerror"></td>
                        </tr>
                        <tr>
                            <td>Choose poster</td>
                            <td><input type="file" name="file" id="poster"/><span class="error1" id="perror"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="ADD MOVIES" class="button_example"/></td>
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

