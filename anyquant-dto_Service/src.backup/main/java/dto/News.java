package dto;

public class News {
	String newstr;
	String url;
	
	public News(String newstr,String url){
		this.newstr=newstr;
		this.url=url;
	}
	
	public String getContent(){
		return newstr;
	}
	public String getURL(){
		return url;
	}
	
	
}
