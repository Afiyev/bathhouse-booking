package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Reservation;

import java.security.Principal;

public interface BookingControllerService {
    Reservation createReservation(Principal principal, int cabinId, int time);
    void saveReservation(Reservation reservation);
}
