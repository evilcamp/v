package org.evilcamp.v.business.security.user.service;

import org.evilcamp.v.business.security.user.dao.hibernate.UserHibernateEntity;
import org.evilcamp.v.business.security.user.dao.hibernate.UserHibernateEntityDao;
import org.evilcamp.v.business.security.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * 偏业务的逻辑处理放在这里
 */
@Service
public class UserService {

    @Autowired
    private UserHibernateEntityDao dao;

    public UserHibernateEntity add(UserHibernateEntity entity){
        Serializable s = dao.save(entity);
        return null;
    }

    public UserHibernateEntity add(String userName, String password,String nickName){
        if(!StringTool.hasText(userName)){
            return null;
        }
        if(!StringTool.hasText(password)){
            return null;
        }

        if(!StringTool.hasText(nickName)){
            return null;
        }

        try{
            UserHibernateEntity entity = new UserHibernateEntity();
            entity.setUserName(userName);
            entity.setPassword(password);
            entity.setCreateTime(new Date());
            entity.setNickName(nickName);
            dao.save(entity);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public void delete(String id){
        UserHibernateEntity u = dao.get(Integer.parseInt(id));
        dao.delete(u);
    }

    public UserHibernateEntity get(String id){
        return dao.get(id);
    }

    public UserDto getByUserName(String userName){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userName",userName);
        List<UserHibernateEntity> entityList = dao.queryByEq(params);
        if(entityList!=null && entityList.size()==1){
            UserDto dto = new UserDto(entityList.get(0));
            return dto;
        }else{
            return null;
        }
    }

    public void update(UserHibernateEntity entity){
         dao.update(entity);
    }

    public List<UserDto> queryByLike(Map<String,Object> params){
        List<UserHibernateEntity> entityList = dao.queryByLike(params);
        List<UserDto> result = new ArrayList<UserDto>();
        for (UserHibernateEntity entity: entityList) {
            UserDto dto = new UserDto(entity);
            result.add(dto);
        }
        return result;
    }


    public List<UserDto> queryALL(){
        List<UserHibernateEntity> entityList = dao.findByHql("from UserHibernateEntity");
        List<UserDto> result = new ArrayList<UserDto>();
        for (UserHibernateEntity entity: entityList) {
            UserDto dto = new UserDto(entity);
            result.add(dto);
        }
        return result;
    }



}
