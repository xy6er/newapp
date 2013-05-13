package com.example.newapp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Aliyah
 * Date: 11.05.13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    public Long id;

    @Validate("required")
    public String title;

    @Validate("required")
    public String text;

    @Validate("required")
    public Date date;
}
