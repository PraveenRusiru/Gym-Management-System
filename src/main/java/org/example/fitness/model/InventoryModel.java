package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.dto.InventoryDto;
import org.example.fitness.dto.OrderDetailDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryModel {
    static InventoryDto inventoryDto;

    public static String getNextInventoryId() throws SQLException {
        ResultSet rst = Crudutill.execute("select inventory_id from Inventory order by inventory_id desc  limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("I%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "I001"; // Return the default customer ID if no data is found
    }


    public void setInventoryDto(InventoryDto inventoryDto) {
        InventoryModel.inventoryDto = inventoryDto;
    }
    public InventoryDto getInventoryDto() {
        return inventoryDto;
    }
    public boolean isInventorySaved() throws SQLException {
        return Crudutill.execute("insert into Inventory (inventory_id, supplement_id, stock_qty, purchase_date, supplier, wholesale_price)  values (?,?,?,?,?,?)",
                    inventoryDto.getInventoryId(),
                inventoryDto.getSuppliemntId(),
                inventoryDto.getStockQty(),
                inventoryDto.getPurchaseDate(),
                inventoryDto.getSupplierName(),
                inventoryDto.getWholesalePrice()
                );

    }

    public ArrayList<InventoryDto> getAllInventory() throws SQLException {
        ResultSet rst = Crudutill.execute("select * from Inventory");
        ArrayList<InventoryDto> inventoryDtos = new ArrayList<>();
        while (rst.next()) {
            InventoryDto inventoryDto = new InventoryDto();
            inventoryDto.setInventoryId(rst.getString(1));
            inventoryDto.setSuppliemntId(rst.getString(2));
            inventoryDto.setStockQty(rst.getInt(3));

            inventoryDto.setPurchaseDate(rst.getDate(6));
            inventoryDto.setSupplierName(rst.getString(7));
            inventoryDto.setWholesalePrice(rst.getDouble(8));
            inventoryDtos.add(inventoryDto);

        }
        return inventoryDtos;
    }

    public boolean updateInventory(InventoryDto inventoryDto,String inventoryId) throws SQLException {
        boolean isInventoryUpdated=Crudutill.execute("update Inventory set stock_qty=?,purchase_date=?,supplier=?,wholesale_price=? where inventory_id=?",
                inventoryDto.getStockQty(),
                inventoryDto.getPurchaseDate(),
                inventoryDto.getSupplierName(),
                inventoryDto.getWholesalePrice(),
                inventoryId
                );
                if(isInventoryUpdated){
                    new Alert(Alert.AlertType.INFORMATION, "Inventory updated successfully").show();
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Inventory update failed").show();
                }
                return isInventoryUpdated;
    }
    public boolean isQtyReduced(OrderDetailDto orderDetailDto) throws SQLException {
            return Crudutill.execute("update Inventory set stock_qty=stock_qty-? where supplement_id=?",
                    orderDetailDto.getQuantity(),
                    orderDetailDto.getSupplimentId()
                    );
    }
        public int howManyQtyRemained(String supplimentId) throws SQLException {
        ResultSet rst=Crudutill.execute("select Inventory.stock_qty from Inventory where supplement_id=?",
                supplimentId
                );
        if(rst.next()){
            return rst.getInt(1);
        }else{
            System.out.println("howManyQtyRemained Error");
            return 0;
        }
        }
}
