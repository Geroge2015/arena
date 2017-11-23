package com.example.cm.testrv.requesthttp.insertdialog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cm on 2017/11/22.
 */

public class ReqDataBean implements Parcelable {
    int type;
    String coverUrl;
    String thumbUrl;
    int startTime;
    int endTime;

    public ReqDataBean(Parcel in) {
        this.type = in.readInt();
        this.coverUrl = in.readString();
        this.thumbUrl = in.readString();
        this.startTime = in.readInt();
        this.endTime = in.readInt();
    }

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

    public static final Creator<ReqDataBean> CREATOR = new Creator<ReqDataBean>() {
        @Override
        public ReqDataBean createFromParcel(Parcel in) {
            return new ReqDataBean(in);
        }

        @Override
        public ReqDataBean[] newArray(int size) {
            return new ReqDataBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(coverUrl);
        dest.writeString(thumbUrl);
        dest.writeInt(startTime);
        dest.writeInt(endTime);
    }
}
