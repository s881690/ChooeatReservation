package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.CustomerLoginServiceImpl;

@RestController
public class CustomerLoginController {
    @Autowired
    CustomerLoginServiceImpl customerLoginService;

    @PostMapping("/customerLogin")
    public ResponseEntity<?> customerLogin(String customerName, String password) {

        customerLoginService.customerLogin(customerName, password);


        return null;
    }
}
