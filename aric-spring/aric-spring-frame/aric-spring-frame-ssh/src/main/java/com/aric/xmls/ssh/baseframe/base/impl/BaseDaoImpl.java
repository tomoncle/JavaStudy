package com.aric.xmls.ssh.baseframe.base.impl;

import com.aric.xmls.ssh.baseframe.base.BaseDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;

/**
 * Created by tom.lee on 2016/3/22.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    @Override
    public Serializable addEntity(T t){
        return this.getHibernateTemplate().save(t);
    }

    @Override
    public void deleteEntity(T t)throws Exception{
        this.getHibernateTemplate().delete(t);

    }

    @Override
    public void updateEntity(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public void saveOrUpdateEntity(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    @Override
    public T findById(Class<T> tClass,Serializable id) {
        return this.getHibernateTemplate().get(tClass, id);
    }

    @Override
    public void deleteById(Serializable id, T t) {
       T entity= findById((Class<T>)t.getClass(),id);
        if(entity==null){
            throw new NullPointerException("对象不存在，传入参数有误！");
        }
       this.getHibernateTemplate().delete(entity);
    }
}
