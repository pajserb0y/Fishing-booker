package com.springboot.app.service;

import com.springboot.app.model.Instructor;
import com.springboot.app.model.Role;
import com.springboot.app.model.dto.InstructorDTO;
import com.springboot.app.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstructorServiceImpl implements InstructorService{
    private final InstructorRepository instructorRepository;
    private final RoleService roleService;

    public InstructorServiceImpl(InstructorRepository instructorRepository, RoleService roleService) {
        this.instructorRepository = instructorRepository;
        this.roleService = roleService;
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
        Instructor instructor = findByUsername(instructorDTO.getUsername());

        instructor.setPhone(instructorDTO.getPhone());
        instructor.setCountry(instructorDTO.getCountry());
        instructor.setCity(instructorDTO.getCity());
        instructor.setAddress(instructorDTO.getAddress());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setMotive(instructorDTO.getMotive());

        saveInstructor(instructor);
        return null;
    }
}
