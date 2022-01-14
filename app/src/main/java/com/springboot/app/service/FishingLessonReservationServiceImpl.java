package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.repository.CustomerRepository;
import com.springboot.app.repository.FishingLessonRepository;
import com.springboot.app.repository.FishingLessonReservationRepository;
import com.springboot.app.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FishingLessonReservationServiceImpl implements FishingLessonReservationService {

    private final FishingLessonReservationRepository fishingLessonReservationRepository;
    private final FishingLessonRepository fishingLessonRepository;
    private final CustomerRepository customerRepository;
    private final InstructorRepository instructorRepository;

    public FishingLessonReservationServiceImpl(FishingLessonReservationRepository fishingLessonReservationRepository, FishingLessonRepository fishingLessonRepository, CustomerRepository customerRepository, InstructorRepository instructorRepository) {
        this.fishingLessonReservationRepository = fishingLessonReservationRepository;
        this.fishingLessonRepository = fishingLessonRepository;
        this.customerRepository = customerRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public FishingLessonReservation reserve(FishingLessonReservation fishingLessonReservation) {
        Optional<FishingLesson> fishingLesson = fishingLessonRepository.findById(fishingLessonReservation.getFishingLesson().getId());
        Optional<Customer> customer = customerRepository.findById(fishingLessonReservation.getCustomer().getId());

        if (fishingLesson.isPresent() && customer.isPresent()) {
            fishingLessonReservation.setFishingLesson(fishingLesson.get());
            fishingLessonReservation.setCustomer(customer.get());
            fishingLessonReservationRepository.save(fishingLessonReservation);
        }

        return fishingLessonReservation;
    }

    @Override
    public List<FishingLessonReservation> getFutureResForCustomer(String customerUsername) {
        return fishingLessonReservationRepository.findAllByCustomerUsername(customerUsername);
    }

    @Override
    public List<FishingLessonReservation> getFutureResForInstructor(String instructorUsername) {
        return fishingLessonReservationRepository.findAllByInstructorUsername(instructorUsername);
    }
}
