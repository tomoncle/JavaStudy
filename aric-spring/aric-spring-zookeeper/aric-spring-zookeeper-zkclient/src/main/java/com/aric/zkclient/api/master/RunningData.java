package com.aric.zkclient.api.master;

import java.io.Serializable;

/**
 * 记录workServer的描述信息
 * Created by tom.lee on 2016/9/19.
 */
public class RunningData implements Serializable {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
