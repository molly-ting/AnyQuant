package vo;

import org.omg.CORBA.OBJ_ADAPTER;

public class DetailBean {

	private String name;
	private String id;
	private Object[][] data;
	
	public DetailBean(){
		data=new Object[5][2];
		data[0][0]="回报系数";
		data[1][0]="安全系数";
		data[2][0]="入手时机";
		data[3][0]="价�?�评�?";
		data[4][0]="增�?�潜�?";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void set5scores(double a,double b,double c,double d,double e){
		data[0][1]=a;
		data[1][1]=b;
		data[2][1]=c;
		data[3][1]=d;
		data[4][1]=e;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}
	
	
	
}
