package Entity.Driving;



import Entity.Route;

import java.io.Serializable;

public class Assignment implements Serializable {
    private Route route;
    private int routeNumber;

    public Assignment(Route route, int routeNumber) {
        this.route = route;
        this.routeNumber = routeNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "route=" + route +
                ", routeNumber=" + routeNumber +
                '}';
    }
}

