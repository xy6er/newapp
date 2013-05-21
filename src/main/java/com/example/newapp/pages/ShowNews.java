package com.example.newapp.pages;

import com.example.newapp.entities.News;
import org.apache.commons.io.FileUtils;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.io.File;

/**
 * User: xy6er
 * Date: 14.05.13
 * Time: 4:25
 */

public class ShowNews {

    @Inject
    private Session session;

    @InjectPage
    private Index index;

    @Property
    @Persist
    private News news;

    @Property
    private String dateInFormatStr = "dd-MM-yyyy HH:mm:ss";

    void onActivate(News news) {
        this.news = news;
    }

    @CommitAfter
    @OnEvent(component = "deleteButton", value = "selected")
    Object deleteNews() throws Exception{
        File dir = new File("src/main/webapp/images/news" + news.id);
        FileUtils.deleteDirectory(dir);
        session.delete(news);
        return index;
    }
}
