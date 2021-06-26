package com.chad.api.service;


import com.chad.api.helper.Result;

public interface CommentsService {


    Result commentsByArticleId(Long articleId);
}
