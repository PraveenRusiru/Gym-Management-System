package org.example.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String orderId;
    private String clientId;
    private Date date;
    private double totalAmount;
    private double discount;
    private ArrayList<OrderDetailDto> orderDetails;
}
