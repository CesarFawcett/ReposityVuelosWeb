package aeroline.nr.api.api.Dto;

import org.springframework.stereotype.Component;
import aeroline.nr.api.entities.User;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFullname(user.getFullname());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public User toEntity(UserCreateDto dto) {
        User user = new User();
        user.setFullname(dto.getFullname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
