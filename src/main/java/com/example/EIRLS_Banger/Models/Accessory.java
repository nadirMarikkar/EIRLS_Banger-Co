package com.example.EIRLS_Banger.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "accessory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accessoryId;
    private String accessoryName;
    private String accessoryDescription;
    private int quantity;
    private String availability;




}
