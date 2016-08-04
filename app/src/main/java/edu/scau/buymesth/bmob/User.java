package edu.scau.buymesth.bmob;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by IamRabbit on 2016/8/3.
 */
public class User extends BmobUser {
    private String nickname;//昵称
    private Integer age = 0;//年龄
    private String gender = "secret";//性别
    private Integer exp = 0;//经验值

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
}
