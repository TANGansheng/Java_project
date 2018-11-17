package firstPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public abstract class Car implements Comparable<Car> {
	
	/**
	 * StandardN will calculate automatically
	 * currentDriverId we might want to modify afterwards
	 * areaUsed to show which area is this car driven
	 */
	private String idCar;
	private int seatNum;
	private AreaUsed areaUsed;
	private int availableSeatNum;
	private List<Driver> owners = new ArrayList<>();
	private int currentDriver;
	private Driver currentDirverObject;
	private GPSLocation carLocation;
	public static List<Driver> nonAssignedDrivers = new ArrayList<Driver>();
	private Integer distanceFromCustomer;
	
/**
 * getter and setter
 * @return
 */
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
	public List<Driver> getOwners() {
		return owners;
	}
	public void setOwners(List<Driver> owners) {
		this.owners = owners;
	}
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
	public Driver getCurrentDirverObject() {
		return currentDirverObject;
	}
	public void setCurrentDirverObject(Driver currentDirverObject) {
		this.currentDirverObject = currentDirverObject;
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
	public static List<Driver> getNonAssignedDrivers() {
		return nonAssignedDrivers;
	}
	public static void setNonAssignedDrivers(List<Driver> nonAssignedDrivers) {
		Car.nonAssignedDrivers = nonAssignedDrivers;
	}
	
	/**
	 * constructor
	 * other infomation is assigned when car is assigned driver or customer succeed find a car
	 * @param areaUsed
	 */
	public Car(AreaUsed areaUsed) {
		this.areaUsed = areaUsed;
		this.carLocation = LocationUtils.GetRandomLocation(areaUsed.getCenter(), areaUsed.getRadius());
	}
	
	
/**
 * assignDriver use to assign owner to a car when setting up myUber system
 * and we let someone be current driver and change its status to onduty
 */
	public void AssignDriver() {
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
				//System.out.printmjmjn jn mjn ln(owners.size());
				iter.remove();
			}
			else {
				//System.out.println("Assignment completed");
				break;
			}
		}
		this.currentDirverObject = this.RandomDriver();
		this.setCurrentDriver(this.currentDirverObject.getDriverId());
		this.currentDirverObject.setStatus("on-duty");
	}
	
	
	/**
	 * randomDriver is used to return one of car's owner
	 * @return
	 */
	public Driver RandomDriver() {
		Random random = new Random();
		int RandomNum = random.nextInt(this.owners.size());
		if (RandomNum <= 0) {
			RandomNum =0;
		}
		int driverIndex = (int)RandomNum;
		return this.owners.get(driverIndex);
	}
	
	/**
	 * changeDriver make car change current driver and set the current driver status to onduty
	 */
	public void changeDriver() {
		Driver currentD = RandomDriver();
		this.currentDriver = currentD.getDriverId();
		System.out.println(this.currentDriver);
		for (Driver driver : this.owners) {
			if (driver.getDriverId() == currentD.getDriverId()){
				driver.setStatus("on-duty");
			}
		}
		}

}
