package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.DB.DBConnection;
import org.example.fitness.dto.OrderDetailDto;
import org.example.fitness.dto.OrderDto;
import org.example.fitness.utill.Crudutill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersModel {
    private OrderDetailModel orderDetailModel=new OrderDetailModel();
    public static String getNextOrderId() throws SQLException {
        ResultSet rst = Crudutill.execute("select order_id from Orders order by order_id desc  limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("O%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "O001"; // Return the default customer ID if no data is found
    }
    public boolean isOrderSaved(OrderDto orderDto) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        try {
         connection.setAutoCommit(false);
         boolean isSaved = Crudutill.execute("insert into Orders values (?,?,?,?,?)",
                 orderDto.getOrderId(),
                 orderDto.getClientId(),
                 orderDto.getDate(),
                 orderDto.getTotalAmount(),
                 orderDto.getDiscount()
         );
         if(isSaved) {
             boolean isOrderDetailsSaved=true;
                for (OrderDetailDto orderDetailDto:orderDto.getOrderDetails()){
                    isOrderDetailsSaved=orderDetailModel.isOrderDetailSaved(orderDetailDto,orderDto.getOrderId());
                    if(!isOrderDetailsSaved) {
                        isOrderDetailsSaved=false;
                    }
                }
                if(isOrderDetailsSaved) {
                        connection.commit();
                        new Alert(Alert.AlertType.CONFIRMATION, "Order Saved").show();
                        return true;
                }
         }
            connection.rollback();
         new Alert(Alert.AlertType.ERROR, "Order Saved error").show();
            return false;
        } catch (Exception e) {
            // rolback
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }finally {
            connection.setAutoCommit(true);
        }

    }
}
