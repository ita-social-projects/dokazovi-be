package com.softserveinc.dokazovi.entity.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum RolePermission implements GrantedAuthority {
	SAVE_OWN_PUBLICATION,
	SAVE_TAG;

	@Override
	public String getAuthority() {
		return name();
	}
}