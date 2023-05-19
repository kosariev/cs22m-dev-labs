package kosariev.cs22m.dev.labs.orders.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@FeignClient(name = "User", url = "localhost:8081")
public interface CustomersApi {

    @GetMapping("/user/get")
    @ResponseBody UserDTO getUserByLogin(@RequestParam String login);

    @GetMapping("/user/check")
    @ResponseBody boolean checkUserById(@RequestParam int id);
}
