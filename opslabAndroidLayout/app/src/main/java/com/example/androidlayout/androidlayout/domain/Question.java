package com.example.androidlayout.androidlayout.domain;

/**
 * Created by wuyanbing on 2018/5/17 0017.
 */

public class Question {
    private Integer id;
    private String descript;
    private boolean result;

    public Question(Integer id, String descript, boolean result) {
        this.id = id;
        this.descript = descript;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
