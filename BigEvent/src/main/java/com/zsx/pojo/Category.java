package com.zsx.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Category {
    private Integer id;//主键ID
    private String categoryName;//分类名称
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间

    public Category(Integer id, String categoryName, String categoryAlias) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryAlias = categoryAlias;
    }
}
