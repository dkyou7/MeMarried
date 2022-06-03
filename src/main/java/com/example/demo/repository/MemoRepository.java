package com.example.demo.repository;

import com.example.demo.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, String> {
    List<Memo> findByNickname(String userId);

    List<Memo> findByUserId(String userId);
}
