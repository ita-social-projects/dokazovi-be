package com.softserveinc.dokazovi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetTokenEntity {

	public static final int EXPIRATION = 60;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token_id")
	private Long id;

	private String token;

	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private UserEntity userEntity;

	@Column(name = "date_expiration")
	private LocalDateTime dateExpiration;
}
