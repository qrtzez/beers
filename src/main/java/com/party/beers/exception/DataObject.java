package com.party.beers.exception;

public class DataObject {

    private Object data;

    public DataObject() {
    }

    public DataObject(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
