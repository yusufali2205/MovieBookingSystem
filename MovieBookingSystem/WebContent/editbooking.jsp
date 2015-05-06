<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<h1>UPDATE MY SEATS</h1>
<form class="booking" id="updatebooking" action="updatebooking" method="post">
    <input id="seats" type="hidden" name="seats" value=""/>
    <input id="itemid" type="hidden" name="id" value=""/>
    <table>
        <tr>
            <td>THEATRE NAME</td>
            <td>    
                <select name="theatre" id="theatre" disabled>
                    <option value="">select</option>                     
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
</script>