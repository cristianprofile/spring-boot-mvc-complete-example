package com.mylab.cromero.repository.dto;

/**
* <h1>MailRequest</h1>
* BaseRequest dto request 
* <p>
* <b>MailRequest</b> MailRequest data transfer object 
* for sevice layer
*
* @author  Cristian Romero Matesanz
*
* 
*/
public class MailRequest {

    private String to;
    private String subject;
    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MailRequest [to=");
        builder.append(to);
        builder.append(", subject=");
        builder.append(subject);
        builder.append(", body=");
        builder.append(body);
        builder.append("]");
        return builder.toString();
    }

}
