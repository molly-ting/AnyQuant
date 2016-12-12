package vo;

public class DonutVO {
	
	private String id;
	private String name;
	private int max;
	private int[] arr;
	
	public DonutVO(String id,String name,int max,int[] arr){
		this.id = id;
		this.name = name;
		this.max = max;
		this.arr = arr;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getMax(){
		return max;
	}
	
	public int[] getArr(){
		return arr;
	}
}
