package com.softserveinc.dokazovi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post_entity")
@Table(name = "posts")
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer id;

	private String title;

	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@ColumnDefault("false")
	private Boolean important;

	@ManyToOne
	@JoinColumn(name = "author_id")
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private UserEntity author;

	@ManyToOne
	@JoinColumn(name = "type_id")
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private PostTypeEntity type;

	@ManyToOne
	@JoinColumn(name = "direction_id")
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private DirectionEntity mainDirection;

	@Enumerated(EnumType.STRING)
	private PostStatus status;

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_directions",
			joinColumns = {@JoinColumn(name = "post_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<DirectionEntity> directions;

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_tags",
			joinColumns = {@JoinColumn(name = "post_id")},
			inverseJoinColumns = {@JoinColumn(name = "tag_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<TagEntity> tags;

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_sources",
			joinColumns = {@JoinColumn(name = "source_id")},
			inverseJoinColumns = {@JoinColumn(name = "post_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<SourceEntity> sources;

	@CreationTimestamp
	private Timestamp createdAt;

	@UpdateTimestamp
	private Timestamp modifiedAt;
}
