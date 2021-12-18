package com.springboot.app.service;

import com.springboot.app.model.Customer;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    public void sendActivationMailAsync(Customer customer) throws MailException, InterruptedException;
}
