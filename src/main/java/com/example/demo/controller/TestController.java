package com.example.demo.controller;

import com.example.demo.dto.RequestBodyDTO;
import com.example.demo.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String testController(){
        return "Hello world!";
    }

    @GetMapping("/{id}")
    public String testConVa(@PathVariable(required = false) int id){
        return "this is : " + id;
    }

    @GetMapping("testRequestParam")
    public String testConVas(@RequestParam(required = false) int id){
        return "this is param: " + id;
    }

    @GetMapping("testRequestBody")
    public String testConVass(@RequestBody RequestBodyDTO dto){
        return "this is param: " + dto.getId()+" "+dto.getMessage();
    }

    @GetMapping("testResponseBody")
    public ResponseDTO<String> testconVas(){
        List<String> list = new ArrayList<>();
        list.add("안녕? 잘 지내?");
        list.add("별 일 없지? 나도 잘 지내.");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("testResponseEntity")
    public ResponseEntity<?> testconVasentity(){
        List<String> list = new ArrayList<>();
        list.add("안녕? 잘 지내?");
        list.add("별 일 없지? 나도 잘 지내.");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }
}
