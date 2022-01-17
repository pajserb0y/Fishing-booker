package com.springboot.app.service;

import com.springboot.app.model.*;
import com.springboot.app.model.dto.CustomerDTO;
import com.springboot.app.model.dto.EntityIdAndCustomerUsername;
import com.springboot.app.repository.BoatRepository;
import com.springboot.app.repository.CustomerRepository;
import com.springboot.app.repository.FishingLessonRepository;
import com.springboot.app.repository.WeekendHouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleService roleService;
    private final WeekendHouseRepository weekendHouseRepository;
    private final BoatRepository boatRepository;
    private final FishingLessonRepository fishingLessonRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, RoleService roleService, WeekendHouseRepository weekendHouseRepository,
                               BoatRepository boatRepository, FishingLessonRepository fishingLessonRepository) {
        this.customerRepository = customerRepository;
        this.roleService = roleService;
        this.weekendHouseRepository = weekendHouseRepository;
        this.boatRepository = boatRepository;
        this.fishingLessonRepository = fishingLessonRepository;
    }

    public Customer saveCustomer(Customer customer) {
        List<Role> roles = roleService.findByName("ROLE_CUSTOMER");
        customer.setRole(roles.get(0));
        if (customer.getPenals() != 0)
            customer.setPenals((int)(Math.random()*5));
        customerRepository.save(customer);
        return customer;
    }

    public Customer findByHashCode(String hashCode) {
        return customerRepository.findByHashCode(hashCode);
    }

    @Override
    public Customer findByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
       // if(customer.getPenals())
        return customer;
    }

    @Override
    public Customer changeCustomer(CustomerDTO customerDTO) {
        Customer customer = findByUsername(customerDTO.getUsername());

        customer.setPhone(customerDTO.getPhone());
        customer.setCountry(customerDTO.getCountry());
        customer.setCity(customerDTO.getCity());
        customer.setAddress(customerDTO.getAddress());
        customer.setLastName(customerDTO.getLastName());
        customer.setFirstName(customerDTO.getFirstName());

        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void setWantedToDelete(Integer id) {
        Optional<Customer> customer = findById(id);
        if(customer.isPresent()) {
            customer.get().setWantDeleting(true);
            customerRepository.save(customer.get());
        }
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Collection<String> findAllUsernames() {
        return customerRepository.findAllUsernames();
    }

    @Override
    public void subscribeWeekendHouse(EntityIdAndCustomerUsername dto) {
        Customer customer = findByUsername(dto.getUsername());
        Optional<WeekendHouse> house = weekendHouseRepository.findById(dto.getId());
        Set<WeekendHouse> houses = customer.getSubscribedWeekendHouses();
        houses.add(house.get());
        customer.setSubscribedWeekendHouses(houses);
        customerRepository.save(customer);
    }

    @Override
    public void subscribeFishingLesson(EntityIdAndCustomerUsername dto) {
        Customer customer = findByUsername(dto.getUsername());
        Optional<FishingLesson> house = fishingLessonRepository.findById(dto.getId());
        Set<FishingLesson> lessons = customer.getSubscribedFishingLessons();
        lessons.add(house.get());
        customer.setSubscribedFishingLessons(lessons);
        customerRepository.save(customer);
    }

    @Override
    public void subscribeBoat(EntityIdAndCustomerUsername dto) {
        Customer customer = findByUsername(dto.getUsername());
        Optional<Boat> house = boatRepository.findById(dto.getId());
        Set<Boat> boats = customer.getSubscribedBoats();
        boats.add(house.get());
        customer.setSubscribedBoats(boats);
        customerRepository.save(customer);
    }

    @Override
    public void unsubscribeFishingLesson(EntityIdAndCustomerUsername dto) {
        Customer customer = findByUsername(dto.getUsername());
        Set<FishingLesson> lessons = customer.getSubscribedFishingLessons();
        for (FishingLesson temp : customer.getSubscribedFishingLessons())
            if(temp.getId() == dto.getId()) {
                lessons.remove(temp);
                break;
            }
        customer.setSubscribedFishingLessons(lessons);
        customerRepository.save(customer);
    }

    @Override
    public void unsubscribeBoat(EntityIdAndCustomerUsername dto) {
        Customer customer = findByUsername(dto.getUsername());
        Set<Boat> boats = customer.getSubscribedBoats();
        for (Boat temp : customer.getSubscribedBoats())
            if(temp.getId() == dto.getId()) {
                boats.remove(temp);
                break;
            }
        customer.setSubscribedBoats(boats);
        customerRepository.save(customer);
    }

    @Override
    public void unsubscribeWeekendHouse(EntityIdAndCustomerUsername dto) {
        Customer customer = findByUsername(dto.getUsername());
        Set<WeekendHouse> houses = customer.getSubscribedWeekendHouses();
        for (WeekendHouse temp : customer.getSubscribedWeekendHouses())
            if(temp.getId() == dto.getId()) {
                houses.remove(temp);
                break;
            }
        customer.setSubscribedWeekendHouses(houses);
        customerRepository.save(customer);
    }
}
