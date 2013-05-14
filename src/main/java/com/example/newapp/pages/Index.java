package com.example.newapp.pages;

import com.example.newapp.entities.News;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.List;

/**
 * Start page of application newapp.
 */
public class Index
{
    @Inject
    private Session session;

    @InjectPage
    ShowNews showNews;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @Property
    private int newsId;

    public List<News> getNewsList()
    {
        return session.createCriteria(News.class).list();
    }

    @OnEvent(component = "submitId", value = "selected")
    Object selectButton() {
        News news = (News)session.get(News.class, newsId);
        showNews.setup(news);
        return showNews;
    }
}
