package com.softserveinc.dokazovi.entity.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum RolePermission implements GrantedAuthority {
	SAVE_OWN_PUBLICATION,
	SAVE_TAG,
	DELETE_POST,
	DELETE_OWN_POST,
	UPDATE_POST,
	UPDATE_OWN_POST,
	SAVE_PUBLICATION,
	SET_IMPORTANCE,
	SAVE_PLATFORM_INFORMATION,
	UPDATE_PLATFORM_INFORMATION,
	CREATE_AUTHOR,
	DELETE_AUTHOR,
	UPDATE_AUTHOR;

	@Override
	public String getAuthority() {
		return name();
	}
}
