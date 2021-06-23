package com.chad.api.service;

import com.chad.api.helper.Result;
import com.chad.api.vo.TagVo;

import java.util.List;

public interface TagService {
  List<TagVo> getTagsByArticleId(long articleId);


  Result hots(int limit);
}
