package org.evilcamp.v.business.security.permission.dao.hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="permission")
public class PermissionHibernateEntity {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private long id;


	@Column(name = "name")
	private String name;

	@Column(name = "path")
	private String path;

	@Column(name = "remark")
	private String remark;

	@Column(name = "create_time")
	private Date createTime;


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PermissionHibernateEntity{" +
				"createTime=" + createTime +
				", id=" + id +
				", name='" + name + '\'' +
				", path='" + path + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
