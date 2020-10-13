package com.twitter.handle.twitterhandle.request;

import java.util.List;

public class AddRulesRequest {

    private List<TwitterRule> add;

    public AddRulesRequest(List<TwitterRule> add) {
        this.add = add;
    }

    public List<TwitterRule> getAdd() {
        return add;
    }

    public void setAdd(List<TwitterRule> add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "AddRulesRequest{" +
                "add=" + add +
                '}';
    }
}
