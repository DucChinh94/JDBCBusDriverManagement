package Util.DBUtil;

import DataTransferObject.AssignmentDto;
import Entity.Driving.Assignment;
import Entity.Driving.DrivingManagement;
import Service.DriverService;
import Service.RouteService;
import Util.file.DataReadable;
import Util.file.DataUpdateable;
import Util.file.DataWriteable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDBUtil implements DataWriteable<AssignmentDto>, DataReadable<DrivingManagement>, DataUpdateable<AssignmentDto> {

    private final static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final static String user = "BUSDATABASE";
    private final static String password = "M@isoan130219";

    DriverService driverService = new DriverService();
    RouteService routeService = new RouteService();

    @Override
    public List<DrivingManagement> readDataFromDB() {
        List<DrivingManagement> drivingManagementList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            String sql = "Select DRIVER_ID, BUS_LINE_ID, ROUND_TRIP_NUMBER from DRIVING_ASSIGNMENT";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int driverId = rs.getInt(1);
                int routeId = rs.getInt(2);
                int roundTripNumber = rs.getInt(3);

                boolean checkExits = false;
                Assignment assignment = new Assignment(routeService.findRouteById(routeId), roundTripNumber);
                for (DrivingManagement drivingManagement : drivingManagementList) {
                    if (drivingManagement.getDriver().getDriverId() == driverId) {
                        drivingManagement.getAssignmentList().add(assignment);
                        checkExits = true;
                        break;
                    }
                }
                if (!checkExits) {
                    List<Assignment> assignmentList = new ArrayList<>();
                    assignmentList.add(assignment);
                    drivingManagementList.add(new DrivingManagement(driverService.findDriverById(driverId), assignmentList));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return drivingManagementList;
    }

    @Override
    public void writeDataToDB(AssignmentDto assignmentDto) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO DRIVING_ASSIGNMENT (DRIVER_ID, BUS_LINE_ID, ROUND_TRIP_NUMBER) VALUES (?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, assignmentDto.getDriver().getDriverId());
            pstmt.setInt(2, assignmentDto.getRoute().getRouteId());
            pstmt.setInt(3, assignmentDto.getTurnNumber());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AssignmentDto assignmentDto) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE DRIVING_ASSIGNMENT SET ROUND_TRIP_NUMBER = ? WHERE DRIVER_ID = ? and BUS_LINE_ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, assignmentDto.getTurnNumber());
            pstmt.setInt(2, assignmentDto.getDriver().getDriverId());
            pstmt.setInt(3, assignmentDto.getRoute().getRouteId());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
