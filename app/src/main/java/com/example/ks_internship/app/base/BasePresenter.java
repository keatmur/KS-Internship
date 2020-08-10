package com.example.ks_internship.app.base;

public interface BasePresenter <T> {
    void takeView(T view);

    void dropView();
}
