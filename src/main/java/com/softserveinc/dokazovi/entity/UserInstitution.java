package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.embeddables.UserInstitutionId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity(name = "user_institution_entity")
@Table(name = "users_institutions")
public class UserInstitution implements Serializable {

	@EmbeddedId
	private UserInstitutionId id = new UserInstitutionId();

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity userEntity;

	@ManyToOne
	@MapsId("institutionId")
	@JoinColumn(name = "institution_id", insertable = false, updatable = false)
	private InstitutionEntity institutionEntity;

	@ColumnDefault("false")
	private boolean isPrimary;

	public UserInstitution(UserEntity userEntity, InstitutionEntity institutionEntity, boolean isPrimary) {
		this.userEntity = userEntity;
		this.institutionEntity = institutionEntity;
		this.isPrimary = isPrimary;
	}

}
