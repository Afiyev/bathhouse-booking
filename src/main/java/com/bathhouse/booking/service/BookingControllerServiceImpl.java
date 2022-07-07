package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Cabin;
import com.bathhouse.booking.model.Reservation;
import com.bathhouse.booking.model.User;
import com.bathhouse.booking.repository.CabinRepository;
import com.bathhouse.booking.repository.ReservationRepository;
import com.bathhouse.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingControllerServiceImpl implements BookingControllerService{

    private final CabinRepository cabinRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public BookingControllerServiceImpl(CabinRepository cabinRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.cabinRepository = cabinRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public Reservation createReservation(Principal principal, int cabinId, int time) {

        Reservation reservation = new Reservation();

        Cabin cabin = new Cabin();
        Optional<Cabin> optionalCabin = cabinRepository.findById(cabinId);
        if(optionalCabin.isPresent()) {
            cabin = optionalCabin.get();
        }

        User currentUser = userRepository.findUserByUsername(principal.getName());

        LocalTime bookedTime = LocalTime.of(time + 12, 0);

        reservation.setUser(currentUser);
        reservation.setTime(bookedTime);
        reservation.setCabin(cabin);
        reservation.setBathhouse(cabin.getBathhouse());
        reservation.setPrice(cabin.getPrice());

        return reservation;
    }

    @Override
    @Transactional
    public void saveReservation(Reservation reservation) {

        int timeIndex = reservation.getTime().getHour() - 12;
        reservation.getCabin().getSchedule().getIsBooked().set(timeIndex, true);

        reservationRepository.save(reservation);
    }
}
