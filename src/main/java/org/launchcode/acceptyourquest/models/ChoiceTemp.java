package org.launchcode.acceptyourquest.models;

public class ChoiceTemp {

    private int pageId;
    private String choiceDescription;

    public ChoiceTemp() {
    }

    public ChoiceTemp(String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getChoiceDescription() {
        return choiceDescription;
    }

    public void setChoiceDescription(String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }
}
