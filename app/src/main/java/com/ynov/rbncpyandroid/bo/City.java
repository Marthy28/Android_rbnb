package com.ynov.rbncpyandroid.bo;

public class City {
    String id;

    String name;
    ImageBnb pic;

    public City(String id, String name, ImageBnb pic) {
        this.id = id;
        this.name = name;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageBnb getPic() {
        return pic;
    }

    public void setPic(ImageBnb pic) {
        this.pic = pic;
    }
}
