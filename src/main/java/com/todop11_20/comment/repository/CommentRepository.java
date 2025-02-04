package com.todop11_20.comment.repository;

import com.todop11_20.common.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
