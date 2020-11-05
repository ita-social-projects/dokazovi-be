package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.SourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Entity(name = "sources")
@Table(name = "sources")
public class Source implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "source_id")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Type(type = "com.softserveinc.dokazovi.entity.enumerations.PostgreSQLSourceTypeEnumType")
	private SourceType type;

	private String value;

	@ManyToMany(mappedBy = "sources")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Post> posts = new HashSet<>();

	@ManyToMany(mappedBy = "sources")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<User> users = new HashSet<>();

	public Source(SourceType type, String value) {
		this.type = type;
		this.value = value;
	}

}
