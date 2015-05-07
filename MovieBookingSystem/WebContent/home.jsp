
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="edu.ksu.cis.acad.dbutil.DatabaseConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%   
DatabaseConnect db = new DatabaseConnect();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
		<script src="cal/jquery-1.6.2.min.js"></script>
		<script src="cal/jquery-ui-1.8.15.custom.min.js"></script>
	
        <link rel="stylesheet" href="cal/jqueryCalendar.css">
        <script>
        	
        jQuery(function() {
            jQuery( "#datepicker" ).datepicker({ minDate: 0,maxDate: '+2W'});
		});
        
        $(document).ready(function(){
            $("#datepicker").change(function(){
            	$("#movie").load("movie?date=" + $(this).val()+"&theatre="+ $('#theatre').val());
            });
            
            $("#movie").change(function(){
            	var tid = $("#theatre").val();
                var mid = $("#movie").val();
                var date = $("#datepicker").val();
                $("#seatstatus").load("seatstatus?tid=" + tid + "&mid=" + mid + "&date=" + date);
            });
            
            $("#bookingform").submit(function() {
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
                    <li><a href="." class="current">HOME</a></li>
                    <li><a href="mybookings.jsp" class="last">MY BOOKINGS</a></li>
                </ul>
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
                                        ResultSet rs = db.result("SELECT * FROM THEATRE");
                                        while (rs.next()) {
                                            out.print("<option value='" + rs.getString(1) + "'>");
                                            out.print(rs.getString(2));
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
                            <td>SEATS</td>
                            <td><div id="seatstatus"></div><span class="error1" id="seaterror"></span></td>
                        </tr>
                        <tr>
                            <td>SEATS PREFERED</td>
                            <td>
                              <input type="text" name="date" id="sseat"/>
                                <span class="error1" id="movieerror"></span>
                            </td>
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
