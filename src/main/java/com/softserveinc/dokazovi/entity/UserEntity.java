package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

/**
 * The User Entity is responsible for correlating with User table in the database.
 */

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
	private String phone;
	private String avatar;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PostEntity> posts;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	@OneToOne(mappedBy = "profile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private DoctorEntity doctor;

	@CreationTimestamp
	private Timestamp createdAt;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<ProviderEntity> userProviderEntities;

	@Column(name = "enabled")
	@EqualsAndHashCode.Exclude
	private Boolean enabled;

	/**
	 * Gets latest expert post, if it exists.
	 * If not - returns null
	 *
	 * @return the resulting post entity
	 */
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
