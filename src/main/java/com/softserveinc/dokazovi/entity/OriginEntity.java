package com.softserveinc.dokazovi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.Table;
import java.util.Set;

/**
 * The Origin Entity is responsible for correlating with Origins table in the database.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "origin_entity")
@Table(name = "origins")
public class OriginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "origin_id")
	private Integer id;

	private String name;

	private String parameters;

	@ManyToMany(mappedBy = "origins")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PostEntity> posts;
}
