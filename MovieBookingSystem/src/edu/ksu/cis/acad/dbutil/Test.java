package edu.ksu.cis.acad.dbutil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String args[]) {
        try {
            Database db = new Database();
            Map map = new HashMap();
            map.put("tid", 1);
            map.put("mid", 1);
            map.put("booking_date", "2013-12-06");
            map.put("showtime", 1);
            map.put("seats","A1");
            db.update("bookticket", map, 5);
            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
