package edu.ksu.cis.acad.dbutil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.ksu.cis.acad.model.Movie;

public class Test {
    public static void main(String args[]) {
        try {
            Database db = new Database();
            Movie movie = new Movie();
            
            movie = db.selectMovie("M_999");
            System.out.println("Movie name: " + movie.getMname());
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
