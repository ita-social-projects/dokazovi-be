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
@Entity(name = "users_institutions")
@Table(name = "users_institutions")
public class UserInstitution implements Serializable {

    @EmbeddedId
    private UserInstitutionId id = new UserInstitutionId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("institutionId")
    @JoinColumn(name="institution_id", insertable = false, updatable = false)
    private Institution institution;

    @ColumnDefault("false")
    private boolean isPrimary;

    public UserInstitution(User user, Institution institution, boolean isPrimary) {
        this.user = user;
        this.institution = institution;
        this.isPrimary = isPrimary;
    }

}
