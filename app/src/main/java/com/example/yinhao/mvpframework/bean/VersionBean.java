package com.example.yinhao.mvpframework.bean;

import com.google.gson.Gson;

public class VersionBean {

    /**
     * force : 1
     * type : 0
     * url : http://10.0.8.89:8080/static/app/weichai.apk
     * version : 1.0.3
     */

    private String force;
    private String type;
    private String url;
    private String version;

    public static VersionBean objectFromData(String str) {

        return new Gson().fromJson(str, VersionBean.class);
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
