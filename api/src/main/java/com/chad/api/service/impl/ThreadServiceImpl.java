package com.chad.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chad.api.dao.mapper.ArticleMapper;
import com.chad.api.dao.pojo.Article;
import com.chad.api.service.ThreadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadServiceImpl implements ThreadService {
	@Override
	@Async("taskExecutor")
	public void updateViewCount(ArticleMapper articleMapper, Article article) {

		Article articleUpdate = new Article();
		articleUpdate.setViewCounts(article.getViewCounts() + 1);
		LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Article::getId, article.getId());
		queryWrapper.eq(Article::getViewCounts, article.getViewCounts());
		articleMapper.update(articleUpdate, queryWrapper);
		//try {
		//	//睡眠5秒 证明不会影响主线程的使用
		//	Thread.sleep(5000);
		//	System.out.println("更新完成");
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}
	}
}
