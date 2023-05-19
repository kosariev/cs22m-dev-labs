package kosariev.cs22m.dev.labs.customers.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kosariev.cs22m.dev.labs.customers.model.UserDTO;
import kosariev.cs22m.dev.labs.customers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User authentication", description = "Methods for user authentication")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @Operation(summary = "Create a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = UserDTO.class)), required = true)
    )
    public @ResponseBody void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @Operation(summary = "Get user by login")
    @ApiResponse(
            responseCode = "200",
            description = "Result of authentication - Authenticated / Not authenticated",
            content = @Content(schema = @Schema(implementation = UserDTO.class))
    )
    @GetMapping("/get")
    public @ResponseBody UserDTO getUserByLogin(@RequestParam String login) {
        return userService.getUserByLogin(login);
    }

    @Operation(summary = "Check user by id")
    @ApiResponse(
            responseCode = "200",
            description = "Result - true/false"
    )
    @GetMapping("/check")
    public @ResponseBody boolean checkLoginById(@RequestParam Long id) {
        return userService.checkUserById(id);
    }
}