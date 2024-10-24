package com.zsx.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsx.dto.ArticleWithAuthorDTO;
import com.zsx.mapper.ArticleMapper;
import com.zsx.pojo.Article;
import com.zsx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int addArticle(Article article) {
        return articleMapper.insert(article);
    }


    public PageInfo<Article> getArticleList(int pageNum, int pageSize, String cateId, String state) {
        // 使用 PageHelper 开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 构建查询条件
        Map<String, Object> params = new HashMap<>();
        if (cateId != null && !cateId.isEmpty()) {
            params.put("categoryId", cateId);
        }
        if (state != null && !state.isEmpty()) {
            params.put("state", state);
        }

        // 执行查询
        List<Article> articles = articleMapper.selectArticleList(params);

        System.out.println(
                "articles = " + articles
        );
        // 返回分页后的结果
        return new PageInfo<>(articles);
    }

    @Override
    public int deleteArticleById(Integer id) {
        return articleMapper.deleteArticleById(id);
    }

    public ArticleWithAuthorDTO getArticleWithAuthorById(Integer articleId) {
        return articleMapper.getArticleWithAuthorById(articleId);
    }


}
