package com.example.EIRLS_Banger.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "rental")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;
    // private String clientNic;
    //private Integer vehicleID;
    // private Integer accessoryId;
    private Date pickupDate;
    private String pickupTime;
    private String rentalType;//(hours/days)
    private String rentalDuration;
    private Date returnDueDate;
    private String returnDueTime;
    private String rentalStatus;
    private String rentalCost;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle", referencedColumnName = "vehicleId")
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accessory", referencedColumnName = "accessoryId")
    private Accessory accessory;

    }