package model;

public class Product {
	private int pid;
	private String pname;
	private String pcategory;
	private double price;
	private String img;
	
	public Product() {
		
	}
	
	public Product(int pid, String pname, String pcategory, double price, String img) {
		this.pid = pid;
		this.pname = pname;
		this.pcategory = pcategory;
		this.price = price;
		this.img = img;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcategory() {
		return pcategory;
	}

	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
