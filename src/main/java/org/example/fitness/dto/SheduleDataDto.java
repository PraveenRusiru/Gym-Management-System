package org.example.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SheduleDataDto {
    private int day;
    private int priorityNum;
    private  String workout;
    private int weight;
    private int set;
    private int rep;
    private int rest;
    private  int tempo;
}
