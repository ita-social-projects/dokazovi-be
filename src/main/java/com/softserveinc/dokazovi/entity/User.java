package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

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
import java.util.Objects;
import java.util.Set;

@Entity(name = "users")
@Table(name = "users")
public class User implements Serializable {

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
	@Type(type = "com.softserveinc.dokazovi.entity.enumerations.PostgreSQLUserStatusEnumType")
	private UserStatus status;

	@OneToMany(mappedBy = "author")
	private Set<Post> posts = new HashSet<>();

	@OneToMany(mappedBy = "author")
	private Set<Charity> charities = new HashSet<>();

	@OneToMany(
			cascade = {CascadeType.REFRESH, CascadeType.MERGE},
			mappedBy = "user",
			orphanRemoval = true
	)
	private Set<UserInstitution> institutions = new HashSet<>();

	@ManyToMany
	@JoinTable(
			name = "roles_users",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(
			name = "users_directions",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	private Set<Direction> directions = new HashSet<>();

	@ManyToMany
	@JoinTable(
			name = "users_sources",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "source_id")}
	)
	private Set<Source> sources = new HashSet<>();

	@CreationTimestamp
	private Timestamp createdAt;

	public User() {}

	public User(String firstName, String lastName, String email, String password, String qualification,
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

	public void addRole(Role role) {
		roles.add(role);
		role.getUsers().add(this);
	}

	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<UserInstitution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Set<UserInstitution> institutions) {
		this.institutions = institutions;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Direction> getDirections() {
		return directions;
	}

	public void setDirections(Set<Direction> directions) {
		this.directions = directions;
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", qualification='" + qualification + '\'' +
				", phone='" + phone + '\'' +
				", bio='" + bio + '\'' +
				", status=" + status +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		User other = (User) o;

		return Objects.equals(other.id, id) &&
				Objects.equals(other.email, email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email);
	}
}
