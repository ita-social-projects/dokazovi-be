package com.softserveinc.dokazovi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "direction_entity")
@Table(name = "directions")
public class Direction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "direction_id")
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "mainDirection")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Post> mainPostsDirections = new HashSet<>();

	@ManyToMany(mappedBy = "directions")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Post> posts = new HashSet<>();

	@ManyToMany(mappedBy = "directions")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<User> users = new HashSet<>();

	public Direction(String name) {
		this.name = name;
	}

}
