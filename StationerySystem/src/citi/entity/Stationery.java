package citi.entity;

public class Stationery {

	private Integer id;
	private String kind;
	private String name;
	private String standard;
	private double price;
	private Integer quantity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		
		this.name = name.substring(0, name.indexOf("("));
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Stationery(Integer id, String kind, String name, String standard, double price, Integer quantity) {
		super();
		this.id = id;
		this.kind = kind;
		this.name = name.substring(0, name.indexOf("("));
		this.standard = standard;
		this.price = price;
		this.quantity = quantity;
	}
	
}
