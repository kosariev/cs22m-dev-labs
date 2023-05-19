package kosariev.cs22m.dev.labs.customers.service;

import kosariev.cs22m.dev.labs.customers.model.UserDTO;
import kosariev.cs22m.dev.labs.customers.model.UserEntity;
import kosariev.cs22m.dev.labs.customers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean checkUserById(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public UserDTO getUserByLogin(String login) {
        UserDTO userDTO = new UserDTO();
        Optional<UserEntity> optionalUserEntity = userRepository.findByLogin(login);

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userDTO.setLogin(userEntity.getLogin());
            userDTO.setPassword(userEntity.getPassword());
        }

        return userDTO;
    }

    public void addUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userRepository.save(userEntity);
    }
}
