package aeroline.nr.api.api.Dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String fullname;
    private String username;
    private String password;
}
