package com.botcompany.entties;


public class Button {

    private String text;
    private String callBackData;

    public Button() {
    }

    public Button(String text, String callBackData) {
        this.text = text;
        this.callBackData = callBackData;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallBackData() {
        return callBackData;
    }

    public void setCallBackData(String callBackData) {
        this.callBackData = callBackData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Button button = (Button) o;

        if (text != null ? !text.equals(button.text) : button.text != null) return false;
        return callBackData != null ? callBackData.equals(button.callBackData) : button.callBackData == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (callBackData != null ? callBackData.hashCode() : 0);
        return result;
    }
}
