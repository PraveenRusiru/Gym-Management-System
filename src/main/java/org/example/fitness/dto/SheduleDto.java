package org.example.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SheduleDto {

    private String sheduleId;
    private String clientId;
    private Date issuedDate;
    private Date expiredDate;
    private String goal;
}
