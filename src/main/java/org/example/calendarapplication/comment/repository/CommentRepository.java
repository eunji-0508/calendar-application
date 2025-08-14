package org.example.calendarapplication.comment.repository;

import org.example.calendarapplication.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository에 @Repository 포함되어 있기 때문에 안써도 됨
// <클래스명, 기본키>
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
