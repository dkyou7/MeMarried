package com.example.demo.service;

import com.example.demo.model.Memo;
import com.example.demo.repository.MemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Memo> retrieve(final String nickname) {
        return memoRepository.findByNickname(nickname);
    }

    public List<Memo> update(final Memo entity) {
        validate(entity);

        final Optional<Memo> original = memoRepository.findById(entity.getId());

        original.ifPresent(memo -> {
            memo.setContent(entity.getContent());
            memo.setAnonymous(entity.isAnonymous());

            memoRepository.save(memo);
        });

        return retrieve(entity.getNickname());
    }

    public List<Memo> delete(final Memo entity) {
        validate(entity);

        try {
            memoRepository.delete(entity);
        }catch (Exception e){
            log.error("Error deleting entity ", entity.getId(),e);

            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        return retrieve(entity.getNickname());
    }
}
