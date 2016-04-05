package com.mylab.cromero.service;

import com.mylab.cromero.repository.dto.MailRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestServiceConfigIT.class, ConfigurationService.class})
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private Wiser wiser;

    @Before
    public void setup() throws InterruptedException {

        boolean serverUpAndRunning = false;
        while (!serverUpAndRunning) {
            try {
                wiser.start();
                serverUpAndRunning = true;
            } catch (RuntimeException e) { // Fix for slow port-closing Jenkins
                if (e.getMessage().toLowerCase().contains("bindexception")) {
                    wiser.stop();
                    Thread.sleep(250L);
                }
            }
        }
    }

    @Transactional
    @Test
    public void testMailOk() throws MessagingException {
        String body = "This is my first mail.I am testing my mail implementation with wiser mock mail server";
        String subject = "testing wiser mock mail server";
        MailRequest mailrequest = new MailRequest();
        mailrequest.setBody(body);
        mailrequest.setSubject(subject);
        mailrequest.setTo("pepito@ole.com");
        mailService.sendMail(mailrequest);
        List<WiserMessage> messages = this.wiser.getMessages();
        Assert.assertEquals(messages.size(), 1);
        Assert.assertNotNull(messages.get(0).getMimeMessage());
        Assert.assertTrue(messages.get(0).getMimeMessage().getSubject().equalsIgnoreCase(subject));

    }

}
