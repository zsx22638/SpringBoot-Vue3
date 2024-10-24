package com.zsx.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@Data
public class Article {
    private Integer id;//主键ID
    private String title;//文章标题
    private String content;//文章内容
    private String coverImg;//封面图像
    private String state;//发布状态 已发布|草稿
    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    public Article(String title, String content, String coverImg, String state, Integer categoryId, Integer userId) {
        this.title = title;
        this.content = content;
        this.coverImg = coverImg;
        this.state = state;
        this.categoryId = categoryId;
        this.createUser = userId;
//        this.createTime = LocalDateTime.now(); // 设置创建时间
//        this.updateTime = LocalDateTime.now(); // 设置更新时间
    }




}
