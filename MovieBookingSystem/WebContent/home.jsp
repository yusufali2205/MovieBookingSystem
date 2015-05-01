
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="edu.ksu.cis.acad.dbutil.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="includes/remcaching.jsp" %>
<%    if (session.getAttribute("username") == null) {
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

                $("#maincontent").on("click", ".available", function() {
                    var id = $(this).attr("id");
                    $(this).toggleClass("selected");
                    if ($(this).attr("class") === "available") {
                        seats = $("#seats").val().replace(id + ",", "");
                    } else {
                        var seats = $("#seats").val();
                        seats += id + ",";
                    }
                    $("#seats").val(seats);
                });

                $("#datepicker").on("change", function() {
                    $("#movie").load("movie?date=" + $(this).val());
                });
                $("#movie").on("change", function() {
                    $("#showtime").val("0");
                    $("#seats").val("");
                });
                $("#theatre").on("change", function() {
                    $("#showtime").val("0");
                    $("#seats").val("");
                });
                $("#maincontent").on("change", "#showtime", function() {
                    var tid = $("#theatre").val();
                    var mid = $("#movie").val();
                    var date = $("#datepicker").val();
                    var showtime = $("#showtime").val();
                    $("#seatstatus").load("seatstatus?tid=" + tid + "&mid=" + mid + "&date=" + date + "&showtime=" + showtime);
                });

                $("#bookingform").on("submit", function(e) {
                    e.preventDefault();
                    var tid = $("#theatre").val();
                    var mid = $("#movie").val();
                    var date = $("#datepicker").val();
                    var showtime = $("#showtime").val();
                    var seats = $("#seats").val();
                    
                    if (tid == "0") {
                        $("#terror").html("please enter the theatre name");
                        return false;
                    } else {
                        $("#terror").hide();
                    }
                    if (date == "") {
                        $("#dateerror").html("please enter the date ");
                        return false;
                    } else {
                        $("#dateerror").hide();
                    }
                    if (mid == "0") {
                        $("#movieerror").html("please enter your theatre name");
                        return false;
                    } else {
                        $("#movieerror").hide();
                    }
                    if (showtime == "0") {
                        $("#sterror").html("please enter the show time");
                        return false;
                    } else {
                        $("#sterror").hide();
                    }
                    if (seats == "") {
                        $("#seaterror").html("please select your seats");
                        return false;
                    } else {
                        $("#seaterror").hide();
                    }

                    console.log(tid+mid+date+showtime+seats);
                    $.ajax({
                        url: $(this).attr("action"),
                        type:"POST",
                        data: { "tid": tid, "mid": mid, "date": date, "showtime": showtime, "seats": seats},
                        beforeSend: function() {
                            $("#message1").show().html("booking...").fadeOut(5000);
                        },
                        success: function(data) {
                            $("#message1").html(data);
                            $("#theatre").val("0");
                            $("#movie").val("0");
                            $("#datepicker").val("");
                            $("#showtime").val("0");
                            $("#seatstatus").html("");
                        }
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
                            if (session.getAttribute("id") != null) {
                                out.print(db.getFullName((Integer) session.getAttribute("id")));
                            }
                            %>
                        </li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </div>
            </div>
            <div id="navigation">
                <ul>
                    <li><a href="." class="current">HOME</a></li>
                    <li><a href="mybookings.jsp" class="last">MY BOOKINGS</a></li>
                </ul>
            </div>
            <div class="wrapper">
                <div class="jcarousel-wrapper">
                    <div class="jcarousel">
                        <ul>
                            <%
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date();
                                Calendar c = Calendar.getInstance();
                                c.setTime(date);
                                c.add(Calendar.DATE, 4);
                                date = c.getTime();
                                String today = dateFormat.format(date);
                                String query = "select * from movies where release_date > '" + today + "'";

                                ResultSet rs1 = db.result(query);
                                while (rs1.next()) {
                                    out.print("<li><img src='img/" + rs1.getString("poster") + "'></li>");
                                }
                            %>


                        </ul>
                    </div>

                    <a href="#" class="jcarousel-control-prev">&lsaquo;</a>
                    <a href="#" class="jcarousel-control-next">&rsaquo;</a>

                    <p class="jcarousel-pagination"></p>
                </div>
            </div>
            <div id="maincontent">
                <h1>BOOK MY TICKET</h1>
                <form class="booking" id="bookingform" action="book" method="post">
                    <input id="seats" type="hidden" name="seats" value=""/>
                    <table>
                        <tr>
                            <td>THEATRE</td>
                            <td>                    
                                <select name="theatre" id="theatre">
                                    <option value="0">--select--</option>
                                    <%
                                        ResultSet rs = db.result("select * from theatre");
                                        while (rs.next()) {
                                            out.print("<option value='" + rs.getString("id") + "'>");
                                            out.print(rs.getString("tname"));
                                            out.print("</option>");
                                        }
                                    %>                        
                                </select><span class="error1" id="terror"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>DATE</td>
                            <td><input type="text" name="date" id="datepicker"/><span class="error1" id="dateerror"></span></td>
                        </tr>
                        <tr>
                            <td>MOVIE NAME</td>
                            <td>
                                <select name="movie" id="movie">
                                    <option value="0">--select--</option>
                                </select>
                                <span class="error1" id="movieerror"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>SHOW TIME</td>
                            <td>                    
                                <select name="showtime" id="showtime">
                                    <option value="0" selected>--select--</option>
                                    <option value="1">first show</option>
                                    <option value="2">second show</option>
                                    <option value="3">third show</option>
                                    <option value="4">fourth show</option>
                                </select>
                                <span class="error1" id="sterror"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>SEATS</td>
                            <td><div id="seatstatus"></div><span class="error1" id="seaterror"></span></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input class="button_example" type="submit" value="BOOK MY TICKET"/></td>
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

