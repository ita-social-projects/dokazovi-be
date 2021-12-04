package com.softserveinc.dokazovi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Optional;


@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "post_fake_view_entity")
@Table(name = "post_fake_views")
public class PostFakeViewEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter Integer id;

	@OneToOne
	@JoinColumn(name = "post_id")
	private @Getter PostEntity post;

	private Integer views;

	public Integer getViews() {
		return Optional.ofNullable(views).orElse(0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		PostFakeViewEntity that = (PostFakeViewEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
