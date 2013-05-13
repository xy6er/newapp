package com.example.newapp.pages;

import com.example.newapp.entities.News;
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

    public List<News> getNewsList()
    {
        return session.createCriteria(News.class).list();
    }
}
