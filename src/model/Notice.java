package model;
 
public class Notice {
 
	 private int number;
	 private String title;
	 private String content;
	 private String time;
	 
	 public Notice() {}
	 
	 
	 public Notice(String title,String content,String time) {
		 this.title=title;
		 this.content=content;
		 this.time=time;
	 }
	 
	 public Notice(int number,String title,String content,String time) {
		 this.number=number;
		 this.title=title;
		 this.content=content;
		 this.time=time;
	 }

	 public  int getNumber() {
		 return number;
	 }
	 public void setNumber(int number) {
		 this.number = number;
	 }
	 public  String getTitle() {
		 return title;
	 }
	 public void setTitle(String title) {
		 this.title = title;
	 }
	 public  String getContent() {
		 return content;
	 }
	 public void setContent(String content) {
		 this.content = content;
	 }
	 public  String getTime() {
		 return time;
	 }
	 public void setTime(String time) {
		 this.time = time;
	 }
}
 