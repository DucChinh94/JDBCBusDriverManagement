package Entity.Driving;

import Entity.Driver;

import java.io.Serializable;
import java.util.List;

public class DrivingManagement implements Serializable {

    private Driver driver;
    private List<Assignment> assignmentList;

    public DrivingManagement(Driver driver, List<Assignment> assignmentList) {
        this.driver = driver;
        this.assignmentList = assignmentList;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    @Override
    public String toString() {
        return "DrivingManagement{" +
                "driver=" + driver +
                ", assignmentList=" + assignmentList +
                '}';
    }
}

