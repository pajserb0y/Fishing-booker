package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.FishingLessonReservationDTO;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FishingLessonReservationServiceImpl implements FishingLessonReservationService {

    private final FishingLessonReservationRepository fishingLessonReservationRepository;
    private final FishingLessonRepository fishingLessonRepository;
    private final CustomerRepository customerRepository;
    private final InstructorRepository instructorRepository;
    private final FishingLessonFeedbackRepository fishingLessonFeedbackRepository;
    private final FishingLessonComplaintRepository fishingLessonComplaintRepository;

    public FishingLessonReservationServiceImpl(FishingLessonReservationRepository fishingLessonReservationRepository, FishingLessonRepository fishingLessonRepository, FishingLessonComplaintRepository fishingLessonComplaintRepository,
                                               CustomerRepository customerRepository, InstructorRepository instructorRepository, FishingLessonFeedbackRepository fishingLessonFeedbackRepository) {
        this.fishingLessonReservationRepository = fishingLessonReservationRepository;
        this.fishingLessonRepository = fishingLessonRepository;
        this.customerRepository = customerRepository;
        this.instructorRepository = instructorRepository;
        this.fishingLessonFeedbackRepository = fishingLessonFeedbackRepository;
        this.fishingLessonComplaintRepository = fishingLessonComplaintRepository;
    }

    @Override
    public FishingLessonReservation reserve(FishingLessonReservation fishingLessonReservation) {
        Optional<FishingLessonReservation> resInDatabase = fishingLessonReservationRepository.findById(fishingLessonReservation.getId());
        if (resInDatabase.isPresent()) {
            resInDatabase.get().setCustomer(customerRepository.findById(fishingLessonReservation.getCustomer().getId()).get());
            fishingLessonReservationRepository.save(resInDatabase.get());
            return resInDatabase.get();
        }
        else {
            Optional<FishingLesson> fishingLesson = fishingLessonRepository.findById(fishingLessonReservation.getFishingLesson().getId());
            Optional<Customer> customer = customerRepository.findById(fishingLessonReservation.getCustomer().getId());

            if (fishingLesson.isPresent() && customer.isPresent()) {
                fishingLessonReservation.setFishingLesson(fishingLesson.get());
                fishingLessonReservation.setCustomer(customer.get());
                fishingLessonReservationRepository.save(fishingLessonReservation);
            }
            return fishingLessonReservation;
        }
    }


    @Override
    public List<FishingLessonReservation> getFutureResForInstructor(Instructor instructorUsername) {
        return fishingLessonReservationRepository.findAllByFishingLessonInstructor(instructorUsername);
    }

    @Override
    public List<FishingLessonReservation> getFutureForCustomerUsername(String username) {
        return fishingLessonReservationRepository.findAllFutureReservationsByCustomerUsername(username);
    }

    @Override
    public List<FishingLessonReservation> getPastForCustomerUsername(String username) {
        return fishingLessonReservationRepository.findAllPastReservationsByCustomerUsername(username);
    }

    @Override
    public void cancel(Integer id) {
        Optional<FishingLessonReservation> res = fishingLessonReservationRepository.findById(id);
        if (res.isPresent()) {
            res.get().setCancelled(true);
            fishingLessonReservationRepository.save(res.get());
        }
    }

    @Override
    public Optional<FishingLessonReservation> findById(Integer fishingLessonReservationId) {
        return fishingLessonReservationRepository.findById(fishingLessonReservationId);
    }

    @Override
    public void sendFeedback(FishingLessonFeedback fishingLessonFeedback) {
        fishingLessonFeedbackRepository.save(fishingLessonFeedback);
    }

    @Override
    public void sendComplaint(FishingLessonComplaint fishingLessonComplaint) {
        fishingLessonComplaintRepository.save(fishingLessonComplaint);
    }

    @Override
    public List<FishingLessonReservation> getCurrentSpecialOffers() {
        return fishingLessonReservationRepository.getCurrentSpecialOffers();
    }

    @Override
    public Set<FishingLessonReservationDTO> getAllReservationsForInstructor(String username) {
        Integer id = instructorRepository.findByUsername(username).getId();
        List<FishingLessonReservation> fishingLessonReservations = fishingLessonReservationRepository.findAllReservationsForInstructor(id);
        Set<FishingLessonReservationDTO> fishingLessonReservationsDTOs = new HashSet<>();
        for (FishingLessonReservation fl : fishingLessonReservations) {
            FishingLessonReservationDTO dto = new FishingLessonReservationDTO(fl);
            fishingLessonReservationsDTOs.add(dto);
        }

        return fishingLessonReservationsDTOs;
    }
}
