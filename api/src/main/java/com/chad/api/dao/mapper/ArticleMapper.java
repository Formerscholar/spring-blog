package com.chad.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chad.api.dao.dos.Archives;
import com.chad.api.dao.pojo.Article;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

  List<Archives> listArchives();
}
