package com.mylab.cromero.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mylab.cromero.controller.view.Message;
import com.mylab.cromero.controller.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller pizzas example.
 * <p>
 * Simple example  rest service with spring mvc.
 * <p>
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @JsonView(View.Summary.class)
    @GetMapping(value="/summary", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Message> listAllMessageSummary() {


        List<Message> listMessage = getMockMessages();
        return listMessage;

    }

    @JsonView(View.Internal.class)
    @GetMapping(value="/internal", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Message> listAllMessageInternal() {


        List<Message> listMessage = getMockMessages();
        return listMessage;

    }

    @GetMapping(value="/full", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Message> listAll() {


        List<Message> listMessage = getMockMessages();
        return listMessage;

    }

    //create mock messages
    private List<Message> getMockMessages() {
        List<Message> listMessage= new ArrayList<Message>();
        Message message = new Message();
        message.setBody("body1");
        message.setName("Samuel");
        message.setId(1L);
        message.setTitle("title1");
        listMessage.add(message);
        message = new Message();
        message.setBody("body2");
        message.setName("Peter");
        message.setId(2L);
        message.setTitle("title2");
        listMessage.add(message);
        return listMessage;
    }


}
