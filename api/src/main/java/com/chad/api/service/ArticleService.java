package com.chad.api.service;

import com.chad.api.dao.dos.Archives;
import com.chad.api.vo.ArticleVo;
import com.chad.api.vo.PageVo;
import com.chad.api.vo.params.PageParams;

import java.util.List;


public interface ArticleService {

  /**
   * 文章列表
   *
   * @param pageParams 页面参数
   * @return {@link PageVo}
   */
  PageVo listArticle(PageParams pageParams);

  /**
   * 热门文章
   *
   * @param limit 限制
   * @return {@link List<ArticleVo>}
   */
  List<ArticleVo> hotArticle(int limit);

  /**
   * 新文章
   *
   * @param limit 限制
   * @return {@link List<ArticleVo>}
   */
  List<ArticleVo> newArticle(int limit);

  /**
   * 档案列表
   *
   * @return {@link List<Archives>}
   */
  List<Archives> listArchives();

}
