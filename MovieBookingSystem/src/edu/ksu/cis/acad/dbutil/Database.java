package edu.ksu.cis.acad.dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Database {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/ticketbooking";
    //  Database credentials
    static final String USER = "booking";
    static final String PASS = "blackcaps";

    Connection conn = null;
    Statement stmt = null;

    //creating the database connection through the Constructor.
    public Database() throws SQLException, ClassNotFoundException {
        //STEP 2: Register JDBC driver
        Class.forName(JDBC_DRIVER);
        //STEP 3: Open a connection
        this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        this.stmt = conn.createStatement();
    }

    //Return the resultset of the query.
    public ResultSet result(String query) throws SQLException {
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public void insert(String tname, Map map) throws SQLException {
        String col = "";
        String value = "";
        int size = map.size();
        int i = 0;

        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            i++;
            Map.Entry<Integer, Integer> entry = entries.next();

            if (i == size) {
                col += entry.getKey();
            } else {
                col += entry.getKey() + ",";
            }

            if (entry.getValue() instanceof Integer) {
                if (i == size) {
                    value += entry.getValue();
                } else {
                    value += entry.getValue() + ", ";
                }
            } else {
                if (i == size) {
                    value += "'" + entry.getValue() + "'";
                } else {
                    value += "'" + entry.getValue() + "'" + ", ";
                }
            }
        }
        System.out.println("INSERT INTO " + tname + "(" + col + ")values(" + value + ")");
        stmt.executeUpdate("INSERT INTO " + tname + "(" + col + ")values(" + value + ")");
    }

    public void delete(String tname, int id) throws SQLException {
        String query = "delete from " + tname + " where id=" + id;
        System.out.println(query);
        stmt.executeUpdate(query);
    }

    public void delete(String tname) throws SQLException {
        stmt.executeUpdate("delete from " + tname);
    }

    public void update(String tname, Map map, int id) throws SQLException {
        String query = "";
        int size = map.size();
        int i = 0;
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            i++;
            Map.Entry<Integer, Integer> entry = entries.next();

            if (entry.getValue() instanceof Integer) {
                if (i == size) {
                    query += entry.getKey() + " = " + entry.getValue();
                } else {
                    query += entry.getKey() + " = " + entry.getValue() + ", ";
                }
            } else {
                if (i == size) {
                    query += entry.getKey() + " = '" + entry.getValue() + "'";
                } else {
                    query += entry.getKey() + " = '" + entry.getValue() + "'" + ", ";
                }
            }
        }
        System.out.println("Update " + tname + " set " + query + " where id=" + id);
        stmt.executeUpdate("Update " + tname + " set " + query + " where id=" + id);
    }

    public String getAvailability(int tid, int mid, String date, int showtime) throws SQLException {
        ResultSet rs = result("select seats from bookticket where tid=" + tid + " and mid=" + mid + " and booking_date='" + date + "' and showtime=" + showtime);
        String test = "";
        while (rs.next()) {
            test += rs.getString(1) + ",";
        }
        return test;
    }

    public String getMyAvailability(String seats, int tid, int mid, String date, int showtime) throws SQLException {
        ResultSet rs = result("select seats from bookticket where tid=" + tid + " and mid=" + mid + " and booking_date='" + date + "' and showtime=" + showtime);
        String test = "";
        while (rs.next()) {
            test += rs.getString(1) + ",";
        }
        String[] arr1 = test.split(",");

        List<String> list1 = new ArrayList();
        for (String s : arr1) {
            list1.add(s);
        }
        System.out.println(list1);
        String[] arr2 = seats.split(",");
        List<String> list2 = new ArrayList();
            for (String s : arr2) {
            list2.add(s);
        }
        System.out.println(list2);
        list1.removeAll(new HashSet(list2));
        System.out.println(list1);
        return test;
    }

    public int getId(String username) throws SQLException {
        ResultSet rs = result("select id from registration where username='" + username + "'");
        rs.next();
        return rs.getInt(1);
    }

    public String getFullName(int id) throws SQLException {
        ResultSet rs = result("select * from registration where id=" + id);
        rs.next();
        return rs.getString("firstname") + " " + rs.getString("lastname");
    }

    public String getMovie(int mid) throws SQLException {
        ResultSet rs = result("select * from movies where id=" + mid);
        rs.next();
        return rs.getString("mname");
    }

    public String getReleaseDate(int mid) throws SQLException {
        ResultSet rs = result("select * from movies where id=" + mid);
        rs.next();
        return rs.getString("release_date");
    }

    public String getPoster(int mid) throws SQLException {
        ResultSet rs = result("select * from movies where id=" + mid);
        rs.next();
        return rs.getString("poster");
    }

    public String getTheatre(int tid) throws SQLException {
        ResultSet rs = result("select * from theatre where id=" + tid);
        rs.next();
        return rs.getString("tname");
    }

    public void closeDb() throws SQLException {
        conn.close();
    }

}
