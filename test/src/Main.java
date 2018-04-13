import java.io.EOFException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      String carType="BMW";
      CarFactory factory=new BMWFactory();
      Car car=factory.createCar();
      car.produceCar();
    }
}
