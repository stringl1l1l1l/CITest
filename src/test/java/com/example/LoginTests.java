package com.example;

import com.example.entity.LoginUserWithCode;
import com.example.entity.LoginUserWithCodePwd;
import com.example.entity.LoginUserWithPwd;
import com.example.entity.ResponseResult;
import com.example.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;

@SpringBootTest
public class LoginTests {
    private final LoginUserWithPwd defaultTestUser = new LoginUserWithPwd(null,"default@qq.com", "1234");
    private LoginUserWithCodePwd registerUser;
    private LoginService loginService;
    private String lastToken;

    @BeforeEach
    void init() {
        this.loginService = BeanUtil.getBean(LoginService.class);
        assert this.loginService != null;
    }

    @Test
    void loginTest() {
        ResponseResult<Map> result = loginService.loginWithPwd(this.defaultTestUser);
        assert result.getCode() == 200;
        Map<String, String> data = result.getData();
        assert data != null;
        this.lastToken = data.get("token");
        assert StringUtils.hasText(this.lastToken);

    }
}
