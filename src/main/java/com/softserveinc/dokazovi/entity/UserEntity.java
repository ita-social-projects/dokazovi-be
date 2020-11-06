package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
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

	@Column(name = "bio", columnDefinition = "TEXT")
	private String bio;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PostEntity> postEntities = new HashSet<>();

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<CharityEntity> charities = new HashSet<>();

	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = {CascadeType.REFRESH, CascadeType.MERGE},
			mappedBy = "user",
			orphanRemoval = true
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<UserInstitution> institutions = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "roles_users",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<RoleEntity> roleEntities = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_directions",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<DirectionEntity> directionEntities = new HashSet<>();

	@ManyToMany
	@JoinTable(
			name = "users_sources",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "source_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<SourceEntity> sourceEntities = new HashSet<>();

	@CreationTimestamp
	private Timestamp createdAt;

	public UserEntity(String firstName, String lastName, String email, String password, String qualification,
			String phone, String bio, UserStatus status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.qualification = qualification;
		this.phone = phone;
		this.bio = bio;
		this.status = status;
	}

	public void addRole(RoleEntity roleEntity) {
		roleEntities.add(roleEntity);
		roleEntity.getUserEntities().add(this);
	}

	public void removeRole(RoleEntity roleEntity) {
		roleEntities.remove(roleEntity);
		roleEntity.getUserEntities().remove(this);
	}

}
