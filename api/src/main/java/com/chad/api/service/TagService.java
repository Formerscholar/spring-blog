package com.chad.api.service;

import com.chad.api.helper.Result;
import com.chad.api.vo.TagVo;

import java.util.List;

public interface TagService {
  /**
   * 被文章标签id
   *
   * @param articleId 文章的id
   * @return {@link List<TagVo>}
   */
  List<TagVo> getTagsByArticleId(long articleId);


  /**
   * @param limit 限制
   * @return {@link Result}
   */
  Result hots(int limit);
}
