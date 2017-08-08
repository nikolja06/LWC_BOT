package com.botcompany.entties;


import java.util.List;

public class Step {

    private String messageText;
    private List<Button> buttons;
    private boolean isEdited;

    public Step() {
    }

//    public Step(String messageText, List<Button> buttons, boolean isEdited) {
//        this.messageText = messageText;
//        this.buttons = buttons;
//        this.isEdited = isEdited;
//    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (isEdited != step.isEdited) return false;
        if (messageText != null ? !messageText.equals(step.messageText) : step.messageText != null) return false;
        return buttons != null ? buttons.equals(step.buttons) : step.buttons == null;
    }

    @Override
    public int hashCode() {
        int result = messageText != null ? messageText.hashCode() : 0;
        result = 31 * result + (buttons != null ? buttons.hashCode() : 0);
        result = 31 * result + (isEdited ? 1 : 0);
        return result;
    }
}
