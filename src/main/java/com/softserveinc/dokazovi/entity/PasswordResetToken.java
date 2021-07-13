package com.softserveinc.dokazovi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {

	private static final int EXPIRATION = 60;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String token;

	private UserEntity userEntity;

	private LocalDateTime dateExpiration;

}
