package com.softserveinc.dokazovi.entity.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserInstitutionId implements Serializable {

    private Integer institutionId;

    private Integer userId;

    public UserInstitutionId() {
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UserInstitutionId))
            return false;

        UserInstitutionId other = (UserInstitutionId) o;

        return Objects.equals(other.institutionId, institutionId) &&
                Objects.equals(other.userId, userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionId, userId);
    }
}
