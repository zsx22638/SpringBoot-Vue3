package com.zsx.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDateTime;
@Data
//需要无参构造，这对读取很重要
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @NotNull(message="id不能为空")
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore
    private String password;//密码
    @NotNull(message="nickname不能为空")
    @Size(min = 2, max = 30, message = "用户名长度必须在2-30之间")
    private String nickname;//昵称
    @Email(message = "邮箱格式不正确")
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
    // 构造函数
    public User(String username, String password, String nickname, String email, String userPic, LocalDateTime createTime, LocalDateTime updateTime) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.userPic = userPic;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}
