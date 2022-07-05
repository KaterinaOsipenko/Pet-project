package com.work.authorization.model;

import com.work.authorization.model.User;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.Locale;
import java.util.PrimitiveIterator;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private User user;
    private Locale locale;

    public OnRegistrationCompleteEvent(String appUrl, User user, Locale locale) {
        super(user);
        this.appUrl = appUrl;
        this.user = user;
        this.locale = locale;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
