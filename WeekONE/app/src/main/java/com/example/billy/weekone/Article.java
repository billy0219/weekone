package com.example.billy.weekone;

import io.realm.RealmObject;

/**
 * Created by billy on 2017-07-19.
 */

public class Article extends RealmObject {
    private String title;
    private String content;

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
        this.content = content;
    }


}
