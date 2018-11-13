package firstPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public abstract class Car implements Comparable<Car> {
	
	/**
	 * StandardN will calculate automatically
	 */
	private String idCar;
	private int seatNum;
	private AreaUsed areaUsed;
	private int availableSeatNum;
	private List<Driver> owners = new ArrayList<>();
	private int currentDriver;
	private GPSLocation carLocation;
	private static List<Driver> nonAssignedDrivers = new ArrayList<>();
	private Integer distanceFromCustomer;
	
	/**
	 * from resource
	 * @return
	 */
	public String getIdCar() {
		return idCar;
	}
	public void setIdCar(String idCar) {
		this.idCar = idCar;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	
	}
	public int getAvailableSeatNum() {
		return availableSeatNum;
	}
	public void setAvailableSeatNum(int availableSeatNum) {
		this.availableSeatNum = availableSeatNum;
	}
	public AreaUsed getAreaUsed() {
		return areaUsed;
	}
	public void setAreaUsed(AreaUsed areaUsed) {
		this.areaUsed = areaUsed;
	}
	public GPSLocation getCarLocation() {
		return carLocation;
	}
	public void setCarLocation(GPSLocation carLocation) {
		this.carLocation = carLocation;
	}
	
	/**another way to construct
	 * public Car(AreaUsed areaUsed, List<Driver> listOfDriver) {
	 
		this.areaUsed = areaUsed;
		this.carLocation = LocationUtils.GetRandomLocation(areaUsed.getCenter(), areaUsed.getRadius());
		this.nonAssignedDrivers = listOfDriver;
	}
	*/
	
	public Car(AreaUsed areaUsed) {
		this.areaUsed = areaUsed;
		this.carLocation = LocationUtils.GetRandomLocation(areaUsed.getCenter(), areaUsed.getRadius());
	}
	
	
	public static List<Driver> getNonAssignedDrivers() {
		return nonAssignedDrivers;
	}
	public static void setNonAssignedDrivers(List<Driver> nonAssignedDrivers) {
		Car.nonAssignedDrivers = nonAssignedDrivers;
	}
	public void AssignDriver(List<Driver> listOfDriver) {
		//System.out.println(Car.nonAssignedDrivers.get(0).getOwnership());
		owners.add(Car.nonAssignedDrivers.get(0));
		Car.nonAssignedDrivers.remove(0);
		//System.out.println(Car.nonAssignedDrivers.get(0).getName());
		Iterator <Driver> iter = Car.nonAssignedDrivers.iterator();
		while (iter.hasNext()) {
			Driver item = iter.next();
			//System.out.println(item.getName());
			System.out.println(item.getOwnership());
			if (item.getOwnership() == false) {
				owners.add(item);
				System.out.println(owners.size());
				iter.remove();
			}
			else {
				System.out.println("Assignment completed");
				break;
			}
		}

		/**
		 * 
		 *
		for(Driver driver : nonAssignedDrivers) {
			if (driver.getOwnership() == false) {
				System.out.println(driver.getName());
				owners.add(driver);
				nonAssignedDrivers.remove(driver);
				System.out.println(Car.nonAssignedDrivers.get(0).getName());
			}
			else {
				System.out.println("assignment for this car is done");;
			}
		}
		*/
		
	}
	public Integer getDistanceFromCustomer() {
		return distanceFromCustomer;
	}
	public void setDistanceFromCustomer(int d) {
		this.distanceFromCustomer = d;
	}
	public void calculateDistance(Ride ride) {
		this.setDistanceFromCustomer(LocationUtils.GetDistance(this.carLocation, ride.getStartPosition()));
	}
	public int compareTo(Car arg0) {
        return this.getDistanceFromCustomer().compareTo(arg0.getDistanceFromCustomer());
    }
	public int getCurrentDriver() {
		return currentDriver;
	}
	public void setCurrentDriver(int currentDriver) {
		this.currentDriver = currentDriver;
	}




	

}
