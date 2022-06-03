package com.example.demo.controller;

import com.example.demo.dto.MemoDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Memo;
import com.example.demo.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @PostMapping
    public ResponseEntity<?> createMemo(@AuthenticationPrincipal String userId,
                                        @RequestBody MemoDTO dto){
        try {

            Memo memo = MemoDTO.toEntity(dto);

            memo.setId(null);
            memo.setUserId(userId);

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

    @GetMapping
    public ResponseEntity<?> retrieveMemoList(@AuthenticationPrincipal String userId){
        List<Memo> entities = memoService.retrieve(userId);

        List<MemoDTO> dtos = entities.stream().map(MemoDTO::new).collect(Collectors.toList());

        ResponseDTO<MemoDTO> response = ResponseDTO.<MemoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateMemo(@AuthenticationPrincipal String userId,
                                        @RequestBody MemoDTO dto){
        Memo memo = MemoDTO.toEntity(dto);

        memo.setUserId(userId);

        List<Memo> entities = memoService.update(memo);

        List<MemoDTO> dtos = entities.stream().map(MemoDTO::new).collect(Collectors.toList());

        ResponseDTO<MemoDTO> response = ResponseDTO.<MemoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@AuthenticationPrincipal String userId,
                                    @RequestBody MemoDTO dto){
        try {
            Memo entity = MemoDTO.toEntity(dto);

            entity.setUserId(userId);

            List<Memo> entities = memoService.delete(entity);

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
