package cars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CarDetails {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map<String, ArrayList<String>> cars = new HashMap<String, ArrayList<String>>();
		
		//Audi Car Details
		ArrayList<String> audi_car = new ArrayList<String>(); 
		audi_car.add("audi a4");
		audi_car.add("audi q3");
		audi_car.add("audi a6");
		
		cars.put("audi", audi_car);
		
		ArrayList<String> audi_a4 = new ArrayList<String>();
		audi_a4.add("5 Star Safety");
		audi_a4.add("14kmpl");
		audi_a4.add("192 bhp");
		audi_a4.add("Rs. 44.99 Lakh");
		
		ArrayList<String> audi_q3 = new ArrayList<String>();
		audi_q3.add("5 Star Safety");
		audi_q3.add("14kmpl");
		audi_q3.add("192 bhp");
		audi_q3.add("Rs. 44.99 Lakh");
		
		ArrayList<String> audi_a6 = new ArrayList<String>();
		audi_a6.add("5 Star Safety");
		audi_a6.add("14kmpl");
		audi_a6.add("241 bhp");
		audi_a6.add("Rs. 65.72 Lakh");
		
		//BMW
		ArrayList<String> bmw_car = new ArrayList<String>(); 
		bmw_car.add("bmw x3");
		bmw_car.add("bmw x1");
		bmw_car.add("bmw m5");
		cars.put("bmw", bmw_car);
		
		ArrayList<String> bmw_x3 = new ArrayList<String>();
		bmw_x3.add("5 Star Safety");
		bmw_x3.add("16kmpl");
		bmw_x3.add("188 bhp");
		bmw_x3.add("Rs. 75.80 Lakh");
		
		ArrayList<String> bmw_x1 = new ArrayList<String>();
		bmw_x1.add("5 Star Safety");
		bmw_x1.add("16kmpl");
		bmw_x1.add("134 bhp");
		bmw_x1.add("Rs. 49.90 Lakh");
		
		ArrayList<String> bmw_m5 = new ArrayList<String>();
		bmw_m5.add("5 Star Safety");
		bmw_m5.add("717kmpl");
		bmw_m5.add("Not mentioned");
		bmw_m5.add("Rs. 1.99 Lakh");
		
		
		//jeep
		ArrayList<String> jeep_car = new ArrayList<String>(); 
		jeep_car.add("jeep compass");
		jeep_car.add("jeep meridian");
		jeep_car.add("jeep wrangler");
		cars.put("jeep", jeep_car);
		
		ArrayList<String> jeep_compass = new ArrayList<String>();
		jeep_compass.add("5 Star Safety");
		jeep_compass.add("13kmpl");
		jeep_compass.add("161 bhp");
		jeep_compass.add("Rs. 18.99 Lakh");
		
		ArrayList<String> jeep_meridian = new ArrayList<String>();
		jeep_meridian.add("Not mentioned");
		jeep_meridian.add("14kmpl");
		jeep_meridian.add("168 bhp");
		jeep_meridian.add("Rs. 24.99 Lakh");
		
		ArrayList<String> jeep_wrangler = new ArrayList<String>();
		jeep_wrangler.add("Not mentioned");
		jeep_wrangler.add("10kmpl");
		jeep_wrangler.add("268 bhp");
		jeep_wrangler.add("Rs. 67.65 Lakh");
		
		Map<String, ArrayList<String>> model = new HashMap<String, ArrayList<String>>();
		model.put("audi a4", audi_a4);
		model.put("audi q3", audi_q3);
		model.put("audi a6", audi_a6);
		model.put("bmw x3", bmw_x3);
		model.put("bmw x1", bmw_x1);
		model.put("bmw m5", bmw_m5);
		model.put("jeep compass", jeep_compass);
		model.put("jeep meridian", jeep_meridian);
		model.put("jeep wrangler", jeep_wrangler);
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Car Details");
		String carname = "";
		while(true) {
			if(carname.equals("")) {
				System.out.print("Enter the car name: ");
				carname = bf.readLine().toLowerCase();
			}
			if(!cars.containsKey(carname)) {
				System.out.println("Car is not available");
				carname="";
			}
			else {
				ArrayList<String> result = cars.get(carname);
				result.stream().forEach((String s)-> System.out.println(s));
				while(true) {
					System.out.print("Enter model name to know more/ Enter car name to change: ");
					String modelname = bf.readLine().toLowerCase();
					if(!result.contains(modelname)) {
						if(cars.containsKey(modelname)) {
							carname = modelname;
							break;
						}
						else {
							System.out.println("Car model is not available");
						}
					}
					else {
						ArrayList<String> details = model.get(modelname);
						details.stream().forEach((String s)-> System.out.println(s));
					}
				}
			}
		}
	}

}
