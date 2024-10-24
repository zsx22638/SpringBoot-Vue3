package com.zsx.controller;

import jakarta.validation.Valid;
import com.zsx.pojo.User;
import com.zsx.service.UserService;
import com.zsx.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.zsx.utils.Md5Util;
import com.zsx.pojo.Result;
import com.zsx.utils.JwtUtil;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户注册
    @PostMapping("/register")
    public Result register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email) {
        // 非空校验
        if (username == null || username.isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            return Result.error("密码不能为空");
        }
        if (email == null || email.isEmpty()) {
            return Result.error("邮箱不能为空");
        }

        // 检查用户名格式
        if (!username.matches("^[a-zA-Z0-9_-]{3,20}$")) {
            return Result.error("用户名格式不正确，长度应在3-20个字符之间");
        }


        // 检查密码长度
        if (password.length() < 6 || password.length() > 20) {
            return Result.error("密码长度应在6-20个字符之间");
        }

        // 邮箱格式校验
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
            return Result.error("邮箱格式不正确");
        }

        // 查询用户名是否存在
        User u = userService.findUserByName(username);
        System.out.println("u:" + u);

        // 如果存在，返回错误信息
        if (u != null) {
            return Result.error("用户名已存在");
        }

        // 如果不存在，注册用户
        userService.saveUser(new User(
                username,
                Md5Util.getMD5String(password),
                username,
                email,
                "",
                LocalDateTime.now(),
                LocalDateTime.now()));

        return Result.success("注册成功");
    }

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // 非空校验
        if (username == null || username.isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            return Result.error("密码不能为空");
        }

        // 检查用户名格式
        if (!username.matches("^[a-zA-Z0-9_-]{3,20}$")) {
            return Result.error("用户名格式不正确，长度应在3-20个字符之间");
        }

        // 检查密码长度
        if (password.length() < 6 || password.length() > 20) {
            return Result.error("密码长度应在6-20个字符之间");
        }

        // 查询用户名是否存在
        User u = userService.findUserByName(username);
        System.out.println("u:" + u);

        // 如果不存在，返回错误信息
        if (u == null) {
            return Result.error("用户名不存在");
        }

        // 如果存在，判断密码是否正确
        if (!Md5Util.checkPassword(password, u.getPassword())) {
            return Result.error("密码错误!");
        }

        // 如果密码正确，生成token
        String token = JwtUtil.genToken(Map.of("id", u.getId(), "username", u.getUsername()));
//        System.out.println(JwtUtil.parseToken(token).toString());
        return Result.success("登录成功", token);
    }


    //用户信息查询
//    @GetMapping("/info")
//    public Result info(@RequestHeader("Authorization") String token) {
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            if (claims == null || !claims.containsKey("id")) {
//                return Result.error("无效的token");
//            }
//
//            Integer id;
//            try {
//                id = (Integer) claims.get("id");
//            } catch (ClassCastException e) {
//                return Result.error("token中的ID格式不正确");
//            }
//
//            User u = userService.findUserById(id);
//            return Result.success(u);
//
//        } catch (Exception e) {
//            return Result.error("未知错误");
//        }
//    }

    //用户信息查询
    @GetMapping("/info")
    public Result info() {

         Map<String,Object> map = ThreadLocalUtil.get();
         String username = (String) map.get("username");
         System.out.println("username:" + username);
         User u = userService.findUserByName(username);
         return Result.success("查询成功！",u);

    }


    //更新勇户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Valid User user, BindingResult bindingResult) {
        // 检查是否有校验错误
        if (bindingResult.hasErrors()) {
            // 获取校验错误信息并返回
            String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return Result.error(errorMessage);
        }

        int row = userService.updateUser(user);
        if (row == 0) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }


    //更新头像
    @PatchMapping("/update/avatar")
    public Result updateAvatar(@RequestParam("avatar") String avatar) {
        if (avatar == null || avatar.isEmpty()) {
            return Result.error("头像地址不能为空");
        }

        // 使用正则表达式校验头像地址
//        String regex = "^(http|https)://.*\\.(jpg|jpeg|png|gif)$";
//        if (!avatar.matches(regex)) {
//            return Result.error("头像地址格式不正确，请提供有效的 URL");
//        }
        int row = userService.updateAvatar(avatar);

        if (row == 0) {
            return Result.error("更新失败");
        }

        return Result.success("更新成功！");
    }


    //更新密码
    @PatchMapping("/update/pwd")
    public Result updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) {
        if (oldPwd == null || oldPwd.isEmpty() || newPwd == null || newPwd.isEmpty()) {
            return Result.error("旧密码和新密码不能为空");
        }

        if (oldPwd.equals(newPwd)) {
            return Result.error("新密码不能与旧密码相同");
        }

        int row = userService.updatePwd(oldPwd, newPwd);

        if (row == 0) {
            return Result.error("更新失败");
        }

        return Result.success("更新成功！");
    }


}
