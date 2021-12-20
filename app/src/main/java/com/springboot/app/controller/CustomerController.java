package com.springboot.app.controller;

import com.springboot.app.model.Customer;
import com.springboot.app.model.dto.CustomerDTO;
import com.springboot.app.service.CustomerService;
import com.springboot.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@CrossOrigin(origins = "http://localhost:4200")  //sluzi za povezivanje sa frontom
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final EmailService emailService;

    @Autowired
    public CustomerController(CustomerService customerService, EmailService emailService) {
        this.customerService = customerService;
        this.emailService = emailService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerDto, BindingResult result) throws Exception {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
//            return new ModelAndView("createCustomer", "formErrors", result.getAllErrors());
        }
        Customer newCustomer = customerService.saveCustomer(new Customer(customerDto));
        emailService.sendActivationMailAsync(newCustomer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/activate")
    public ResponseEntity<?> activateCustomerAccount(WebRequest request, @RequestParam("hashCode") String hashCode) {
        Locale locale = request.getLocale();

        Customer verificationCustomer = customerService.findByHashCode(hashCode);
        if (verificationCustomer == null || verificationCustomer.isActivated()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        verificationCustomer.setActivated(true);
        customerService.saveCustomer(verificationCustomer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:4200");
        return new ResponseEntity<String>(headers,HttpStatus.FOUND);
    }


}
