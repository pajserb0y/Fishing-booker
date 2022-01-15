package com.springboot.app.service;

import com.springboot.app.model.Customer;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    @Async
    public void sendActivationMailAsync(Customer customer) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(customer.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Activation mail");
        mail.setText("Hi, " + customer.getFirstName() + ".\n\nWelcome to our site." +
                    "\nWe hope that you will be satisfied with our services." +
                    "\nIn order to activate your account click on this link: " +
                    "http://localhost:8080/api/customers/activate?hashCode=" + customer.getHashCode());

        javaMailSender.send(mail);
    }

    @Async
    public void sendNotificationForDeletingToAdmin(String note, Integer id) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("health.care.clinic.psw+admin@gmail.com");       //ovde treba da se iscupaju svi admini iz baze i da im se posalje
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Deleting request for " + id.toString());
        mail.setText("User with id: '" + id.toString() + "' has submited a request for deleting." +
                "\nHere is their note:" +
                "\n'" + note + "'");

        javaMailSender.send(mail);
    }

    @Async
    public void sendNotificationForWeekendHouseReservation(WeekendHouseReservation reservation) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("health.care.clinic.psw+admin@gmail.com");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successfully reservation");
        mail.setText("You have successfully reserved weekend house '" + reservation.getWeekendHouse().getName() +
                "' for period from " + reservation.getStartDateTime() + " to " + reservation.getEndDateTime());

        javaMailSender.send(mail);
    }

    @Override
    public void sendNotificationForFishingLessonReservation(FishingLessonReservation reservation) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(reservation.getCustomer().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successfully reservation");
        mail.setText("You have successfully reserved fishing lesson '" + reservation.getFishingLesson().getName() +
                "' for period from " + reservation.getStartDateTime() + " to " + reservation.getEndDateTime());

        javaMailSender.send(mail);
    }
}
