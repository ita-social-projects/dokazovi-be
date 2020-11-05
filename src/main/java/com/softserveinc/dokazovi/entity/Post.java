package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
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
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "posts")
@Table(name = "posts")
public class Post implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer id;

	private String title;

	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@ColumnDefault("false")
	private boolean important;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private PostType type;

	@ManyToOne
	@JoinColumn(name = "direction_id")
	private Direction mainDirection;

	@Enumerated(EnumType.STRING)
	@Type(type = "com.softserveinc.dokazovi.entity.enumerations.PostgreSQLPostStatusEnumType")
	private PostStatus status;

	@ManyToMany(
			cascade = {CascadeType.REFRESH, CascadeType.MERGE},
			fetch = FetchType.EAGER
	)
	@JoinTable(
			name = "posts_directions",
			joinColumns = {@JoinColumn(name = "post_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Direction> directions = new HashSet<>();

	@ManyToMany(
			cascade = {CascadeType.REFRESH, CascadeType.MERGE},
			fetch = FetchType.EAGER
	)
	@JoinTable(
			name = "posts_tags",
			joinColumns = {@JoinColumn(name = "post_id")},
			inverseJoinColumns = {@JoinColumn(name = "tag_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Tag> tags = new HashSet<>();

	@ManyToMany(
			cascade = {CascadeType.REFRESH, CascadeType.MERGE},
			fetch = FetchType.EAGER
	)
	@JoinTable(
			name = "posts_sources",
			joinColumns = {@JoinColumn(name = "source_id")},
			inverseJoinColumns = {@JoinColumn(name = "post_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Source> sources = new HashSet<>();

	@CreationTimestamp
	private Timestamp createdAt;

	@UpdateTimestamp
	private Timestamp modifiedAt;

	public Post(String title, String content, boolean important, User author,
			PostType type, Direction mainDirection, PostStatus status) {
		this.title = title;
		this.content = content;
		this.important = important;
		this.author = author;
		this.type = type;
		this.mainDirection = mainDirection;
		this.status = status;
	}

}
