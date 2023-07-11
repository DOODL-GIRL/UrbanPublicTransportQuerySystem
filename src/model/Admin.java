package model;
 
public class Admin {
 
	 private String username;
	 private String password;
	 private String phonenumber;
	 
	 public Admin() {}
	 
	 public Admin(String username, String password) {
		 this.username=username;
		 this.password=password;
	 }
	 
	 public Admin(String username, String password, String phone_number) {
		 this.username=username;
		 this.password=password;
		 this.phonenumber=phone_number;
	 }
	 
	 public  String getUsername() {
		 return username;
	 }
	 public void setUsername(String username) {
		 this.username = username;
	 }
	 public String getPassword() {
		 return password;
	 }
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 public String getPhonenumber() {
		 return phonenumber;
	 }
	 public void setPhonenumber(String phone_number) {
		 this.phonenumber = phone_number;
	 }
}
 