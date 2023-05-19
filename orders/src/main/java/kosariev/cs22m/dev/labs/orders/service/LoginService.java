package kosariev.cs22m.dev.labs.orders.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {
    @Autowired
    private CustomersApi customersApi;

    public ResponseDTO login(UserDTO loginDTO) {
        UserDTO userDTO = customersApi.getUserByLogin(loginDTO.getLogin());
        ResponseDTO responseDTO = new ResponseDTO();

        if (userDTO.getPassword() != null && userDTO.getPassword().equals(loginDTO.getPassword())) {
            responseDTO.setMessage("Authenticated");
            return responseDTO;
        }

        responseDTO.setMessage("Not authenticated");
        return responseDTO;
    }
}
