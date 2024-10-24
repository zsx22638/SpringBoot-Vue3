package com.zsx.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zsx.dto.ArticleWithAuthorDTO;
import com.zsx.pojo.Article;

public interface ArticleService {
    int addArticle(Article article);

    public PageInfo<Article> getArticleList(int pageNum, int pageSize, String cateId, String state);

    int deleteArticleById(Integer id);

    ArticleWithAuthorDTO getArticleWithAuthorById(Integer id);
}
