package com.anhntk429.myapp429;

import java.util.HashMap;

public class Todo {
    private String id,title,content;

    public Todo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Todo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;}
 //hàm convert dữ liệu sang hashmap:để lưu vào firebase
 public HashMap<String, Object> convertToHashMap() {
     HashMap<String, Object> hashMap = new HashMap<>();
     hashMap.put("id", id);
     hashMap.put("title", title);
     hashMap.put("content", content);
     return hashMap;
    }
}
