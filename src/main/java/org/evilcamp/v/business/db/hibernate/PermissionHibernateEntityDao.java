package org.evilcamp.v.business.db.hibernate;

import org.evilcamp.v.framework.db.hibernate.HibernateEntityDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The Class UserEntityDao.
 * user查询类,
 * 若用户定义的Dao类继承HibernateEntityDao，要添加init初始化方法，务必要先调用一下父类的init方法，以完成泛型类型的写入。
 * 
 * 
 * @date 2014-8-7 18:01:25
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,spring_web 1.0
 */
@Repository
public class PermissionHibernateEntityDao extends HibernateEntityDao<PermissionHibernateEntity> {
	public void init(){
		super.init();
	}
	
	

	public void queryByTemplate(){
		List<PermissionHibernateEntity> result = findByHibernateTemplate("from PermissionHibernateEntity");
		if (result!=null&&result.size()>0) {
			for (PermissionHibernateEntity t : result) {
				System.out.println(t.toString());
			}
		}
	}
	
	/**
	 * queryUser.
	 */
	public void queryByHql(){
		List<PermissionHibernateEntity> result = findByHql("from PermissionHibernateEntity");
		if (result!=null&&result.size()>0) {
			for (PermissionHibernateEntity t : result) {
				System.out.println(t.toString());
			}
		}
	}
	
	public void queryByCriteria(){
		List<Criterion> conditions = new ArrayList<Criterion>();
		conditions.add(Restrictions.like("name", "x"));
		conditions.add(Restrictions.eq("age", 17));
		List<PermissionHibernateEntity> result =findByCriteria(conditions);
		if (result!=null&&result.size()>0) {
			for (PermissionHibernateEntity t : result) {
				System.out.println(t.toString());
			}
		}
		
	}
	@SuppressWarnings("rawtypes")
	public void queryByNativeSql(){
		List result = findByNativeSql("select * from permission");
		if (result!=null&&result.size()>0) {
			for (Object o : result) {
				System.out.println(o.toString());
			}
		}
		
	}
	
	
	/**
	 * insert.
	 */
	public void insert(){
		PermissionHibernateEntity r = new PermissionHibernateEntity();
		r.setName("role");
		r.setRemark("remark");
		r.setPath("/");
		r.setCreateTime(new Date());
		save(r);
	}

}
