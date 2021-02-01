package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="menu")
public class Menu extends Category{
    @Id
    private int menuId;
    private String item;
    private String price;
}
