package org.launchcode.acceptyourquest.models;

import java.util.ArrayList;

public class NovelTemp {
    public String novelTitle = "";
    public String subTitle = "";
    public ArrayList<String> storyFields = new ArrayList<>();
    public ArrayList<String> customTags = new ArrayList<>();
    public ArrayList<PageTemp> pages = new ArrayList<>();

    public NovelTemp() {
    }

    public NovelTemp(String novelTitle, String subTitle) {
        this.novelTitle = novelTitle;
        this.subTitle = subTitle;
    }

    public String getNovelTitle() {
        return novelTitle;
    }

    public void setNovelTitle(String novelTitle) {
        this.novelTitle = novelTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public ArrayList<String> getStoryFields() {
        return storyFields;
    }

    public void setStoryFields(ArrayList<String> storyFields) {
        this.storyFields = storyFields;
    }

    public ArrayList<String> getCustomTags() {
        return customTags;
    }

    public void setCustomTags(ArrayList<String> customTags) {
        this.customTags = customTags;
    }

    public ArrayList<PageTemp> getPages() {
        return pages;
    }

    public void setPages(ArrayList<PageTemp> pages) {
        this.pages = pages;
    }
}
