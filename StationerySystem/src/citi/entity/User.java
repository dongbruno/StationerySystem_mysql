package citi.entity;

public class User {

	private String soeId;
	private String location;
	private String unit;
	private String team;
	private String name;
	private String authority;
	public String getSoeId() {
		return soeId;
	}
	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public User(String soeId, String location, String unit, String team, String name, String authority) {
		super();
		this.soeId = soeId;
		this.location = location;
		this.unit = unit;
		this.team = team;
		this.name = name;
		this.authority = authority;
	}
	
}
