package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_directions",
			joinColumns = {@JoinColumn(name = "post_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	private Set<Direction> directions = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_tags",
			joinColumns = {@JoinColumn(name = "post_id")},
			inverseJoinColumns = {@JoinColumn(name = "tag_id")}
	)
	private Set<Tag> tags = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "posts_sources",
			joinColumns = {@JoinColumn(name = "source_id")},
			inverseJoinColumns = {@JoinColumn(name = "post_id")}
	)
	private Set<Source> sources = new HashSet<>();

	@CreationTimestamp
	private Timestamp createdAt;

	@UpdateTimestamp
	private Timestamp modifiedAt;

	public Post() {
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}

	public Direction getMainDirection() {
		return mainDirection;
	}

	public void setMainDirection(Direction mainDirection) {
		this.mainDirection = mainDirection;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public Set<Direction> getDirections() {
		return directions;
	}

	public void setDirections(Set<Direction> directions) {
		this.directions = directions;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Source> getSources() {
		return sources;
	}

	public void setSources(Set<Source> sources) {
		this.sources = sources;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", important=" + important +
				", author=" + author +
				", type=" + type +
				", mainDirection=" + mainDirection +
				", status=" + status +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Post)) {
			return false;
		}

		Post other = (Post) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.author, author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, author);
	}

}
