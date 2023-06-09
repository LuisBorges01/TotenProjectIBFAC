package br.ibfac.main.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER")
public class UserModel implements UserDetails, Serializable{
/*UserDetails no geral´é uma classe de informações sobre um usuário*/
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false, unique = true)
	private String password ;
	
	@ManyToMany
	@JoinTable(name = "TB_USERS_ROLES",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleModel> roles;

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.roles;
		}
	
		@Override
		public String getPassword() {
			return this.password;
		}
	
		@Override
		public String getUsername() {
	        return this.username;
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
}