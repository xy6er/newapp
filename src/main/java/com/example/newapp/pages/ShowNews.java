package com.example.newapp.pages;

import com.example.newapp.entities.News;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 * User: xy6er
 * Date: 14.05.13
 * Time: 4:25
 */

public class ShowNews {

    @Property
    @Persist
    private News news;

    @Property
    @Persist
    private String text;

    public void setup(News news) {
        this.news = news;
        text = news.text;
    }
}
