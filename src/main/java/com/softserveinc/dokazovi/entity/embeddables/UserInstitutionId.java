package com.softserveinc.dokazovi.entity.embeddables;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserInstitutionId implements Serializable {

	private Integer institutionId;

	private Integer userId;

}
