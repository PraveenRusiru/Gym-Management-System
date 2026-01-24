package org.example.fitness.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String clientId;
    private String clientName;
    private  int age;
    private String gender;
    private String goal;
    private int height;
    private int weight;
    private double fatPer;
    private Date joinedDate;

}
