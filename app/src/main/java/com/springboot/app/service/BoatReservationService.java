package com.springboot.app.service;

import com.springboot.app.model.Boat;
import com.springboot.app.model.BoatComplaint;
import com.springboot.app.model.BoatFeedback;
import com.springboot.app.model.BoatReservation;

import java.util.List;
import java.util.Optional;

public interface BoatReservationService {
    BoatReservation reserve(BoatReservation boatReservation);

    List<BoatReservation> getFutureForCustomerUsername(String username);

    List<BoatReservation> getPastForCustomerUsername(String username);

    void cancel(Integer id);

    Optional<BoatReservation> findById(Integer boatReservationId);

    void sendFeedback(BoatFeedback boatFeedback);

    void sendComplaint(BoatComplaint boatComplaint);

    List<BoatReservation> getCurrentSpecialOffers();

    List<BoatReservation> findAllReservationsForBoat(Boat boat);
}
