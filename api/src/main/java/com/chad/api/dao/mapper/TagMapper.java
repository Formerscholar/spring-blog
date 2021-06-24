package com.chad.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chad.api.dao.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
  /**
   * 被文章标签id
   *
   * @param articleId 文章的id
   * @return {@link List<Tag>}
   */
  List<Tag> getTagsByArticleId(long articleId);

  /**
   * 发现热点标签id
   *
   * @param limit 限制
   * @return {@link List<Long>}
   */
  List<Long> findHotsTagsIds(int limit);

  /**
   * 通过id找到标签
   *
   * @param tagsIds 标签id
   * @return {@link List<Tag>}
   */
  List<Tag> findTagsByIds(List<Long> tagsIds);
}
