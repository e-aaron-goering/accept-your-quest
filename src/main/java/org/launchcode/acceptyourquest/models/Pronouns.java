package org.launchcode.acceptyourquest.models;

public enum Pronouns {
    HE ("he", "him", "his", "his", "himself"),
    SHE ("she", "her", "her", "hers", "herself"),
    AE ("ae", "aer", "aer", "aers", "aerself"),
    FAE ("fae", "faer", "faer", "faers", "faerself"),
    EY ("ey", "em", "eir", "eirs", "eirself"),
    PER ("per", "per", "pers", "pers", "perself"),
    THEY ("they", "them", "their", "theirs", "themself"),
    VE ("ve", "ver", "vis", "vis", "verself"),
    XE ("xe", "xem", "xir", "xyrs", "xemself"),
    ZIE ("zie", "hir", "hir", "hirs", "hirself");

    private final String subject;
    private final String object;
    private final String possessive;
    private final String possessivePronoun;
    private final String reflexive;

    Pronouns (String subject, String object, String possessive, String possessivePronoun, String reflexive){
        this.object = object;
        this.subject = subject;
        this.possessive = possessive;
        this.possessivePronoun = possessivePronoun;
        this.reflexive = reflexive;
    }
}
