package org.harryng.demo.model;

public class OutputChatMessage extends ChatMessage{
    private String time = "";

    public OutputChatMessage(String from, String to, String content, String time) {
        this.setFrom(from);
        this.setTo(to);
        this.setContent(content);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
