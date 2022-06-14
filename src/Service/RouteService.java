package Service;

import Entity.Route;
import Util.DBUtil.RouteDBUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    RouteDBUtil routeDBUtil = new RouteDBUtil();
    public static List<Route> routeList;

    public void addRoute(){
        System.out.println("Nhập vào số chuyến mới:");
        int routeNumber = -1;
        do {
            try {
                routeNumber = new Scanner(System.in).nextInt();
                if (routeNumber > 0) {
                    break;
                }
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            } catch (InputMismatchException e){
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            }
        } while (true);
        for (int i=0;i<routeNumber;i++){
            Route route = new Route();
            route.inputRoute();
            routeList.add(route);
            routeDBUtil.writeDataToDB(route);
        }
    }

    public void showRoute(){
        for (Route route : routeList) {
            System.out.println(route);
        }
    }

    public static Route findRouteById(int routeId) {
        for (Route route : routeList) {
            if (route.getRouteId() == routeId)
                return route;
        }
        return null;
    }

    public void initializeRouteData() {
        List<Route> routeList = routeDBUtil.readDataFromDB();
        if (routeList.size() > 0) {
            Route.autoId = routeList.get(routeList.size() - 1).getRouteId() + 1;
            this.routeList = routeList;
        } else {
            this.routeList = new ArrayList<>();
        }

    }
}
