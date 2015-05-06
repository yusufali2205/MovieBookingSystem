
<%@page import="java.util.ArrayList"%>
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
        
        <script src="cal/jquery-1.6.2.min.js"></script>
		<script src="cal/jquery-ui-1.8.15.custom.min.js"></script>
		<link rel="stylesheet" href="cal/jqueryCalendar.css">
		<script>
                jQuery(function() {
                                jQuery( "#datepicker" ).datepicker({ minDate: 0,maxDate: '+2W'});
                });
                
                $(document).ready(function() {
                    $('#datepicker').on('change', function() {
                      alert( this.value ); // or $(this).val()
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
                         /*   if (session.getAttribute("id") != null) {
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
                    <li><a href="." class="current">HOME</a></li>
                    <li><a href="mybookings.jsp" class="last">MY BOOKINGS</a></li>
                </ul>
            </div>
            <div class="wrapper">
                
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
                                     <option value="0">--select--</option>
                       	 			 <%
           								 ArrayList<edu.ksu.cis.acad.model.Theatre> theartres = (ArrayList<edu.ksu.cis.acad.model.Theatre>)request.getAttribute("theatres");
           								 if(theartres!=null){   
          								  for (edu.ksu.cis.acad.model.Theatre theartre : theartres) { 
                 								  out.println("<option>"+theartre.getTheatre_name()+"</option>");
          								     }
         									    }else{
             									    System.out.print("theatre is null");
           								  }
            						 %>                      
                                </select><span class="error1" id="terror"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>DATE</td>
                            <td><input type="text" name="date" id="datepicker"/>
                            <span class="error1" id="dateerror"></span></td>
                        </tr>
                        <tr>
                            <td>MOVIE NAME</td>
                            <td>
                                <select name="movie" id="movie">
                                    <%
           								 ArrayList<String> movies = (ArrayList<String>)request.getAttribute("movies");
           								 if(movies!=null){   
          								  for (String movie : movies) { 
                 								  out.println("<option>"+movie+"</option>");
          								     }
         									    }else{
             									    System.out.print("states is null");
           								  }
            						 %>
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
        </div>
    </body>
</html>

