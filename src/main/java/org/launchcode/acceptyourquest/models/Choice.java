package org.launchcode.acceptyourquest.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
public class Choice {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=80)
    private String choiceDescription;

    @ManyToOne
    private Page currentPage;

    private int pagePointer;

    public Choice() {
    }

    public Choice(@NotNull @Size(min = 3, max = 80) String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }

    public Choice(@NotNull @Size(min = 3, max = 80) String choiceDescription, Page page) {
        this.choiceDescription = choiceDescription;
        this.currentPage = page;
    }

    public String getChoiceDescription() {
        return choiceDescription;
    }

    public void setChoiceDescription(String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }

    public void setCurrentPage(Page page) {
        this.currentPage = page;
    }

    public Page getCurrentPage() {
        return this.currentPage;
    }

    public int getPagePointer() {
        return this.pagePointer;
    }

    public void setPagePointer(int pagePointer) {
        this.pagePointer = pagePointer;
    }
}
