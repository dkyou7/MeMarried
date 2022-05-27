package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Memo {
    private String id;
    private String phoneNumber;
    private String content;
    private boolean anonymous;
    private String nickname;
}
