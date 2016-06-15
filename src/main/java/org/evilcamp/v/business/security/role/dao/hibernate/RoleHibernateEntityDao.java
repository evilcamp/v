package org.evilcamp.v.business.security.role.dao.hibernate;

import org.evilcamp.v.framework.db.hibernate.HibernateEntityDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RoleHibernateEntityDao extends HibernateEntityDao<RoleHibernateEntity> {
	private static final Logger logger = LoggerFactory.getLogger(RoleHibernateEntityDao.class);
	public void init(){
		super.init();
	}



	public void queryByTemplate(){
		List<RoleHibernateEntity> result = findByHibernateTemplate("from RoleHibernateEntity");
		if (result!=null&&result.size()>0) {
			for (RoleHibernateEntity t : result) {
				logger.info(t.toString());
			}
		}
	}

	/**
	 * queryUser.
	 */
	public void queryByHql(){
		List<RoleHibernateEntity> result = findByHql("from RoleHibernateEntity");
		if (result!=null&&result.size()>0) {
			for (RoleHibernateEntity t : result) {
				logger.info(t.toString());
			}
		}
	}

	public void queryByCriteria(){
		List<Criterion> conditions = new ArrayList<Criterion>();
		conditions.add(Restrictions.like("name", "x"));
		conditions.add(Restrictions.eq("age", 17));
		List<RoleHibernateEntity> result =findByCriteria(conditions);
		if (result!=null&&result.size()>0) {
			for (RoleHibernateEntity t : result) {
				logger.info(t.toString());
			}
		}

	}
	@SuppressWarnings("rawtypes")
	public void queryByNativeSql(){
		List result = findByNativeSql("select * from role");
		if (result!=null&&result.size()>0) {
			for (Object o : result) {
				logger.info(o.toString());
			}
		}

	}


	/**
	 * insert.
	 */
	public void insert(){
		RoleHibernateEntity r = new RoleHibernateEntity();
		r.setName("role");
		r.setRemark("remark");
		r.setCreateTime(new Date());
		save(r);
	}

}
