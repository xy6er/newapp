package com.example.newapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import java.util.Date;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    public int id;

    @Validate("required")
    public String title;

    @Validate("required")
    public String text;

    @Validate("required")
    public Date date;

    @Validate("required")
    public String imageUrl;

    @Validate("required")
    public String scaledImageUrl;

    public News() {
    }

    public News(String title, String text, Date date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }
}
