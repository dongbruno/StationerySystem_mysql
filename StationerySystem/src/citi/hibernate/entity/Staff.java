package citi.hibernate.entity;
// Generated 2017-3-23 13:03:30 by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Staff generated by hbm2java
 */
public class Staff implements java.io.Serializable {

	private String soeId;
	private String name;
	private boolean isAdmin;
	private String location;
	private String unit;
	private String team;
	private Set orderses = new HashSet(0);

	public Staff() {
	}

	public Staff(String soeId, String name, boolean isAdmin, String location, String unit, String team) {
		this.soeId = soeId;
		this.name = name;
		this.isAdmin = isAdmin;
		this.location = location;
		this.unit = unit;
		this.team = team;
	}

	public Staff(String soeId, String name, boolean isAdmin, String location, String unit, String team, Set orderses) {
		this.soeId = soeId;
		this.name = name;
		this.isAdmin = isAdmin;
		this.location = location;
		this.unit = unit;
		this.team = team;
		this.orderses = orderses;
	}

	public String getSoeId() {
		return this.soeId;
	}

	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTeam() {
		return this.team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

}
