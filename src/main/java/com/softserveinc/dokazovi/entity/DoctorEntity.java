package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.UserPromotionLevel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "doctor_entity")
@Table(name = "doctors")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Integer id;

	private String qualification;

	@Column(columnDefinition = "TEXT")
	private String bio;

	@ColumnDefault("1.0")
	private Double promotionScale;

	@Setter(AccessLevel.NONE)
	private Long publishedPosts;

	@Setter(AccessLevel.NONE)
	private Long rating;

	@Enumerated(EnumType.ORDINAL)
	@ColumnDefault("0")
	private UserPromotionLevel promotionLevel;

	@ManyToOne
	@JoinColumn(name = "institution_id")
	private InstitutionEntity mainInstitution;

	@OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private UserEntity profile;

	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<CharityEntity> charities;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "doctors_institutions",
			joinColumns = {@JoinColumn(name = "doctor_id")},
			inverseJoinColumns = {@JoinColumn(name = "institution_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<InstitutionEntity> institutions;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "doctors_directions",
			joinColumns = {@JoinColumn(name = "doctor_id")},
			inverseJoinColumns = {@JoinColumn(name = "direction_id")}
	)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<DirectionEntity> directions;

	private void updateUserRating() {
		rating = (long) Math.ceil(publishedPosts * promotionScale);
	}

	@PreUpdate
	public void preUpdateFunction() {
		updateUserRating();
	}

	@PrePersist
	public void prePersistFunction() {
		updateUserRating();
	}
}
