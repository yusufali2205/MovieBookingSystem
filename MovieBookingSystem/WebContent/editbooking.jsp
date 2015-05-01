<%@page import="java.util.Date"%>
<%@page import="edu.ksu.cis.acad.dbutil.Database"%>
<%@page import="java.sql.ResultSet"%>
<%
    Database db1 = new Database();
    int id = Integer.parseInt(request.getParameter("id"));
    ResultSet rs1 = db1.result("select * from bookticket where id=" + id);
    rs1.next();
    int mid = rs1.getInt("mid");
    int tid = rs1.getInt("tid");
    String booking_date = rs1.getDate("booking_date").toString();
    int showtime = rs1.getInt("showtime");
    String seats = rs1.getString("seats");
%>
<h1>UPDATE MY SEATS</h1>
<form class="booking" id="updatebooking" action="updatebooking" method="post">
    <input id="seats" type="hidden" name="seats" value=""/>
    <input id="itemid" type="hidden" name="id" value="<%=id%>"/>
    <table>
        <tr>
            <td>THEATRE NAME</td>
            <td>    
                <select name="theatre" id="theatre" disabled>
                    <option value="">select</option>
                    <%
                        Database db = new Database();
                        ResultSet rs = db.result("select * from theatre");
                        while (rs.next()) {
                            out.print("<option value='" + rs.getString("id") + "'>");
                            out.print(rs.getString("tname"));
                            out.print("</option>");
                        }
                    %>                        
                </select>
            </td>
        </tr>
        <tr>
            <td>DATE</td>
            <td><input type="text" name="date" id="datepicker" disabled></td>
        </tr>
        <tr>
            <td>MOVIE NAME</td>
            <td>
                <select name="movie" id="movie" disabled>
                    <%
                        ResultSet rs2 = db.result("select * from movies where release_date <= '" + booking_date + "'");
                        out.print("<option value=''>select</option>");
                        while (rs2.next()) {
                            out.print("<option value='" + rs2.getString("id") + "'>");
                            out.print(rs2.getString("mname"));
                            out.print("</option>");
                        }
                    %>        
                </select>
            </td>
        </tr>
        <tr>
            <td>SHOWTIME</td>
            <td>   
                <select name="showtime" id="showtime" disabled>
                    <option value="none">select</option>
                    <option value="1">first show</option>
                    <option value="2">second show</option>
                    <option value="3">third show</option>
                    <option value="4">fourth show</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>SEAT STATUS</td>
            <td><div id="seatstatus"></div></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="button_example" value="UPDATE MY SEATS"/></td>
        </tr>
    </table>
</form>
<script>

    $(function() {
        document.getElementById("movie").value =<%= mid%>;
        document.getElementById("datepicker").value = "<%= booking_date%>";
        document.getElementById("theatre").value =<%= tid%>;
        document.getElementById("showtime").value =<%= showtime%>;
        document.getElementById("seats").value = "<%= seats%>";

        $('#datepicker').datepicker({minDate: 0, dateFormat: 'yy-mm-dd'});

        var tid = $("#theatre").val();
        var mid = $("#movie").val();
        var date = $("#datepicker").val();
        var showtime = $("#showtime").val();
        var seats = $("#seats").val();
        var url = "currentstatus?tid=" + tid + "&mid=" + mid + "&date=" + date + "&showtime=" + showtime + "&seats=" + seats;
        $("#updatebooking").on("submit",function(){
            if($("#seats").val() == ""){
                alert("please select min one seat or cancel your booking! thanks!");
                return false;
            }
        });
    
        $("#seatstatus").load(url);
    });
</script>