package com.example.cm.testrv.requesthttp.startupdialog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cm on 2017/11/30.
 */

public class BaseDataBean implements Parcelable {
    int type;
    String coverUrl;
    String thumbUrl;
    int startTime;
    int endTime;

    public BaseDataBean(Parcel bean) {
        this.type = bean.readInt();
        this.coverUrl = bean.readString();
        this.thumbUrl = bean.readString();
        this.startTime = bean.readInt();
        this.endTime = bean.readInt();
    }

    // 在实现上面的接口方法后，接下来还需要执行反序列化，定义一个变量，并重新定义其中的部分方法
    public static final Creator<BaseDataBean> CREATOR = new Creator<BaseDataBean>() {
        //在这个方法中反序列化上面的序列化内容，最后根据反序列化得到的各个属性，得到之前试图传递的对象
        //反序列化的属性的顺序必须和之前写入的顺序一致
        @Override
        public BaseDataBean createFromParcel(Parcel source) {
            return new BaseDataBean(source);
        }

        @Override
        public BaseDataBean[] newArray(int size) {
            return new BaseDataBean[size];   //一般返回一个数量为size的传递的类的数组就可以了
        }
    };

    // 下面是实现Parcelable接口的内容
    @Override
    public int describeContents() {
        return 0;
    }

    //在这个方法中写入这个类的变量
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);   // int类型 type
        dest.writeString(coverUrl);
        dest.writeString(thumbUrl);
        dest.writeInt(startTime);
        dest.writeInt(endTime);
    }

    //设置setter() 和 getter()方法  short cut:   alt + insert
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
