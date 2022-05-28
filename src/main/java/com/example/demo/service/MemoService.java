package com.example.demo.service;

import com.example.demo.model.Memo;
import com.example.demo.repository.MemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemoService {

    @Autowired
    private MemoRepository memoRepository;

    public String testService(){
        Memo memo = Memo.builder().content("안녕?").build();

        memoRepository.save(memo);

        // 바로 ID로 찾는게 가능하다.
        Memo findMemo = memoRepository.findById(memo.getId()).get();
        return findMemo.getContent();
    }

    public List<Memo> create(final Memo memo){
        // 1. 검증
        validate(memo);

        // 2. save
        memoRepository.save(memo);

        log.info("Entity id : {} is saved.", memo.getId());

        // 3. findAll
        return memoRepository.findAll();
    }

    private void validate(Memo memo) {
        if(memo == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if(memo.getContent() == null){
            log.warn("Unknown content.");
            throw new RuntimeException("Unknown content.");
        }
    }

}
