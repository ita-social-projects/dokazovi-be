package com.softserveinc.dokazovi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "institutions")
@Table(name = "institutions")
public class Institution implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "institution_id")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;

	@Column(name = "address", nullable = false)
	private String address;

	@OneToMany(
			cascade = {CascadeType.REFRESH, CascadeType.MERGE},
			mappedBy = "institution",
			orphanRemoval = true
	)
	private Set<UserInstitution> users = new HashSet<>();

	public Institution() {
	}

	public Institution(Region region, String name, String address) {
		this.name = name;
		this.region = region;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<UserInstitution> getUsers() {
		return users;
	}

	public void setUsers(Set<UserInstitution> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Institution{" +
				"id=" + id +
				", name='" + name + '\'' +
				", region=" + region +
				", address='" + address + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Institution)) {
			return false;
		}

		Institution other = (Institution) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.name, name) &&
				Objects.equals(other.address, address) &&
				Objects.equals(other.region, region);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, address, region);
	}
}
