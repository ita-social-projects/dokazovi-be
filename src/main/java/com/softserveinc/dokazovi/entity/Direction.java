package com.softserveinc.dokazovi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "directions")
@Table(name = "directions")
public class Direction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "direction_id")
	private Integer id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "mainDirection", fetch = FetchType.EAGER)
	private Set<Post> mainPostsDirections = new HashSet<>();

	@ManyToMany(mappedBy = "directions", fetch = FetchType.EAGER)
	private Set<Post> posts = new HashSet<>();

	@ManyToMany(mappedBy = "directions", fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<>();

	public Direction() {
	}

	public Direction(String name) {
		this.name = name;
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

	public Set<Post> getMainPostsDirections() {
		return mainPostsDirections;
	}

	public void setMainPostsDirections(Set<Post> mainPostsDirections) {
		this.mainPostsDirections = mainPostsDirections;
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
		return "Direction{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Direction)) {
			return false;
		}

		Direction other = (Direction) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.name, name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
