package org.harryng.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OutputChatMessage extends ChatMessage{
    private String time = "";

    public OutputChatMessage(String from, String to, String content, String time) {
        this.setFrom(from);
        this.setTo(to);
        this.setContent(content);
        this.setTime(time);
    }

//    public void setTime(String time) {
//        this.time = time;
//    }
}
