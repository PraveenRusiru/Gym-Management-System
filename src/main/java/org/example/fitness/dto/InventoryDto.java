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
public class InventoryDto {
    private String inventoryId;
    private String suppliemntId;
    private int stockQty;
    //private int reorderQty;
    private Date purchaseDate;
    private String supplierName;
    private double wholesalePrice;
}
