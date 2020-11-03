package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.SourceType;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "sources")
@Table(name = "sources")
public class Source implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "source_id")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column
	@Type(type = "com.softserveinc.dokazovi.entity.enumerations.PostgreSQLSourceTypeEnumType")
	private SourceType type;

	private String value;

	@ManyToMany(mappedBy = "sources", fetch = FetchType.EAGER)
	private Set<Post> posts = new HashSet<>();

	@ManyToMany(mappedBy = "sources")
	private Set<User> users = new HashSet<>();

	public Source() {
	}

	public Source(SourceType type, String value) {
		this.type = type;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SourceType getType() {
		return type;
	}

	public void setType(SourceType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Source{" +
				"id=" + id +
				", type=" + type +
				", value='" + value + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Source)) {
			return false;
		}

		Source other = (Source) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.value, value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, value);
	}
}
