package com.example.comic.Model;

public class Comment {
    public String content;
    public String create_at;

    public Comment() {
    }

    public Comment(String content, String create_at) {
        this.content = content;
        this.create_at = create_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}
