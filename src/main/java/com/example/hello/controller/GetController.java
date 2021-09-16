package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetController {

    //요즘 방식, 각 메소드별로 매핑 어노테이션이 있음.
    @GetMapping(path = "/hello")
    public String getHello() {
        return "get Hello";
    }

    //예전 방식
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi() {
        return "get hi";
    }

    //url에서 변하는 값 받기
    @GetMapping("/path-variable/{ln}")
//    public String pathVariable(@PathVariable String name, ) {
    public String pathVariable(@PathVariable(name = "ln") String pathName) {
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    //쿼리 파라미터 받기
    //localhost/api/get/query-param?name=hyejin&age=27

    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();

            sb.append(entry.getKey() + "=" + entry.getValue() + "\n");
        });
        return sb.toString();
    }

    @GetMapping("/query-param2")
    public String queryParam2(
            @RequestParam String name,
            @RequestParam int age
    ) {

        StringBuilder sb = new StringBuilder();

        System.out.println(name);
        System.out.println(age);
        System.out.println();

        sb.append(name).append("\n").append(age);

        return sb.toString();
    }


    //주로 현업에서 사용하는 방법,
    @GetMapping("/query-param3")
    public String queryParam3(
            UserRequest userRequest
    ) {

        StringBuilder sb = new StringBuilder();

        System.out.println(userRequest.getAge());
        System.out.println(userRequest.getName());
        System.out.println();


        return userRequest.getName();
    }

}


