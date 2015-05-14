
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
     <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>

        <script type="text/javascript" src="js/jcarousel.js"></script>
        <script type="text/javascript" src="js/jcarousel.responsive.js"></script>
        
      <script>
      $(function() {
    	  $("#maincontent").on("submit", "#updatetheatre",function(e) {
              e.preventDefault();
              var theatre = $("#theatres").val();
              var shtime = $("#shtime").val();
              if (theatre == "") {
                  $("#terror").html("please enter the theatre name");
                  return false;
              } else {
                  $("#terror").hide();
              }
              $.ajax({
                  url: $(this).attr("action"),
                  type: 'POST',
                  data: {"theatre": theatre,"shtime":shtime},
                  beforeSend: function() {
                      $("#message1").show().html("adding theatre...");
                  },
                  success: function(data) {
                      $("#message1").html(data).fadeOut(5000);
                      $("#formcontent").load("addtheatre.jsp");
                      
                  }
              });
          });
    	  
    	  
    	  $("#delcontent").on("submit", "#deltheatre",function(e) {
              e.preventDefault();
              var theatre = $("#theatres").val();
              if (theatre == "") {
                  $("#terror").html("please enter the theatre name");
                  return false;
              } else {
                  $("#terror").hide();
              }
              $.ajax({
                  url: $(this).attr("action"),
                  type: 'POST',
                  data: {"theatre": theatre},
                  beforeSend: function() {
                      $("#message1").show().html("deleting theatre...");
                  },
                  success: function(data) {
                      $("#message1").html(data).fadeOut(5000);
                      $("#formcontent").load("addtheatre.jsp");
                    
                  }
              });
          });
    	  
    	  
    	  
    	  
      });
      
      </script>
        
    
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
                            <td>SHOW TIME</td>
                            <td><input id="shtime" type="text" name="shtime" value=""/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="ADD THEATRES" class="button_example"/></td>
                            <td></td>
                        </tr>
                    </table>
                   
                </form>
                
            </div>
            <div id="delcontent">
            <h1>DELETE THEATRES</h1>
                <form class="booking" id="deltheatres" action="deltheatre" method="post">
                    <table>
                        <tr>
                            <td>THEATRE NAME</td>
                            <td><input id="theatres" type="text" name="theatre" value=""/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="DELETE THEATRES" class="button_example"/></td>
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

