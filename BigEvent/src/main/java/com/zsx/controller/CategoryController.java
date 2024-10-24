package com.zsx.controller;


import com.zsx.mapper.CategoryMapper;
import com.zsx.pojo.Category;
import com.zsx.pojo.Result;
import com.zsx.service.CategoryService;
import com.zsx.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public Result addCategory(@RequestBody Map<String, String> requestBody) {
        String cate_name = requestBody.get("cate_name");
        String cate_alias = requestBody.get("cate_alias");

        if (cate_name == null || cate_name.isEmpty() || cate_alias == null || cate_alias.isEmpty()) {
            return Result.error("分类名称不能为空");
        }

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUser = (Integer) map.get("id");

        int row = categoryService.addCategory(cate_name, cate_alias, createUser);
        if (row == 0) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功！");
    }

    //查询所有分类
    @GetMapping("/list")
    public Result listCategory() {
        List<Category> categoryList = categoryService.listCategory();
//        System.out.println(categoryList);
        return Result.success("查询成功！",categoryList);
    }

    //根据id返回文章分类详情
    @GetMapping("/{id}")
    public Result getCategory(@PathVariable Integer id) {
        Category category =  categoryService.getCategory(id);
        return Result.success("文章详情",category);
    }

    //更新文章分类
    @PutMapping()
    public Result updateCategory(@RequestBody Map<String, String> requestBody){
        Integer id = Integer.parseInt(requestBody.get("id"));
        String cate_name = requestBody.get("cate_name");
        String cate_alias = requestBody.get("cate_alias");
        Category category = new Category(id, cate_name, cate_alias);
        int row = categoryService.updateCategory(category);
        if (row == 0) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");

    }

    //删除文章分类
    @DeleteMapping("")
    public Result deleteCategory(@RequestParam Integer id){
        int row = categoryService.deleteCategory(id);
        return Result.success("删除成功");
    }


}
