
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
                $('#datepicker').datepicker({minDate: 0, dateFormat: 'yy-mm-dd'});
                $("#addmovies").on("submit", function(e) {
                    e.preventDefault();
                    var mname = $("#mname").val();
                    var date = $("#datepicker").val();
                    var poster = $("#poster").val();
                    
                    if (mname == "0") {
                        $("#merror").html("please enter the movie name");
                        return false;
                    } else {
                        $("#merror").hide();
                    }
                    if (date == "") {
                        $("#dateerror").html("please enter the date ");
                        return false;
                    } else {
                        $("#dateerror").hide();
                    }
                    if (poster == "") {
                        $("#perror").html("please choose the poster ");
                        return false;
                    } else {
                        $("#perror").hide();
                    }
                    var formData = new FormData($(this)[0]);
                    $.ajax({
                        url: $(this).attr("action"),
                        type: 'POST',
                        data: formData,
                        async: true,
                        beforeSend: function() {
                            $("#message1").show().html("Uploading...");
                        },
                        success: function(data) {
                            $("#message1").html(data);
           
                        },
                        cache: false,
                        contentType: false,
                        processData: false
                    });
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

