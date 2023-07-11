package model;
import java.util.List;
import java.util.ArrayList;
 
public class Line {
 
	 private int number;
	 private String name;
	 private boolean type;
	 private int starting_station;
	 private int ending_station;
	 private String starting_time;
	 private String ending_time;
	 private List<Station> stations=new ArrayList<>();
	 
	 public Line() {}
	 
	 
	 public  int getNumber() {
		 return number;
	 }
	 
	 public  String getName() {
		 return name;
	 }
	 
	 public  boolean getType() {
		 return type;
	 }
	 
	 public  int getStartingStation() {
		 return starting_station;
	 }

	 public  int getEndingStation() {
		 return ending_station;
	 }
	 
	 public  String getStartingTime() {
		 return starting_time;
	 }
	 
	 public  String getEndingTime() {
		 return ending_time;
	 }

	 public  List<Station> getStations() {
		 return stations;
	 }
	 

	 
	 public  void setNumber(int number) {
		 this.number=number;
	 }
	 public  void setName(String name) {
		 this.name=name;
	 }
	 public  void setType(boolean type) {
		 this.type=type;
	 }
	 public  void setStartingStation(int starting_station) {
		 this.starting_station=starting_station;
	 }
	 public  void setEndingStation(int ending_station) {
		 this.ending_station=ending_station;
	 }
	 public  void setStartingTime(String starting_time) {
		 this.starting_time=starting_time;
	 }
	 public  void setEndingTime(String ending_time) {
		 this.ending_time=ending_time;
	 }
	 public  void setStations(List<Station> stations) {
		 this.stations.addAll(stations);
	 }
	
}
 