package com.aric.common.utils;

/**
 * Created by aric on 2016/5/5.
 */
public class BaseEnum {

    public enum  DataLogicStatus{
        UN_DELETED(0),
        IS_DELETED(1),
        ENABLED(0),
        DISABLED(1);
        private Integer code;
        DataLogicStatus(Integer code){
            this.code=code;
        }
        public Integer getCode() {
            return code;
        }
    }


}
