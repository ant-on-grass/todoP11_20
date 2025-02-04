package com.todop11_20.comment.service;

import com.todop11_20.comment.model.request.CommentRequestDto;
import com.todop11_20.comment.model.response.CommentResponseDto;
import com.todop11_20.comment.repository.CommentRepository;
import com.todop11_20.common.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

  private final CommentRepository commentRepository;

  public CommentResponseDto createComment(CommentRequestDto requestDto) {
    Comment comment = commentRepository.save(Comment.of(requestDto));
    return CommentResponseDto.of(comment);
  }

}
