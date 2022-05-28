package com.example.demo.controller;

import com.example.demo.dto.MemoDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Memo;
import com.example.demo.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @PostMapping
    public ResponseEntity<?> createMemo(@RequestBody MemoDTO dto){
        try {
            String tmpContent = "안녕? 잘 지내니?";

            Memo memo = MemoDTO.toEntity(dto);

            memo.setId(null);
            memo.setContent(tmpContent);

            List<Memo> entities = memoService.create(memo);

            List<MemoDTO> dtos = entities.stream().map(MemoDTO::new).collect(Collectors.toList());

            ResponseDTO<MemoDTO> response = ResponseDTO.<MemoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MemoDTO> response = ResponseDTO.<MemoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

}
