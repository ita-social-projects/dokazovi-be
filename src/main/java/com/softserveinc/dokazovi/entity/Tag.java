package com.softserveinc.dokazovi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity(name = "tags")
@Table(name = "tags")
public class Tag implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Integer id;

	private String tag;

	@ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
	private Set<Post> posts = new HashSet<>();

	public Tag() {
	}

	public Tag(String tag) {
		this.tag = tag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Tag{" +
				"id=" + id +
				", tag='" + tag + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Tag)) {
			return false;
		}

		Tag other = (Tag) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.tag, tag);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tag);
	}

}
