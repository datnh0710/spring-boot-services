package com.datnh.springboot.mycoolapp.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author datnh0710
 * @created 02/07/2024 - 11:28 AM
 * @packege com.datnh.springboot.mycoolapp.restcontroller
 * @project springboot
 */
@RestController
public class CoolAppController {

    @GetMapping("/")
    public String sayHello(){
        return "Hello From the Cool APP!";
    }

}
