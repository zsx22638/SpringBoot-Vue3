package com.zsx.mapper;


import com.zsx.controller.CategoryController;
import com.zsx.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {


    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) values(#{cateName},#{cateAlias},#{createUser},now(),now())")
    int addCategory(String cateName, String cateAlias,int createUser);

    @Select("select * from category")
    List<Category> listCategory();

    @Select("select * from category where id = #{id}")
    Category getCategory(Integer id);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    int updateCategory(Category category);

    @Delete("delete from category where id = #{id}")
    int deleteCategory(Integer id);
}
