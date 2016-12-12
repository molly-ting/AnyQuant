package vo;

import javax.swing.ImageIcon;

public class ImageNewsVO extends NewsVO{
	ImageIcon icon;
	String picurl;
	public String getPicurl() {
		return picurl;
	}


	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}


	public ImageNewsVO(String newstr,String url) {
		super(newstr,url);
	}

	
	public void setImageicon(ImageIcon icon){
		this.icon=icon;
	}
	
	public ImageIcon getImageIcon(){
		return icon;
	}
}
