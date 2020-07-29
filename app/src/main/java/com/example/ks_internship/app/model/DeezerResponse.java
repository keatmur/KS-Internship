package com.example.ks_internship.app.model;

import java.util.List;

public class DeezerResponse {
    private List<DeezerTrack> data;
    private long total;
    private String next;

    public List<DeezerTrack> getData() {
        return data;
    }

    public void setData(List<DeezerTrack> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
