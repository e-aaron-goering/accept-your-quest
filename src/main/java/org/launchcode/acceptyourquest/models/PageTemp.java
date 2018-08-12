package org.launchcode.acceptyourquest.models;

import java.util.ArrayList;

public class PageTemp {

    private int id;
    private String pageTitle;
    private String pageText;
    private ArrayList<ChoiceTemp> choices = new ArrayList<>();

    public PageTemp() {
    }

    public PageTemp(int id, String pageTitle, String pageText) {
        this.id = id;
        this.pageTitle = pageTitle;
        this.pageText = pageText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageText() {
        return pageText;
    }

    public void setPageText(String pageText) {
        this.pageText = pageText;
    }

    public ArrayList<ChoiceTemp> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<ChoiceTemp> choices) {
        this.choices = choices;
    }
}
