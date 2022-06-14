package Entity;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Route implements Serializable {
    public static int autoId = 100;
    private int routeId;
    private float distance;
    private int stopNumber;

    public Route(int routeId, float distance, int stopNumber) {
        this.routeId = routeId;
        this.distance = distance;
        this.stopNumber = stopNumber;
    }

    public Route() {}

    public int getRouteId() {
        return routeId;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    public void inputRoute(){
        this.routeId = autoId;
        autoId++;
        inputDistance();
        inputStopNumber();
    }

    private void inputDistance(){
        System.out.println("nhap vao khoang cach:");
        distance = -1.0f;
        do {
            try {
                distance = new Scanner(System.in).nextFloat();
                if (distance > 0){
                    break;
                }
                System.out.println("Khoảng cách là số thực lớn hơn 0, xin moi nhap lai!");
            } catch (InputMismatchException e){
                System.out.println("Khoảng cách là số thực lớn hơn 0, xin moi nhap lai!");
            }
        } while (true);
    }

    private void inputStopNumber(){
        System.out.println("Nhập số điểm dừng:");
        stopNumber = -1;
        do {
            try {
                stopNumber = new Scanner(System.in).nextInt();
                if (stopNumber > 0){
                    break;
                }
                System.out.println("Số điểm dừng là số nguyên dương, xin mời nhập lại");
            } catch (InputMismatchException ex){
                System.out.println("Số điểm dừng là số nguyên dương, xin mời nhập lại");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", distance=" + distance +
                ", stopNumber=" + stopNumber +
                '}';
    }
}
