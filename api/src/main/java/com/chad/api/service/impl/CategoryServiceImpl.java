package com.chad.api.service.impl;

import com.chad.api.dao.mapper.CategoryMapper;
import com.chad.api.dao.pojo.Category;
import com.chad.api.service.CategoryService;
import com.chad.api.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Long id){
        Category category = categoryMapper.selectById(id);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
