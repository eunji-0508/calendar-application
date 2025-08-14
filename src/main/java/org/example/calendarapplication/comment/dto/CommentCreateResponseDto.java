package org.example.calendarapplication.comment.dto;

// 댓글 생성용 ResponseDto
// ResponseDto에는 final 키워드, 생성자 넣어야함
public class CommentCreateResponseDto {
    private final Long id;          // 댓글 ID
    private final String content;   // 댓글 내용
    private final Long userId;      // 유저 ID
    private final Long calendarId;          // 일정 ID

    // 생성자
    public CommentCreateResponseDto(Long id, String content, Long userId, Long calendarId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.calendarId = calendarId;
    }
}
