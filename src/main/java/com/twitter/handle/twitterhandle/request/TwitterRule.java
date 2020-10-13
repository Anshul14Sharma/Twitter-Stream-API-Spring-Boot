package com.twitter.handle.twitterhandle.request;

public class TwitterRule {
    private String value;
    private String tag;

    public TwitterRule(String value, String tag) {
        this.value = value;
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TwitterRule{" +
                "value='" + value + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
