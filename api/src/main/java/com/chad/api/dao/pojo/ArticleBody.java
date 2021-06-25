package com.chad.api.dao.pojo;

import lombok.Data;

@Data
public class ArticleBody {

    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}