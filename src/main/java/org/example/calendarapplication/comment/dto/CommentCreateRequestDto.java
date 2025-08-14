package org.example.calendarapplication.comment.dto;

import lombok.Getter;

// 댓글 생성용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class CommentCreateRequestDto {
    private String content;
}
