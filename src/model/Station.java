package model;
 
public class Station {
 
	 private int number;
	 private String name;
	 private boolean type;
	 private double longitude;
	 private double latitude;
	 private String linename;
	 
	 public Station() {}
	 
	 public Station(String name,boolean type,double longitude,double latitude) {
		 this.name=name;
		 this.type=type;
		 this.longitude=longitude;
		 this.latitude=latitude;
	 }
	 
	 public Station(String name){
		 this.name=name;
	 }
	 
	 public  int getNumber() {
		 return number;
	 }
	 
	 public  String getName() {
		 return name;
	 }
	 
	 public  boolean getType() {
		 return type;
	 }
	 
	 public  double getLongitude() {
		 return longitude;
	 }

	 public  double getLatitude() {
		 return latitude;
	 }
	 
	 public  String getLinename() {
		 return linename;
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
	 public  void setLongitude(double longitude) {
		 this.longitude=longitude;
	 }
	 public  void setLatitude(double latitude) {
		 this.latitude=latitude;
	 }
	 public  void setLinename(String line_name) {
		 this.linename=line_name;
	 }
}
 