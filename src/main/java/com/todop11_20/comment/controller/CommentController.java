package com.todop11_20.comment.controller;


import com.todop11_20.comment.model.request.CommentRequestDto;
import com.todop11_20.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<String> createCommentApi(@RequestBody CommentRequestDto requestDto) {

    return ResponseEntity.ok()
        .body(
            commentService
                .createComment(requestDto)
                .getCreateMessage()
        );
  }

}
