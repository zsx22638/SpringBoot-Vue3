package com.zsx.service;

import com.zsx.controller.CategoryController;
import com.zsx.pojo.Category;

import java.util.List;

public interface CategoryService {
    int addCategory(String cateName, String cateAlias,Integer createUser);

    List<Category> listCategory();

    Category getCategory(Integer id);

    int updateCategory(Category category);

    int deleteCategory(Integer id);
}
