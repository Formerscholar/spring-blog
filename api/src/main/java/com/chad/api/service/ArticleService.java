package com.chad.api.service;

import com.chad.api.dao.dos.Archives;
import com.chad.api.vo.ArticleVo;
import com.chad.api.vo.PageVo;
import com.chad.api.vo.params.PageParams;

import java.util.List;


public interface ArticleService {

  PageVo listArticle(PageParams pageParams);

  List<ArticleVo> hotArticle(int limit);

  List<ArticleVo> newArticle(int limit);

  List<Archives> listArchives();

}
