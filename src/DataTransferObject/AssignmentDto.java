package DataTransferObject;

import Entity.Driver;
import Entity.Route;

public class AssignmentDto {

    private Driver driver;
    private Route route;
    private int turnNumber;

    public AssignmentDto(Driver driver, Route route, int turnNumber) {
        this.driver = driver;
        this.route = route;
        this.turnNumber = turnNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Route getRoute() {
        return route;
    }

    public void setLine(Route route) {
        this.route = route;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }
}
