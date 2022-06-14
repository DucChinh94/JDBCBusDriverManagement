package Entity;

import java.io.Serializable;
import java.util.Scanner;

public class Driver extends Person implements Serializable {
    public static int autoId = 10000;
    private int driverId;
    private String level;

    public Driver(String fullName, String address, String phoneNumber, int id, String level) {
        super(fullName, address, phoneNumber);
        this.driverId = id;
        this.level = level;
    }

    public Driver() {}

    public int getDriverId() {
        return driverId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public void inputInfo() {
        this.driverId = this.autoId;
        this.autoId++;
        System.out.print("Nhap vao ten lái xe: ");
        this.setFullName(new Scanner(System.in).nextLine());
        System.out.print("Nhap vao dia chi lái xe: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.print("Nhap so dien thoai: ");
        this.setPhoneNumber(new Scanner(System.in).nextLine());
        inputLevel();
    }

    private void inputLevel(){
        System.out.println("Nhap trinh do lai xe: ");
        String leveDriver = "";
        do {
            leveDriver = new Scanner(System.in).nextLine();
            if (leveDriver.matches("[a-fA-F]")){
                level = leveDriver;
                break;
            }
            System.out.println("Trình đồ từ A - F, xin mời nhập lại!");
        } while (true);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", level='" + level + '\'' +
                '}';
    }
}
