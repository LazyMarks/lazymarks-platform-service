package com.lazymarks.platform.api.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lazymarks.platform.api.entity.User;

public class LazymarksUserDetails implements UserDetails {

	private static final long serialVersionUID = -6714298309486308232L;
	private User user;

	public LazymarksUserDetails(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	public String getEmail() {
		return this.user.getEmail();
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
		return this.user.isActive();
	}

}
