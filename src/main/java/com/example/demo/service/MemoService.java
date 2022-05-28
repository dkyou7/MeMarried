package com.example.demo.service;

import com.example.demo.model.Memo;
import com.example.demo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
