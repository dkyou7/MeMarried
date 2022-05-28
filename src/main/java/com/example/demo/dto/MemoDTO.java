package com.example.demo.dto;

import com.example.demo.model.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {
    private String id;
    private String content;
    private String nickname;
    private boolean anonymous;

    public MemoDTO(final Memo memo) {
        this.id = memo.getId();
        this.content = memo.getContent();
        this.nickname = memo.getNickname();
        this.anonymous = memo.isAnonymous();
    }

    public static Memo toEntity(final MemoDTO dto){
        return Memo.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .nickname(dto.getNickname())
                .build();
    }
}
