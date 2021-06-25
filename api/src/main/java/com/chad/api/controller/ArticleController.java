package com.chad.api.controller;

import com.chad.api.dao.dos.Archives;
import com.chad.api.helper.Result;
import com.chad.api.service.ArticleService;
import com.chad.api.vo.ArticleVo;
import com.chad.api.vo.PageVo;
import com.chad.api.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;


	@PostMapping("article_list")
	public Result listArticle(@RequestBody PageParams pageParams) {
		PageVo pageVo = articleService.listArticle(pageParams);
		return Result.success(pageVo);
	}

	@PostMapping("hot")
	public Result hotArticle() {
		int limit = 5;
		List<ArticleVo> articleVos = articleService.hotArticle(limit);
		return Result.success(articleVos);
	}

	@PostMapping("new")
	public Result newArticle() {
		int limit = 5;
		List<ArticleVo> articleVos = articleService.newArticle(limit);
		return Result.success(articleVos);
	}

	@PostMapping("listArchives")
	public Result listArchives() {
		List<Archives> articleVos = articleService.listArchives();
		return Result.success(articleVos);
	}

	@PostMapping("detaid/{id}")
	public Result detail(@PathVariable("id") Long id) {
		ArticleVo articleVo = articleService.findArticleById(id);
		return Result.success(articleVo);
	}
}
