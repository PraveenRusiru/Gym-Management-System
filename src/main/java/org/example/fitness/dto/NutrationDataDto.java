package org.example.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutrationDataDto {
    private String id;
    private int maintainance_calories;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
}
