package com.example.ramona.planyourtrip.Util;

/**
 * Created by Ramona on 7/12/2018.
 */

public class LuggageList {
    private Integer id;
    private String luggageName;
    private Integer checked;

    public String getLuggageName() {
        return luggageName;
    }

    public void setLuggageName(String luggageName) {
        this.luggageName = luggageName;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
