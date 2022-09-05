package com.xiaotu.shiro.auth.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = -5440372534300871944L;

    private Integer id;
    private String username;
    private String password;
    private Date createTime;
    private String status;

    public User() {
    }

    public User(Integer id, String username, String password, Date createTime, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
