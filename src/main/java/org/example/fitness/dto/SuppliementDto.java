package org.example.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuppliementDto {
    private String suppliementId;
    private String title;
    private String description;
    private double price;
    private String category;
}
