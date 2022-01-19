package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.DateTimeRangeDTO;
import com.springboot.app.model.dto.FishingLessonDTO;
import com.springboot.app.model.dto.InstructorDTO;
import com.springboot.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final FishingLessonReservationRepository fishingLessonReservationRepository;
    private final FishingLessonRepository fishingLessonRepository;
    private final RoleService roleService;
    private final FishingLessonFeedbackRepository fishingLessonFeedbackRepository;
    private final TermFishingLessonRepository termFishingLessonRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository, RoleService roleService, FishingLessonReservationRepository fishingLessonReservationRepository,
                                 FishingLessonRepository fishingLessonRepository, FishingLessonFeedbackRepository fishingLessonFeedbackRepository, TermFishingLessonRepository termFishingLessonRepository) {
        this.instructorRepository = instructorRepository;
        this.roleService = roleService;
        this.fishingLessonReservationRepository = fishingLessonReservationRepository;
        this.fishingLessonRepository = fishingLessonRepository;
        this.fishingLessonFeedbackRepository = fishingLessonFeedbackRepository;
        this.termFishingLessonRepository = termFishingLessonRepository;
    }

    @Override
    public Instructor saveInstructor(Instructor instructor) {
        List<Role> roles = roleService.findByName("ROLE_INSTRUCTOR");
        instructor.setRole(roles.get(0));
        instructor.setActivated(true);       //admin treba da odobri aktivaciju naloga koji nisu customer
        instructorRepository.save(instructor);
        return null;
    }

    @Override
    public Instructor findByUsername(String username) {
        return instructorRepository.findByUsername(username);
    }

    @Override
    public Instructor changeInstructor(InstructorDTO instructorDTO) {
        Optional<Instructor> instructor = instructorRepository.findById(instructorDTO.getId());

        if (instructor.isPresent()) {
            instructor.get().edit(instructorDTO);
            instructorRepository.save(instructor.get());
            return instructor.get();
        }

        return null;
    }

    @Override
    public void addFishingLessonForInstructor(FishingLessonDTO fishingLessonDTO, Integer id) {
        FishingLesson fishingLesson = new FishingLesson(fishingLessonDTO);
        Optional<Instructor> instructor = instructorRepository.findById(id);

        if (instructor.isPresent()) {
            fishingLesson.setInstructor(instructor.get());
            fishingLessonRepository.save(fishingLesson);
        }
    }

    @Override
    public FishingLesson changeFishingLesson(FishingLessonDTO fishingLessonDTO) {
        Optional<FishingLesson> fishingLesson = fishingLessonRepository.findById(fishingLessonDTO.getId());

        if (fishingLesson.isPresent()) {
            fishingLesson.get().edit(fishingLessonDTO);
            fishingLessonRepository.save(fishingLesson.get());
        }

        return fishingLesson.get();
    }

    @Override
    public boolean removeFishingLesson(Integer fishingLessonId) {
        Optional<FishingLesson> fishingLesson = fishingLessonRepository.findById(fishingLessonId);

        if (!fishingLesson.isPresent())
            return false;

        if (!fishingLessonReservationRepository.existsFutureReservation(fishingLesson.get().getId()).isEmpty())
            return false;

        fishingLessonRepository.delete(fishingLesson.get());
        return true;
    }

    @Override
    public List<FishingLesson> findAllFishingLessons() {
        return fishingLessonRepository.findAll();
    }

    @Override
    public List<FishingLesson> findAllAvailableFishingLessons(DateTimeRangeDTO dateRange) {
        List<Integer> fishingLessonIds = fishingLessonReservationRepository.findAllForDateRange(dateRange.getStart(), dateRange.getEnd());
        fishingLessonIds.addAll(termFishingLessonRepository.findAllFishingLessonIdsThatWorkForPeriod(dateRange.getStart(), dateRange.getEnd()));
        if (fishingLessonIds.isEmpty())
            return findAllFishingLessons();
        else
            return fishingLessonRepository.findAllByIdNotIn(fishingLessonIds);
    }

    @Override
    public List<FishingLesson> findAllFishingLessonsForInstructor(Integer instructorId) {
        return fishingLessonRepository.findAllByInstructorId(instructorId);
    }

    @Override
    public List<FishingLessonReservation> findAllReservationsForInstructor(Integer instructorId, boolean futureOnly) {
        if (futureOnly)
            return fishingLessonReservationRepository.findOnlyFutureReservationsForInstructor(instructorId);
        else
            return fishingLessonReservationRepository.findAllReservationsForInstructor(instructorId);
    }

    @Override
    public Integer findAvgGradeForFishingLessonId(Integer id) {
        return fishingLessonFeedbackRepository.findAverageGradeFishingLessonByFishingLessonId(id);
    }

    @Override
    public FishingLesson findFishingLessonById(Integer id) {
        return fishingLessonRepository.findById(id).get();
    }

    @Override
    public List<TermFishingLesson> findFreeTermsForFishingLesson(FishingLesson fishingLesson) {
        return termFishingLessonRepository.findByFishingLesson(fishingLesson);
    }

    @Override
    public List<FishingLessonReservation> findAllReservationsForFishingLesson(Integer fishingLessonId) {
        return fishingLessonReservationRepository.findAllByFishingLessonId(fishingLessonId);
    }

    @Override
    public Collection<String> findAllUsernames() {
        return instructorRepository.findAllUsernames();
    }
}
