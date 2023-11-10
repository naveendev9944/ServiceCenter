import java.util.*;
import java.lang.Math;
class Car {
	int carno;
	Car(int carno){
		this.carno=carno;
	}
    	public void service(String threadno) {
        	System.out.println("\nCar "+this.carno+"  Serviced Successfully by Thread "+threadno);
    	}
}

class ParkingLot {
    	private Queue<Car> carQueue = new LinkedList<>();

    	public void parkCar(Car car) {
        	carQueue.add(car);
    	}

    	public synchronized Car unpark() {
            	try {
            		while(carQueue.size()==0)
            	        Thread.sleep(10000);
	
        		} catch (InterruptedException e) {
        			e.printStackTrace();
    			}
        	return carQueue.poll();
    	}
}

class ServiceCenter {
    	private ParkingLot parkingLot = new ParkingLot();

    	public void createServiceLine(byte n){
        	for (byte i = 0; i < n; i++) {
        	    ServiceLine serviceLine = new ServiceLine(parkingLot);
        	    serviceLine.start();
        	}
    	}
    

    	public void addCar(Car car) {
        	parkingLot.parkCar(car);
    	}
}

class ServiceLine extends Thread {
    	private ParkingLot parkingLot;
    	static byte no=1;
    	public ServiceLine(ParkingLot parkingLot) {
        	this.parkingLot = parkingLot;
    	}

    	@Override
    	public void run() {
    		this.setName(""+no++);
        	while (true) {
            		Car car = parkingLot.unpark();
            		
                	car.service(this.getName());
			try {
              	    		Thread.sleep(1000); 
               		} catch (InterruptedException e) {
               	    		e.printStackTrace();
               		}
            		 
            		
        	}
    	}
}
class CarService{
    	public static void main(String[] args){
        	Scanner sc =new Scanner(System.in);
        	ServiceCenter servicecenter = new ServiceCenter();
        	System.out.println("Enter the no of service line");
        	byte n=sc.nextByte();
		servicecenter.createServiceLine(n);
		int i;
		 for (int j = 1; j <= n; j++) {
            		i=(int)(Math.random()*10000);
        		Car car = new Car(i);
        	   	servicecenter.addCar(car);
        		System.out.println("\n\t\t\tCar " + i + " has arrived at the service center.");
        	}
		System.out.println("\n\t\t\t\tNOTE");
        	System.out.println("\n**************************************************************** Always press 1 to add a car **************************************************************\n");
        	System.out.println("*****************************************************************  Press 0 to exit  ***************************************************************\n\n");
		while(true){     
			i=(int)(Math.random()*10000);
        		Car car = new Car(i);
        	   	servicecenter.addCar(car);
        		System.out.println("\n\t\t\tCar " + i + " has arrived at the service center.");
        		
			i=sc.nextInt();
			if(i==0)
				System.exit(0);
			else if(i==1)
				continue;
			
			System.out.println("Wrong input Read the note carefully give 0 or 1");
        	
        	}
    	}
}

