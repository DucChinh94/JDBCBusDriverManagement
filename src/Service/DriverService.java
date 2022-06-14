package Service;

import Entity.Driver;
import Util.DBUtil.DriverDBUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriverService {

    DriverDBUtil driverDBUtil = new DriverDBUtil();
    public static List<Driver> driverList;

    public void addDriver(){
        System.out.println("Nhập số lái xe mới:");
        int driverNumber = -1;
        do {
            try {
                driverNumber = new Scanner(System.in).nextInt();
                if (driverNumber > 0) {
                    break;
                }
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            } catch (InputMismatchException e){
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            }
        } while (true);
        for (int i=0; i<driverNumber;i++){
            Driver driver = new Driver();
            driver.inputInfo();
            driverList.add(driver);
            driverDBUtil.writeDataToDB(driver);
        }
    }

    public void showDriver(){
        for (Driver driver : driverList) {
            System.out.println(driver);
        }
    }

    public static Driver findDriverById(int driverId) {
        for (Driver driver : driverList) {
            if (driver.getDriverId() == driverId)
                return driver;
        }
        return null;
    }

    public void initializeDriverData() {
        List<Driver> driverList = driverDBUtil.readDataFromDB();
        if (driverList.size() > 0) {
            Driver.autoId = driverList.get(driverList.size() - 1).getDriverId() + 1;
            this.driverList = driverList;
            System.out.println(driverList.size());
        } else {
            this.driverList = new ArrayList<>();
        }

    }
}
