package antifraud.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private String role; // should be prefixed with ROLE_
}
