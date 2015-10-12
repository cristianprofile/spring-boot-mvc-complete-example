package com.mylab.cromero.service;

import com.mylab.cromero.repository.dto.MailRequest;

/**
* <h1>MailService</h1>
* MailService interface definition 
* <p>
* <b>MailService</b> definition of methods of interface
* for sevice layer
*
* @author  Cristian Romero Matesanz
*
* 
*/
public interface MailService {

    /**
     * Method to send an email whith information request
     * @param mailrequest mail request to send by email protocol
     */
     void sendMail(final MailRequest mailrequest);

}
