package com.softserveinc.dokazovi.security;


import com.softserveinc.dokazovi.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserPrincipal  implements OAuth2User, UserDetails {
private Integer id;
private String email;
private String password;
private Collection<? extends GrantedAuthority> authorities;
private transient Map<String, Object> attributes;

public UserPrincipal(Integer id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        }

public static UserPrincipal create(UserEntity user) {
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(a -> roles.add(a.getName().toUpperCase()));
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(a -> authorities.add(new SimpleGrantedAuthority(a)));
        return new UserPrincipal(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        authorities
        );
        }

public static UserPrincipal create(UserEntity user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
        }

public Integer getId() {
        return id;
        }

public String getEmail() {
        return email;
        }

@Override
public String getPassword() {
        return password;
        }

@Override
public String getUsername() {
        return getEmail();
        }

@Override
public boolean isAccountNonExpired() {
        return true;
        }

@Override
public boolean isAccountNonLocked() {
        return true;
        }

@Override
public boolean isCredentialsNonExpired() {
        return true;
        }

@Override
public boolean isEnabled() {
        return true;
        }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
        }

@Override
public Map<String, Object> getAttributes() {
        return attributes;
        }

public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        }

@Override
public String getName() {
        return String.valueOf(id);
        }
}
