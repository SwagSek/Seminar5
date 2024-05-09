package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "UserTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MyUser {
	
	@Column(name = "uId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int uid;
	
	@Column(name = "username")
	@NotNull
	@Size(min = 4, max = 20)
	private String username;
	
	@Column(name = "password")
	@NotNull
	@Size(min = 4, max = 20)
	private String password;
	
	private MyUser(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
}
