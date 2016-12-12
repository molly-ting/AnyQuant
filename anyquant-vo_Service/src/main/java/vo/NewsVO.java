package vo;

public class NewsVO {
	String newstr;
	String url;
	
	public NewsVO(String newstr,String url){
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
