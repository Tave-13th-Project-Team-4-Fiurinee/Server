package com.example.fiurinee.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HelloController {
    @GetMapping("/docker")
    @ResponseBody
    public String sayHello() {
        return "ci/cd 테스트 우왕 드디어";
    }
}
