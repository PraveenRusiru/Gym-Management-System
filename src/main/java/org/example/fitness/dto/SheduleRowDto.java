package org.example.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SheduleRowDto {
    private int priorityNum;
    private String workout;
    private int weight;
    private int set;
    private int rep;
    private int tempo;
    private int  rest;
}
