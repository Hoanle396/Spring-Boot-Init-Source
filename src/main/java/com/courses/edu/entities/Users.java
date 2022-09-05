package com.courses.edu.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.courses.edu.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users extends BaseEntity {

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "fullname", nullable = false)
	private String fullname;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", length = 32, columnDefinition = "varchar(32) default 'USER'")
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	@Column(name = "avatarUrl", nullable = true, length = 512)
	private String avatarUrl;

	public Users() {
		super();
	}

	public Users(String username, String fullname, String password, Role role) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.role = role;
	}

	public Users(String username, String fullname, String password) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
	}

	public Users(String username, String fullname, String password, String avatarUrl) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.avatarUrl = avatarUrl;
	}

	public Users(Long id, String username, String fullname, String password, Role role, String avatarUrl, Date createAt,
			Date updateAt) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.role = role;
		this.avatarUrl = avatarUrl;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}
