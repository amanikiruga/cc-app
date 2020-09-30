package org.communitycookerfoundation.communitycookerfoundation.util;

import com.google.firebase.firestore.DocumentId;

abstract public class ReportPrompt {

    protected int prompt_id;

    protected String question;

    protected int question_id;

    protected String input_type;

    public abstract String getQuestion();

    public abstract String getInput_type();

    public abstract int getQuestion_id();
    //TODO: create spinner prompts
    //TODO: Sort out admin new users



}
