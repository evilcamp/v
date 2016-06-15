package org.evilcamp.v.business.security.user.dto;

import org.evilcamp.v.business.security.user.dao.hibernate.UserHibernateEntity;
import org.evilcamp.v.framework.utils.DateUtil;

public class UserDto {

    private String id;

    private String userName;

    private String password;

    private String nickName;

    private String remark;

    private String createTime;

    public UserDto(UserHibernateEntity entity){
        this.id = entity.getId()+"";
        this.userName = entity.getUserName();
        this.password = entity.getPassword();
        this.nickName = entity.getNickName();
        this.remark = entity.getRemark();
        this.createTime = DateUtil.getCommonFormat(entity.getCreateTime());
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserHibernateEntity{" +
                "createTime=" + createTime +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
