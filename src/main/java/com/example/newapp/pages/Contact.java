package com.example.newapp.pages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.Date;

public class Contact {
    @Inject
    private Session session;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @Property
    private String fio;
    @Property
    private String email;
    @Property
    private String text;


    @CommitAfter
    @OnEvent(component = "addButton", value = "selected")
    void onSuccessForm()
    {
        message = "Сообщение успешно отправлено. Большое спасибо, что написали нам!";
    }

}
