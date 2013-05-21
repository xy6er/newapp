package com.example.newapp.pages;

import com.example.newapp.entities.News;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Start page of application newapp.
 */
public class Search
{
    @Inject
    private Session session;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @Property
    private News news;

    @Property
    private String dateInFormatStr = "dd-MM-yyyy HH:mm:ss";

    private static String searchText = "";

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<News> getNewsList()
    {
        return session.createCriteria(News.class)
                .add(Restrictions.or(
                        Restrictions.like("title", searchText, MatchMode.ANYWHERE),
                        Restrictions.like("text", searchText, MatchMode.ANYWHERE)
                ))
                .addOrder(Order.desc("date"))
                .list();
    }

}
