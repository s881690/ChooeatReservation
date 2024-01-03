package service.impl;

import model.Customer;
import org.springframework.stereotype.Service;
import service.CustomerLoginService;
@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {
    @Override
    public Customer customerLogin(String customerName, String password) {
        //檢查前端來的帳號密碼是否為空值
        //先不管JWT
        //拿帳號密碼去後端找是否有這筆資料
        return null;
    }
}
