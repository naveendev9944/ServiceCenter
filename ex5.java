import java.util.*;
import java.lang.Math;
class Car {
	int carno;
	Car(int carno){
		this.carno=carno;
	}
    	public void service() {
        	System.out.println("Car "+this.carno+"  Serviced Successfully");
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

    	public void createServiceLine(){
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
				try {
                	    		Thread.sleep(10000); 
                		} catch (InterruptedException e) {
                	    		e.printStackTrace();
                		}
            		} 
            		else {
                		try {
                	    		Thread.sleep(100); 
                		} catch (InterruptedException e) {
                	    		e.printStackTrace();
                		}
            		}
        	}
    	}
}

class ex5 {
    	public static void main(String[] args) {
        	// Create a service center
        	ServiceCenter servicecenter = new ServiceCenter();
		servicecenter.createServiceLine();
		int i;
        	for(int j=0;j<10;j++){
			i=(int)(Math.random()*10000);
        		Car car = new Car(i);
        	   	servicecenter.serviceCar(car);
        		System.out.println("Car " + i + " has arrived at the service center.");
        	}
    	}
}

