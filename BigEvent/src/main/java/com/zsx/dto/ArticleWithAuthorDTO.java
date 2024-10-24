package com.zsx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleWithAuthorDTO {
    private Integer id;//主键ID
    private String title;//文章标题
    private String content;//文章内容
    private String coverImg;//封面图像
    private LocalDateTime pub_date;//(发布时间)创建时间
    private String state;//发布状态 已发布|草稿

    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private String categoryName;//分类名称


    private String username;//用户名
    private String nickname;//昵称


}
