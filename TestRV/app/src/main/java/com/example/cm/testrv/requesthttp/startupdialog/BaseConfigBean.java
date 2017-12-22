package com.example.cm.testrv.requesthttp.startupdialog;

import java.io.Serializable;

/**
 * @author George
 */

public class BaseConfigBean implements Serializable {

    public int id;
    public int classId;
    public int subClassId;
    public int startTime;
    public int endTime;
    public long beginDate;
    public long expireDate;

    public BaseConfigBean() {
    }

    @Override
    public String toString() {
        return "BaseConfigBean : id = " + id + " classId = " + classId + " subClassId = " + subClassId
                + " startTime = " + startTime + " endTime = " + endTime + "beginDate = " + beginDate
                + " expireDate = " + expireDate;
    }

    public static class Builder {
        BaseConfigBean configBean;

        public Builder() {
            this.configBean = new BaseConfigBean();

        }

        public Builder setId(int id) {
            configBean.id = id;
            return this;
        }

        public Builder setClassId(int classId) {
            configBean.classId = classId;
            return this;
        }

        public Builder setSubClassId(int subClassId) {
            configBean.subClassId = subClassId;
            return this;
        }

        public Builder setStartTime(int startTime) {
            configBean.startTime = startTime;
            return this;
        }

        public Builder setEndTime(int endTime) {
            configBean.endTime = endTime;
            return this;
        }

        public Builder setBeginDate(long beginDate) {
            configBean.beginDate = beginDate;
            return this;
        }

        public Builder setExpireDate(long expireDate) {
            configBean.expireDate = expireDate;
            return this;
        }

        public BaseConfigBean build() {
            return configBean;
        }

    }



}
