<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="edu.cis.ksu.acad.dbutil.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    if (session.getAttribute("username") == null) {
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
                $("#message").center();
                $("#maincontent").load("mybookings");

                $("#mainWrapper").on("click", ".available", function() {
                    var id = $(this).attr("id");
                    $(this).removeClass("available");
                    $(this).toggleClass("selected");
                    if ($(this).attr("class") === "available") {
                        seats = $("#seats").val().replace(id + ",", "");
                    } else {
                        var seats = $("#seats").val();
                        seats += id + ",";
                    }
                    $("#seats").val(seats);
                });

                $("#mainWrapper").on("click", ".selected", function() {
                    var id = $(this).attr("id");
                    $(this).addClass("available");
                    $(this).toggleClass("selected");
                    if ($(this).attr("class") === "available") {
                        seats = $("#seats").val().replace(id + ",", "");
                    } else {
                        var seats = $("#seats").val();
                        seats += id + ",";
                    }
                    $("#seats").val(seats);
                });


                $("#mainWrapper").on("change", "#movie", function() {
                    $("#showtime").val("none");
                });
                $("#mainWrapper").on("change", "#theatre", function() {
                    $("#showtime").val("none");
                });
            
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
                            if (session.getAttribute("id") != null) {
                                out.print(session.getAttribute("id"));
                            }
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
                     <li><a href="recommend.jsp" class="last">RECOMMENDATIONS</a></li>
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
