package com.chad.api.service;

import com.chad.api.vo.CategoryVo;

public interface CategoryService {
	CategoryVo findCategoryById(Long categoryId);
}
