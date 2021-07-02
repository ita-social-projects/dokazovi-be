package com.softserveinc.dokazovi.entity;

import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "role_entity")
@Table(name = "roles")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;

	@Column(name = "role_name")
	private String name;

	@ElementCollection(targetClass = RolePermission.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"))
	@Enumerated(EnumType.STRING)
	private Set<RolePermission> permissions;
}
