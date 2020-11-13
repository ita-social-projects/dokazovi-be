package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

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
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user_entity")
@Table(name = "users")
public class UserEntity implements Serializable {

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

	@Column(name = "bio", columnDefinition = "TEXT")
	private String bio;

	@ManyToOne
	@JoinColumn(name = "direction_id")
	private DirectionEntity mainDirection;

	@ManyToOne
	@JoinColumn(name = "institution_id")
	private InstitutionEntity mainInstitution;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PostEntity> posts;

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<CharityEntity> charities;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_institutions",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "institution_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<InstitutionEntity> institutions;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "roles_users",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<RoleEntity> roles;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_directions",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<DirectionEntity> directions;

	@OneToMany(mappedBy = "user")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<SourceEntity> sources;

	@CreationTimestamp
	private Timestamp createdAt;

	public UserEntity(String firstName, String lastName, String email, String password, String qualification,
			String phone, String avatar, String bio, UserStatus status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.qualification = qualification;
		this.phone = phone;
		this.avatar = avatar;
		this.bio = bio;
		this.status = status;
	}

	public void addRole(RoleEntity role) {
		roles.add(role);
		role.getUsers().add(this);
	}

	public void removeRole(RoleEntity role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}
}
