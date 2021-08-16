package com.softserveinc.dokazovi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "foreign_expert_entity")
@Table(name = "foreign_experts")
public class ForeignExpertEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "foreign_expert_id")
	private Integer id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "bio")
	private String bio;

	@Column(name = "avatar")
	private String avatar;
}
