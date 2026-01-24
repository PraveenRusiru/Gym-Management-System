package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.DB.DBConnection;
import org.example.fitness.dto.SuppliementDto;
import org.example.fitness.utill.Crudutill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplimentModel {
    static SuppliementDto suppliementDto;
    private InventoryModel inventoryModel=new InventoryModel();
    public static String getNextSupplimentId() throws SQLException {
        ResultSet rst = Crudutill.execute("select Supplement.supplement_id from Supplement order by Supplement.supplement_id desc  limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(3); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("Sup%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "Sup001"; // Return the default customer ID if no data is found
    }

    public void setSuppliementDto(SuppliementDto suppliementDto) {
        SupplimentModel.suppliementDto = suppliementDto;
    }
    public SuppliementDto getSuppliementDto() {
        return suppliementDto;
    }

    public boolean isSupplimentSaved() throws SQLException {
        return Crudutill.execute("insert into Supplement values (?,?,?,?,?)",
                suppliementDto.getSuppliementId(),
                suppliementDto.getTitle(),
                suppliementDto.getDescription(),
                suppliementDto.getPrice(),
                suppliementDto.getCategory()
                );
    }

    public boolean saveSupplimentAndInventoyDetail() throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            if(isSupplimentSaved()){
                boolean isInventorySaved=inventoryModel.isInventorySaved();

                if(isInventorySaved){

                    connection.commit();
                    new Alert(Alert.AlertType.INFORMATION,"Suppliment & Inventory Saved").show();
                    return true;
                }else{

                    connection.rollback();
                    System.out.println("Inventory Saved erro");
                    return false;
                }
            }else{

                connection.rollback();
                System.out.println("suppliment saved error");
                return false;
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }finally {
            connection.setAutoCommit(true);
        }


    }

    public ArrayList<SuppliementDto> getAllSuppliement() throws SQLException {
        ArrayList<SuppliementDto> suppliementDtos = new ArrayList<>();
        ResultSet rst = Crudutill.execute("select * from Supplement");
        while (rst.next()) {
            SuppliementDto suppliementDto = new SuppliementDto();
            suppliementDto.setSuppliementId(rst.getString(1));
            suppliementDto.setTitle(rst.getString(2));
            suppliementDto.setDescription(rst.getString(3));
            suppliementDto.setPrice(rst.getDouble(4));
            suppliementDto.setCategory(rst.getString(5));
            suppliementDtos.add(suppliementDto);

        }
        return suppliementDtos;
    }

    public boolean isSupplimentUpdated(SuppliementDto suppliementDto,String supplimentId) throws SQLException {
        boolean isSupplimentUpdated=Crudutill.execute("update Supplement set title=?,description=?,price=?,Category=? where supplement_id=?",
                suppliementDto.getTitle(),
                suppliementDto.getDescription(),
                suppliementDto.getPrice(),
                suppliementDto.getCategory(),
                supplimentId
                );

        if(isSupplimentUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Updated").show();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Update Failed").show();
        }
return isSupplimentUpdated;
    }

    public boolean deleteSuppliment(String supplimentId) throws SQLException {
        boolean isSupplimentDeleted=Crudutill.execute("delete  from Supplement  where supplement_id=?",supplimentId);
        if(isSupplimentDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Deleted").show();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Suppliment Delete Failed").show();
        }
        return isSupplimentDeleted;
    }

}
