package org.launchcode.acceptyourquest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CustomOption {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=100)
    private String storyOption;

    @NotNull
    @Size(max=10)
    private String customTag;

    @ManyToOne
    private Novel novel;

    public CustomOption() {
    }

    public CustomOption(@NotNull @Size(min = 3, max = 40) String storyOption, @NotNull @Size(max = 10) String customTag) {
        this.storyOption = storyOption;
        this.customTag = customTag;
    }

    public int getId() {
        return id;
    }

    public String getStoryOption() {
        return storyOption;
    }

    public void setStoryOption(String storyOption) {
        this.storyOption = storyOption;
    }

    public String getCustomTag() {
        return customTag;
    }

    public void setCustomTag(String customTag) {
        this.customTag = customTag;
    }

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}
