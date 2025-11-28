package aeroline.nr.api.services;

import java.util.List;

import aeroline.nr.api.api.Dto.UserCreateDto;
import aeroline.nr.api.api.Dto.UserDto;
import aeroline.nr.api.api.Dto.UserUpdateDto;

public interface UserService {
    UserDto getById(int id);

    List<UserDto> getAll();

    UserDto create(UserCreateDto dto);

    UserDto update(int id, UserUpdateDto dto);

    void delete(int id);

}
