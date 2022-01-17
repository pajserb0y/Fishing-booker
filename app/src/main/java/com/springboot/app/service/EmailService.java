package com.springboot.app.service;

import com.springboot.app.model.BoatReservation;
import com.springboot.app.model.Customer;
import com.springboot.app.model.FishingLessonReservation;
import com.springboot.app.model.WeekendHouseReservation;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    void sendActivationMailAsync(Customer customer) throws MailException, InterruptedException;
    @Async
    void sendNotificationForDeletingToAdmin(String note, Integer id) throws MailException;
    @Async
    void sendNotificationForWeekendHouseReservation(WeekendHouseReservation reservation);
    @Async
    void sendNotificationForBoatReservation(BoatReservation reservation);
    @Async
    void sendNotificationForFishingLessonReservation(FishingLessonReservation reservation);

    void sendNotificationForSpecialOfferWeekendHouse(Customer customer, WeekendHouseReservation reservation);

    void sendNotificationForSpecialOfferBoat(Customer customer, BoatReservation reservation);

    void sendNotificationForSpecialOfferFishingLesson(Customer customer, FishingLessonReservation reservation);
}
