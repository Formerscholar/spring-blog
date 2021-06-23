package com.chad.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chad.api.dao.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
  List<Tag> getTagsByArticleId(long articleId);

  List<Long> findHotsTagsIds(int limit);

  List<Tag> findTagsByIds(List<Long> tagsIds);
}
