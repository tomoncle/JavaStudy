package com.aric.xmls.ssh.baseframe.base;

import java.io.Serializable;

/**
 * Created by tom.lee on 2016/3/22.
 *
 * 父接口，封装了一些公共的方法
 *
 * */
public interface BaseDao<T> {

    /**
     * 添加对象
     * @param t
     * @return
     */
    Serializable addEntity(T t);


    /**
     * 删除对象
     * @param t
     */
    void deleteEntity(T t) throws Exception;


    /**
     *修改对象
     */
    void updateEntity(T t);


    /**
     * 添加或者修改
     */
    void saveOrUpdateEntity(T t);


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T  findById(Class<T> tClass,Serializable id);



    void deleteById(Serializable id,T t);


}
