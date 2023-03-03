package final_project_2.models;

import final_project_2.configs.Authority;
import final_project_2.configs.AuthorityEnum;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority_join_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public User(String name, String password, List<Authority> authorities) {
        this.name = name;
        this.password = password;
        this.authorities = authorities;
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
        enabled = true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                ", score=" + score +
                '}';
    }

    private String confirmpassword;

    private double score;

    public User(double score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }


    public User(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public boolean isAdmin() throws NullPointerException {
        for (Authority authority : authorities) {
            if (authority.getAuthority().equals(AuthorityEnum.ROLE_ADMIN.name())) {
                return true;
            }
        }
        return false;
    }
}
