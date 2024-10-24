package com.zsx.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zsx.dto.ArticleWithAuthorDTO;
import com.zsx.pojo.Article;
import com.zsx.pojo.Result;
import com.zsx.service.ArticleService;
import com.zsx.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //发布文章
    @PostMapping("/add")
    public Result addArticle(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String coverImg,
            @RequestParam String state,
            @RequestParam Integer categoryId
    ) {

        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Article article = new Article(title,content,coverImg,state,categoryId,userId);

        System.out.println("User ID: " + userId);


        System.out.println(article);
        int row = articleService.addArticle(article);
        if (row == 0) {
            return Result.error("发布失败");
        }
        return Result.success("发布成功");
    }

    //获取文章列表 分页器
    // 获取文章列表 分页器
    @GetMapping("/list")
    public Result listArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(required = false) String categoryId,
                              @RequestParam(required = false) String state

    ) {
        // 调用服务层查询分页数据
        PageInfo<Article> page = articleService.getArticleList(pageNum, pageSize,categoryId,state);
        return Result.success(page);
    }

    //删除文章
    @DeleteMapping("/delete/{id}")
    public Result deleteArticle(@PathVariable Integer id){
        int row = articleService.deleteArticleById(id);
        if (row == 0) {
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

    @GetMapping("/detail/{id}")
    public Result getArticleWithAuthor(@PathVariable("id") Integer id) {
        ArticleWithAuthorDTO articleWithAuthorDTO = articleService.getArticleWithAuthorById(id);
        if(articleWithAuthorDTO == null){
            Result.error("查询失败");
        }
        return Result.success("查询成功！",articleWithAuthorDTO);
    }


}
