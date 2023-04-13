package aeroline.nr.api.api.Dto;

import org.springframework.stereotype.Component;
import aeroline.nr.api.entities.User;

@Component
public class UserMapper {
    public UserCreateDto toUserCreateDto(User user){
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setId(user.getId());
        userCreateDto.setFullname(user.getFullname());
        userCreateDto.setUsername(user.getUsername());
        userCreateDto.setPassword(user.getPassword());

        return userCreateDto;
    }
    public User toEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFullname(userCreateDto.getFullname());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        return user;
    
    }
    public User toUserEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setId(userCreateDto.getId());
        user.setFullname(userCreateDto.getFullname());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());

        return user;
    }
}

