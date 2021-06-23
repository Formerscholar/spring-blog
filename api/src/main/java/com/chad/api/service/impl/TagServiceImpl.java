package com.chad.api.service.impl;

import com.chad.api.dao.mapper.TagMapper;
import com.chad.api.dao.pojo.Tag;
import com.chad.api.helper.Result;
import com.chad.api.service.TagService;
import com.chad.api.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
  @Autowired
  private TagMapper tagMapper;

  @Override
  public List<TagVo> getTagsByArticleId(long articleId) {

    List<Tag> tags = tagMapper.getTagsByArticleId(articleId);

    return copyList(tags);
  }

  @Override
  public Result hots(int limit) {
    List<Long> tagsIds = tagMapper.findHotsTagsIds(limit);
    if (CollectionUtils.isEmpty(tagsIds)) {
      return Result.success(Collections.emptyList());
    }
    List<Tag> tags = tagMapper.findTagsByIds(tagsIds);
    return Result.success(tags);
  }

  public TagVo copy(Tag tag) {
    TagVo tagVo = new TagVo();
    BeanUtils.copyProperties(tag, tagVo);
    return tagVo;
  }

  public List<TagVo> copyList(List<Tag> tagList) {
    List<TagVo> tagVoList = new ArrayList<>();
    for (Tag tag : tagList) {
      tagVoList.add(copy(tag));
    }
    return tagVoList;
  }
}
