package com.ynov.rbncpyandroid.bo;

import java.io.Serializable;

public class ImageBnb implements Serializable {
    String url;

    public ImageBnb(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
