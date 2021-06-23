package com.chad.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chad.api.dao.dos.Archives;
import com.chad.api.dao.mapper.ArticleMapper;
import com.chad.api.dao.pojo.Article;
import com.chad.api.dao.pojo.SysUser;
import com.chad.api.service.ArticleService;
import com.chad.api.service.SysUserService;
import com.chad.api.service.TagService;
import com.chad.api.vo.ArticleVo;
import com.chad.api.vo.PageVo;
import com.chad.api.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleMapper articleMapper;

  @Autowired
  private TagService tagService;

  @Autowired
  private SysUserService sysUserService;

  @Override
  public PageVo listArticle(PageParams pageParams) {
    Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
    Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
    List<Article> records = articlePage.getRecords();
    List<ArticleVo> articleVoList = copyList(records, true, true);
    PageVo pageVo = setPage(articleVoList, articlePage.getPages(), articlePage.getCurrent(), articlePage.getSize(), articlePage.getTotal());
    return pageVo;
  }

  @Override
  public List<ArticleVo> hotArticle(int limit) {
    LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper
      .orderByDesc(Article::getViewCounts)
      .select(Article::getId, Article::getTitle)
      .last("limit " + limit);
    List<Article> articles = articleMapper.selectList(queryWrapper);
    List<ArticleVo> articleVoList = copyList(articles, false, false);
    return articleVoList;
  }

  @Override
  public List<ArticleVo> newArticle(int limit) {

    LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.orderByDesc(Article::getCreateDate)
      .select(Article::getId, Article::getTitle)
      .last("limit " + limit);
    List<Article> articleList = articleMapper.selectList(queryWrapper);
    List<ArticleVo> articleVoList = copyList(articleList, false, false);
    return articleVoList;
  }

  @Override
  public List<Archives> listArchives() {
    List<Archives> archives = articleMapper.listArchives();
    return archives;
  }


  private PageVo setPage(Object data, Long last_page, Long current, Long pageSize, Long total) {
    PageVo pageVo = new PageVo();
    pageVo.setData(data);
    pageVo.setLast_page(last_page);
    pageVo.setCurrent_page(current);
    pageVo.setPer_page(pageSize);
    pageVo.setTotal(total);
    return pageVo;
  }

  private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
    List<ArticleVo> articleVoList = new ArrayList<>();
    for (Article record : records) {
      articleVoList.add(copy(record, isTag, isAuthor));
    }
    return articleVoList;
  }

  private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
    ArticleVo articleVo = new ArticleVo();
    BeanUtils.copyProperties(article, articleVo);
    articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
    if (isTag) {
      Long articleId = article.getId();
      articleVo.setTags(tagService.getTagsByArticleId(articleId));
    }
    if (isAuthor) {
      Long authorId = article.getAuthorId();
      SysUser user = sysUserService.getUserById(authorId);
      articleVo.setAuthor(user.getNickname());
    }
    return articleVo;
  }
}
