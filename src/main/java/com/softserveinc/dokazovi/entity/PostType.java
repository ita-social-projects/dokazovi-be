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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "post_type_entity")
@Table(name = "post_types")
public class PostType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "type")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Post> posts;

	public PostType(String name) {
		this.name = name;
	}

}
