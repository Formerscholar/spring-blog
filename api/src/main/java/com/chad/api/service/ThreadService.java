package com.chad.api.service;

import com.chad.api.dao.mapper.ArticleMapper;
import com.chad.api.dao.pojo.Article;

public interface ThreadService {
	void updateViewCount(ArticleMapper articleMapper, Article article);
}
