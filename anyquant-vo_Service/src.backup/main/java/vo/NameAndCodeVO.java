package vo;

public class NameAndCodeVO {
	private String name;
	private String code;
	public NameAndCodeVO(String name,String code) {
		this.name=name;
		this.code=code;
	}
	
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
