package aeroline.nr.api.services;

import java.util.List;

import aeroline.nr.api.api.Dto.UserCreateDto;
import aeroline.nr.api.api.Dto.UserDto;

public interface UserService {
    UserDto create(UserCreateDto dto);

    UserDto getById(int id);

    List<UserDto> getAll();

    UserDto update(int id, UserCreateDto dto);

    void delete(int id);
}
