package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.embeddables.UserInstitutionId;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "users_institutions")
@Table(name = "users_institutions")
public class UserInstitution implements Serializable {

    @EmbeddedId
    @Column(name = "id")
    private UserInstitutionId id = new UserInstitutionId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("institutionId")
    private Institution institution;

    @Column(name = "is_primary", nullable = false)
    @ColumnDefault("false")
    private boolean isPrimary;

    public UserInstitution() {
    }

    public UserInstitution(User user, Institution institution, boolean isPrimary) {
        this.user = user;
        this.institution = institution;
        this.isPrimary = isPrimary;
    }

    public UserInstitutionId getId() {
        return id;
    }

    public void setId(UserInstitutionId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInstitution)) {
            return false;
        }

        UserInstitution other = (UserInstitution) o;

        return Objects.equals(other.id, id) &&
                Objects.equals(other.isPrimary, isPrimary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isPrimary);
    }
}
