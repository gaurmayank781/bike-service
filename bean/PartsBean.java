package in.co.online.bike.service.bean;

public class PartsBean  extends BaseBean{
	
	
	private String company;
	private String partName;
	private String frequency;
	private String price;
	
	
	

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+ "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return partName;
	}
 
}
