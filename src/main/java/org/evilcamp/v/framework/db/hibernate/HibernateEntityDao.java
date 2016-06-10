package org.evilcamp.v.framework.db.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * The Class HibernateEntityDao.
 * 
 * HibernateEntityDao继承HibernateBaseDao的目的只是为了获取泛型类型,
 * 用来为hibernate查询接口提供查询参数
 * 
 * @param <T>
 *            the generic type
 * @date 2014-8-18 11:46:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,spring_web 1.0
 */
@Repository
public class HibernateEntityDao<T> extends HibernateBaseDao<T> {

	/** hibernateTemplate. */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/** 用来获取泛型类型. */
	private Class<T> entityClass;

	private static final Logger logger = LoggerFactory.getLogger(HibernateEntityDao.class);

	/**
	 * init.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		Type genericSuperClass = getClass().getGenericSuperclass();
		if (genericSuperClass instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) genericSuperClass).getActualTypeArguments();
			String className = p[0].toString();
			// 检查T泛型信息
			if(!className.equals("T")){
				entityClass = (Class)p[0];
				System.out.println("未发现泛型T");
				System.out.println("获取泛型" + (Class)p[0]);
			}else{
				System.out.println("发现泛型T");
			}
		} else {
			System.out.println("类型不匹配1...");
		}

	}

	/**
	 * Gets hibernateTemplate.
	 *
	 * @return hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * Sets hibernateTemplate.
	 *
	 * @param hibernateTemplate
	 *            comments
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * Gets sessionFactory.
	 *
	 * @return sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return this.hibernateTemplate.getSessionFactory();
	}

	/**
	 * save.
	 *
	 * @param t
	 *            comments
	 */
	public Serializable save(T t) {
		logger.info("connections info {}",hibernateTemplate.getSessionFactory().getAllCollectionMetadata());

		return hibernateTemplate.save(t);
	}

	/**
	 * delete.
	 *
	 * @param t
	 *            comments
	 */
	public void delete(T t) {
		hibernateTemplate.delete(t);
	}

	/**
	 * update.
	 *
	 * @param t
	 *            comments
	 */
	public void update(T t) {
		hibernateTemplate.update(t);
	}

	/**
	 * get.
	 *
	 * @param id comments
	 * @return T
	 */
	public T get(Serializable id) {
		return  (T) hibernateTemplate.get(entityClass, id);
	}

	/**
	 * load.
	 *
	 * @param id comments
	 * @return T
	 */
	public T load(Serializable id) {
		return (T) hibernateTemplate.load(entityClass, id);
	}

	/**
	 * findByHibernateTemplate.
	 *
	 * @param hql comments
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByHibernateTemplate(String hql) {
		return (List<T>) hibernateTemplate.find(hql);
	}

	/**
	 * findByHql. 
	 * 使用hql进行查询
	 *
	 * @param hql
	 *            comments
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByHql(String hql) {
		Session session = getSessionFactory().openSession();
		List<T> result = (List<T>) session.createQuery(hql).list();
		return result;
	}

	/**
	 * findByCriteria. 
	 * 使用Criteria对象进行查询
	 * List cats =
	 * sess.createCriteria(Cat.class) .add( Restrictions.like("name", "F%")
	 * .addOrder( Order.asc("name") ) .addOrder( Order.desc("age") )
	 * .setMaxResults(50) .list();
	 *
	 * @param conditions
	 *            comments
	 * @return List
	 */
	public List<T> findByCriteria(List<Criterion> conditions) {
		return findByCriteria(conditions, null, null);
	}

	/**
	 * findByCriteria. 使用Criteria对象进行查询 
	 * List cats =
	 * sess.createCriteria(Cat.class) .add( Restrictions.like("name", "F%")
	 * .addOrder( Order.asc("name") ) .addOrder( Order.desc("age") )
	 * .setMaxResults(50) .list();
	 * 
	 * @param conditions
	 *            comments
	 * @param maxResult
	 *            comments
	 * @param order
	 *            comments
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(List<Criterion> conditions,
			Integer maxResult, Order order) {
		if (conditions == null) {
			return null;
		}
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(entityClass);
		for (Criterion criterion : conditions) {
			criteria.add(criterion);
		}
		if (maxResult != null) {
			criteria.setMaxResults(maxResult);
		}
		if (order != null) {
			criteria.addOrder(order);
		}
		return (List<T>) criteria.list();
	}

	/**
	 * findByNativeSql. 
	 * 使用原生Sql进行查询
	 *
	 * @param sql
	 *            comments
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List findByNativeSql(String sql) {
		Session session = getSessionFactory().openSession();
		List result = session.createSQLQuery(sql).list();
		return result;
	}
}
