package com.chad.api.controller;

import com.chad.api.helper.Result;
import com.chad.api.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;

	@GetMapping("article/{id}")
	public Result comments(@PathVariable("id") Long articleId) {

		return commentsService.commentsByArticleId(articleId);

	}
}
