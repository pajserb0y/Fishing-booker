package com.springboot.app.service;

import com.springboot.app.model.WeekendHouseReservation;
import com.springboot.app.model.dto.WeekendHouseReservationDTO;

import java.util.List;
import java.util.Set;

public interface WeekendHouseReservationService {

    WeekendHouseReservation reserve(WeekendHouseReservation weekendHouseReservation);

    List<WeekendHouseReservation> getFutureForCustomerUsername(String username);
}
