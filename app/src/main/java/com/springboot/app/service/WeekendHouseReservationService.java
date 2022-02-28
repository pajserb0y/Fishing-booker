package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.BoatReservationDTO;
import com.springboot.app.model.dto.WeekendHouseReservationDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WeekendHouseReservationService {

    WeekendHouseReservation reserve(WeekendHouseReservation weekendHouseReservation);

    List<WeekendHouseReservation> getFutureForCustomerUsername(String username);

    List<WeekendHouseReservation> getPastForCustomerUsername(String username);

    void cancel(Integer id);

    List<WeekendHouseReservation> findAllReservationsForWeekendHouse(WeekendHouse weekendHouse);

    Optional<WeekendHouseReservation> findById(Integer weekendHouseReservationId);

    void sendFeedback(WeekendHouseFeedback weekendHouseFeedback);

    void sendComplaint(WeekendHouseComplaint weekendHouseComplaint);

    void sendReport(Report report);

    List<WeekendHouseReservation> getCurrentSpecialOffers();

    Set<WeekendHouseReservationDTO> getAllReservationsForWeekendHouseOwner(String username);
}
