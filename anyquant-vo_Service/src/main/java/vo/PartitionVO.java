package vo;

public class PartitionVO {

	private String id;
	private int number;

	public PartitionVO(String id, int number) {
		this.id = id;
		this.number = number;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}
}
