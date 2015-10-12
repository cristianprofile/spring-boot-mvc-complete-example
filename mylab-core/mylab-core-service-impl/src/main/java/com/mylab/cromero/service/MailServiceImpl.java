package com.mylab.cromero.service;

import com.mylab.cromero.repository.dto.MailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


/**
* <h1>Mail Service Implement!</h1>
* Bussiness Service example using Spring Mail 
* <p>
* <b>Spring Mail</b> Class send mail from custon mailRequest input parameter
*
* @author  Cristian Romero Matesanz
*
* 
*/
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendMail(final MailRequest mailrequest) {
        this.logger.debug("Begin operation: sending mail, request:{} ", mailrequest);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailrequest.getTo());
        message.setSubject(mailrequest.getSubject());
        message.setText(mailrequest.getBody());
        mailSender.send(message);
        this.logger.debug("End operation: sending mail, request:{} ", mailrequest);
    }
}
