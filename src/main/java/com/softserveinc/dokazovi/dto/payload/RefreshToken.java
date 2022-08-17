package com.softserveinc.dokazovi.dto.payload;

import com.softserveinc.dokazovi.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity(name = "refresh_token")
@Table(name = "refreshtoken")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refreshtoken_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

}
