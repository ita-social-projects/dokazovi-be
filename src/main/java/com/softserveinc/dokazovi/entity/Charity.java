package com.softserveinc.dokazovi.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "charities")
@Table(name = "charities")
public class Charity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "charity_id")
	private Integer id;

	@Column(name = "body", columnDefinition = "TEXT")
	private String body;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@CreationTimestamp
	private Timestamp createdAt;

	@UpdateTimestamp
	private Timestamp modifiedAt;

	public Charity() {
	}

	public Charity(String body, User author) {
		this.body = body;
		this.author = author;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	@Override
	public String toString() {
		return "Charity{" +
				"id=" + id +
				", body='" + body + '\'' +
				", author=" + author +
				", createdAt=" + createdAt +
				", modifiedAt=" + modifiedAt +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Charity)) {
			return false;
		}

		Charity other = (Charity) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.body, body) &&
				Objects.equals(other.author, author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, body, author);
	}
}
