package com.example.ks_internship.app.model;

import java.util.Objects;

public class DeezerRepoErrorItem {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeezerRepoErrorItem)) return false;
        DeezerRepoErrorItem that = (DeezerRepoErrorItem) o;
        return Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getCode());
    }
}
