import java.util.*;

class Car {
    public void service() {
        
    }
}

class ParkingLot {
    private Queue<Car> carQueue = new LinkedList<>();

    public void parkCar(Car car) {
        carQueue.add(car);
    }

    public Car unpark() {
        return carQueue.poll();
    }
}

class ServiceCenter {
    private ParkingLot parkingLot = new ParkingLot();

    public void createLine(){
        for (int i = 0; i < 3; i++) {
            ServiceLine serviceLine = new ServiceLine(parkingLot);
            serviceLine.start();
        }
    }
    

    public void serviceCar(Car car) {
        parkingLot.parkCar(car);
    }
}

class ServiceLine extends Thread {
    private ParkingLot parkingLot;

    public ServiceLine(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        while (true) {
            Car car = parkingLot.unpark();
            if (car != null) {
                car.service();
            } else {
                try {
                    Thread.sleep(1000); // Sleep for 1 second if parking lot is empty
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a service center
        ServiceCenter servicecenter = new ServiceCenter();
	servicecenter.createServiceLine();
        // Simulate cars arriving at the service center
        for (int i = 1; i <= 10; i++) {
            Car car = new Car();
            servicecenter.serviceCar(car);
            System.out.println("Car " + i + " has arrived at the service center.");
        }
    }
}

