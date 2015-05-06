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
                       /*     if (session.getAttribute("id") != null) {
                                out.print(db.getFullName((Integer) session.getAttribute("id")));
                            }*/
                            %>
                        </li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </div>
            </div>
            <div id="navigation">
                <ul>
                    <li><a href="." >HOME</a></li>
                    <li><a href="mybookings.jsp" class="last current">MY BOOKINGS</a></li>
                </ul>
            </div>
            <div id="loaded"></div> 
            <div id="maincontent">

            </div>

        </div>

        <div id="footerWrap">
            <div id="footer"></div>
        </div>
        <div id="message"></div>
    </body>
</html>
