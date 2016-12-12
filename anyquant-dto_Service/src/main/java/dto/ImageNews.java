package dto;

import javax.swing.ImageIcon;

public class ImageNews extends News{
	String picurl;
	public ImageNews(String newstr,String url) {
		super(newstr,url);
	}

	
	public void setPictureURL(String picurl){
		this.picurl=picurl;
	}
	
	public String getPictureURL(){
		return picurl;
	}
	

	
	
}
