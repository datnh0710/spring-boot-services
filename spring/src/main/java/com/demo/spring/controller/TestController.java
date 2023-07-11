package com.demo.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author datnh0710
 * @created 01/06/2023 - 11:14 PM
 * @packege com.demo.controller
 * @project spring
 */

@RestController
// url/test
@RequestMapping(value = "/test")
public class TestController {
    // url/test/test1
    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }

    // url/test/test2?name=?id=abc&name=datnh
    @GetMapping("/test2")
    public String test2(@RequestParam String id, @RequestParam(name = "name", required = true, value = "datnh") String name){
        return "get with @RequestParam  " + id;
    }

    // url/test/test3/{id}
    @GetMapping("/test3/{id}")
    public String test3(@PathVariable("id") String id){
        return "get id @PathVariable /test3/"+ id;
    }

    // url/test/test4/{id}
    @DeleteMapping("/test4/{id}")
    public String test4(@PathVariable("id") String id){
        return "delete id with @PathVariable /test4/"+ id;
    }

    //url/test/test5/{id}
    @PutMapping("/test5/{id}")
    public String test5(@PathVariable("id") String id, @RequestBody Object object){
        return "put id with @PathVariable /test5/" + id;
    }

    //url/test/test6/{id}
    @PutMapping("/test6/{id}")
    public ResponseEntity<Object> test6(@PathVariable("id") String id, @RequestBody Object object){
        return new ResponseEntity<>("put id with @PathVariable and @RequestBody  /test6/" + id, HttpStatus.OK);
    }

    //url/test/test7
    @PostMapping("/test7")
    public ResponseEntity<Object> test7(@RequestBody Object obj){
        return new ResponseEntity<>("Post with @RequestBody /test7", HttpStatus.OK);
    }






}
