package com.softserveinc.dokazovi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.UserPromotionLevel;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_entity")
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String qualification;

	private String phone;

	private String avatar;

	@ColumnDefault("1.0")
	private Double promotionScale;

	@Enumerated(EnumType.STRING)
	@ColumnDefault("BASIC")
	private UserPromotionLevel promotionLevel;

	@Column(name = "bio", columnDefinition = "TEXT")
	private String bio;

	@ManyToOne
	@JoinColumn(name = "direction_id")
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private DirectionEntity mainDirection;

	@ManyToOne
	@JoinColumn(name = "institution_id")
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private InstitutionEntity mainInstitution;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<PostEntity> posts;

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<CharityEntity> charities;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_institutions",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "institution_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<InstitutionEntity> institutions;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "roles_users",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<RoleEntity> roles;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_directions",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<DirectionEntity> directions;

	@OneToMany(mappedBy = "user")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<SourceEntity> sources;

	@CreationTimestamp
	private Timestamp createdAt;


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class)
	private Set<ProviderEntity> userProviderEntities;


	@Column(name = "enabled")
	private Boolean enabled;

	public PostEntity getLatestExpertPost() {
		if (posts == null || posts.isEmpty()) {
			return null;
		}
		return posts.stream()
				.filter(postEntity -> Objects.equals(postEntity.getStatus(), PostStatus.PUBLISHED))
				.max(Comparator.comparing(PostEntity::getCreatedAt))
				.orElse(null);
	}

	public boolean getEnabled() {
		return this.enabled;
	}
}
