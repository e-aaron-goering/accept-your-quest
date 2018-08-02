package org.launchcode.acceptyourquest.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Choice {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=80)
    private String choiceDescription;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public String getChoiceDescription() {
        return choiceDescription;
    }

    public void setChoiceDescription(String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }

    public Page getPage() {
        return page;
    }
}
