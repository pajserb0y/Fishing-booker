package com.springboot.app.service;

import com.springboot.app.model.WeekendHouse;
import com.springboot.app.model.WeekendHouseComplaint;
import com.springboot.app.model.WeekendHouseFeedback;
import com.springboot.app.model.WeekendHouseReservation;

import java.util.List;
import java.util.Optional;

public interface WeekendHouseReservationService {

    WeekendHouseReservation reserve(WeekendHouseReservation weekendHouseReservation);

    List<WeekendHouseReservation> getFutureForCustomerUsername(String username);

    List<WeekendHouseReservation> getPastForCustomerUsername(String username);

    void cancel(Integer id);

    void sendFeedback(WeekendHouseFeedback weekendHouseFeedback);
    List<WeekendHouseReservation> findAllReservationsForWeekendHouse(WeekendHouse weekendHouse);

    Optional<WeekendHouseReservation> findById(Integer weekendHouseReservationId);

    void sendComplaint(WeekendHouseComplaint weekendHouseComplaint);
}
