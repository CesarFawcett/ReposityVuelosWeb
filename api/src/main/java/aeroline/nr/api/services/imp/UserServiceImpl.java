package aeroline.nr.api.services.imp;

import aeroline.nr.api.repositories.UserRepository;
import aeroline.nr.api.services.UserService;
import lombok.RequiredArgsConstructor;
import aeroline.nr.api.api.Dto.UserCreateDto;
import aeroline.nr.api.api.Dto.UserDto;
import aeroline.nr.api.api.Dto.UserMapper;
import aeroline.nr.api.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto create(UserCreateDto dto) {
        User user = mapper.toEntity(dto);
        User saved = repository.save(user);
        return mapper.toUserDto(saved);
    }

    @Override
    public UserDto getById(int id) {
        return repository.findById(id)
                .map(mapper::toUserDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(int id, UserCreateDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullname(dto.getFullname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        User updated = repository.save(user);

        return mapper.toUserDto(updated);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

}
