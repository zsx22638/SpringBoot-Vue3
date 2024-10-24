package com.zsx.service.Impl;

import com.zsx.controller.CategoryController;
import com.zsx.mapper.CategoryMapper;
import com.zsx.pojo.Category;
import com.zsx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(String cateName, String cateAlias,Integer createUser) {
        return categoryMapper.addCategory(cateName, cateAlias,createUser);
    }

    @Override
    public List<Category> listCategory() {
        return categoryMapper.listCategory();
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryMapper.getCategory(id);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.deleteCategory(id);
    }
}
