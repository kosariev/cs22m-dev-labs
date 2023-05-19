package kosariev.cs22m.dev.labs.orders.controller;

import kosariev.cs22m.dev.labs.orders.service.LoginService;
import kosariev.cs22m.dev.labs.orders.service.ResponseDTO;
import kosariev.cs22m.dev.labs.orders.service.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public @ResponseBody ResponseDTO login(@RequestBody UserDTO userDTO) {
        return loginService.login(userDTO);
    }
}
