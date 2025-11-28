package aeroline.nr.api.services.imp;

import aeroline.nr.api.repositories.UserRepository;
import aeroline.nr.api.services.UserService;
import lombok.RequiredArgsConstructor;
import aeroline.nr.api.api.Dto.UserCreateDto;
import aeroline.nr.api.api.Dto.UserDto;
import aeroline.nr.api.api.Dto.UserMapper;
import aeroline.nr.api.api.Dto.UserUpdateDto;
import aeroline.nr.api.entities.User;
import aeroline.nr.api.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto getById(int id) {
        return repository.findById(id)
                .map(mapper::toUserDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toUserDto)
                .toList();
    }

    @Override
    public UserDto create(UserCreateDto dto) {
        User user = mapper.toEntity(dto);
        return mapper.toUserDto(repository.save(user));
    }

    @Override
    public UserDto update(int id, UserUpdateDto dto) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setFullname(dto.getFullname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        User updated = repository.save(user);

        return mapper.toUserDto(updated);
    }

    @Override
    public void delete(int id) {
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        repository.delete(user);
    }

}
