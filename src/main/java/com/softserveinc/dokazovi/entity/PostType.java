package com.softserveinc.dokazovi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity(name = "post_types")
@Table(name = "post_types")
public class PostType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
	private Set<Post> posts;

	public PostType() {
	}

	public PostType(String name) {
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

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "PostType{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PostType)) {
			return false;
		}

		PostType other = (PostType) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.name, name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
