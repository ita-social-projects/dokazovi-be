package com.softserveinc.dokazovi.entity;

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
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post_entity")
@Table(name = "posts")
public class PostEntity implements Serializable {

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
	private UserEntity author;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private PostTypeEntity type;

	@ManyToOne
	@JoinColumn(name = "direction_id")
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
	private Set<DirectionEntity> directions;

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_tags",
			joinColumns = {@JoinColumn(name = "post_id")},
			inverseJoinColumns = {@JoinColumn(name = "tag_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<TagEntity> tags;

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_sources",
			joinColumns = {@JoinColumn(name = "source_id")},
			inverseJoinColumns = {@JoinColumn(name = "post_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<SourceEntity> sources;

	@CreationTimestamp
	private Timestamp createdAt;

	@UpdateTimestamp
	private Timestamp modifiedAt;

	public PostEntity(String title, String content, Boolean important, UserEntity author,
			PostTypeEntity type, DirectionEntity mainDirection, PostStatus status) {
		this.title = title;
		this.content = content;
		this.important = important;
		this.author = author;
		this.type = type;
		this.mainDirection = mainDirection;
		this.status = status;
	}

}
