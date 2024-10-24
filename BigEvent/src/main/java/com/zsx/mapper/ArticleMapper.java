package com.zsx.mapper;

import com.zsx.dto.ArticleWithAuthorDTO;
import com.zsx.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    int insert(Article article);

    List<Article> selectArticleList(Map<String, Object> params);

    @Delete("delete from article where id = #{id}")
    int deleteArticleById(Integer id);

    // 根据文章ID获取文章信息和作者信息
    ArticleWithAuthorDTO getArticleWithAuthorById(@Param("articleId") Integer articleId);

}
