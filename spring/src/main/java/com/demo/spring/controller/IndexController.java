package com.demo.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author datnh0710
 * @created 01/06/2023 - 2:21 PM
 * @packege com.demo.controller
 * @project spring
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "Hello from datnh!!!";
    }
}
