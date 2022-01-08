package com.springboot.app.model;

import com.springboot.app.model.dto.UserDTO;
import com.springboot.app.model.dto.WeekendHouseOwnerDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

@Entity
public class WeekendHouseOwner extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "weekend_house_owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
    private String motive;

    public WeekendHouseOwner(Integer id, String firstName, String lastName, String email, String username, String password, String address, String city, String country, String phone, boolean isDeleted, boolean isActivated, List<Role> roles, String motive) {
        super(id, firstName, lastName, email, username, password, address, city, country, phone, isDeleted, isActivated);
        this.roles = roles;
        this.motive = motive;
    }

    public WeekendHouseOwner(WeekendHouseOwnerDTO weekendHouseOwnerDTO, List<Role> roles, String motive) {
        super(weekendHouseOwnerDTO);
        this.roles = roles;
        this.motive = motive;
    }

}
