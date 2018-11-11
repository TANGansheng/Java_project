package firstPart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideUberVan extends Ride {
	private static Map<String, Double> trafficRateMap = new HashMap<String, Double>() {
		{put("lowTraffic", (double) 1);
		put("mediumTraffic",(double)1.5);
		put("heavyTraffic",(double)1.8);}};
	private static Map<String, Double> lengthTypeMap = new HashMap<String, Double>(){
			{
				put("LessThanFive",6.2);
				put("FiveToTen",7.7);
				put("TenToTwenty",3.25);
				put("MoreThanTwenty",2.6);
			}
		};
	public RideUberVan(Customer customer, int passengerNum, GPSLocation startPosition, GPSLocation endPosition, MyTime startTime) {
		super(customer, passengerNum, startPosition,endPosition,startTime);
		//this.setPricePerKmLessThanFive(6.2);
		//this.setPricePerKmFiveToTen(7.7);
		//this.setPricePerKmTenToTwenty(3.25);
		//this.setPricePerKmMoreThanTwenty(2.6);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double price() {
		this.setPriceToPay(this.getLength()*lengthTypeMap.get(this.getLengthType())*trafficRateMap.
				get(this.getTrafficState()));
		return this.getPriceToPay();
	}

}
