package com.example.newapp.components;

import com.example.newapp.pages.Search;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Layout component for pages of application newapp.
 */
@Import(stylesheet = "context:layout/layout.css")
public class Layout
{
    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String searchText;

    @InjectPage
    private Search search;

    @CommitAfter
    @OnEvent(component = "searchButton", value = "selected")
    Object onSuccessForm() {
        search.setSearchText(searchText);
        return search;
    }

}
