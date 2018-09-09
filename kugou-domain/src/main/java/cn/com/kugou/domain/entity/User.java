package cn.com.kugou.domain.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */

public class User implements Serializable {

    private Long id;
    private String userName;
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
