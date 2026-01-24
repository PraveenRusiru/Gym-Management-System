package org.example.fitness.model;

import org.example.fitness.dto.OrderDetailDto;
import org.example.fitness.utill.Crudutill;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {
    private    InventoryModel inventoryModel=new InventoryModel();

    public boolean isOrderDetailSaved(OrderDetailDto orderDetailDto,String orderId) throws SQLException {
        boolean isOrderDetailsSaved= Crudutill.execute("insert into Order_Detail values (?,?,?,?)",
                orderId,
                orderDetailDto.getSupplimentId(),
                orderDetailDto.getQuantity(),
                orderDetailDto.getPriceForItems()
                );
        boolean isItemQtyReduced=inventoryModel.isQtyReduced(orderDetailDto);
        System.out.println("isItemQtyReduced "+isItemQtyReduced);
        System.out.println("isOrderDetailsSaved "+isOrderDetailsSaved);
        if(isOrderDetailsSaved && isItemQtyReduced){
            return true;
        }else{

            return false;
        }
    }

}
