package io.github.homework.entity;

import android.os.Parcelable;

import java.io.Serializable;

public class Music implements Serializable {
    private int id;
    private String name;
    private String author;
    private int cover;
    private int resource;//音频资源
    private int type;

    public Music(String name, String author, int cover, int resource, int type) {
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.resource = resource;
        this.type = type;
    }

    public Music(int id, String name, String author, int cover, int resource, int type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.resource = resource;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", cover=" + cover +
                ", resource=" + resource +
                ", type=" + type +
                '}';
    }
}
