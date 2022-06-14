package Util.DBUtil;

import Entity.Route;
import Util.file.DataReadable;
import Util.file.DataWriteable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDBUtil implements DataReadable<Route>, DataWriteable<Route>{

    private final static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final static String user = "BUSDATABASE";
    private final static String password = "M@isoan130219";

    @Override
    public List<Route> readDataFromDB() {
        List<Route> routeList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            String sql = "Select ID, DISTANCE, BUS_STOP_NUMBER from BUS_LINE ORDER BY ID";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int distance = rs.getInt(2);
                int stopPoint = rs.getInt(3);
                Route line = new Route(id, distance, stopPoint);
                routeList.add(line);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return routeList;
    }

    @Override
    public void writeDataToDB(Route route) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO BUS_LINE ( ID, DISTANCE, BUS_STOP_NUMBER) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, route.getRouteId());
            pstmt.setFloat(2, route.getDistance());
            pstmt.setInt(3, route.getStopNumber());
            pstmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
