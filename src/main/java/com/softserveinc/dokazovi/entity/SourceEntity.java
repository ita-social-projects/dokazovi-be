package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.SourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "source_entity")
@Table(name = "sources")
public class SourceEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "source_id")
	private Integer id;

	@Enumerated(EnumType.STRING)
	private SourceType type;

	private String value;

	@ManyToMany(mappedBy = "sources")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PostEntity> posts = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private UserEntity user;

	public SourceEntity(SourceType type, String value) {
		this.type = type;
		this.value = value;
	}

}
