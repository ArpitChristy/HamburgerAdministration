package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Document(collection = "location")
@Data
public class Location {
    @Id
    private int locationId;
    private String name;
    private String longitude;
    private String latitude;
    private String address;
    private String phone;

}
