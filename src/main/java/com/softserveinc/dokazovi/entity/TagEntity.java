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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "tag_entity")
@Table(name = "tags")
public class TagEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Integer id;

	private String tag;

	@ManyToMany(mappedBy = "tags")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PostEntity> posts = new HashSet<>();

	public TagEntity(String tag) {
		this.tag = tag;
	}

}
