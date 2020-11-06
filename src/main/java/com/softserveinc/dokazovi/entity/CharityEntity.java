package com.softserveinc.dokazovi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
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

@Data
@NoArgsConstructor
@Entity(name = "charity_entity")
@Table(name = "charities")
public class CharityEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "charity_id")
	private Integer id;

	@Column(name = "body", columnDefinition = "TEXT")
	private String body;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private UserEntity author;

	@CreationTimestamp
	private Timestamp createdAt;

	@UpdateTimestamp
	private Timestamp modifiedAt;

	public CharityEntity(String body, UserEntity author) {
		this.body = body;
		this.author = author;
	}

}
