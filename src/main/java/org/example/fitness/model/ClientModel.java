package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientModel implements FirstAnalysis{
    static ClientDto clientDto;
    public static String getNextClientId() throws SQLException {

        ResultSet rst = Crudutill.execute("select client_id from Client order by client_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001"; // Return the default customer ID if no data is found
    }
    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }
    public  ClientDto getClientDto() {
        return clientDto;
    }
    public boolean isClientSaved() throws SQLException {
        return Crudutill.execute("insert into Client values (?,?,?,?,?,?,?,?,?)",
                getClientDto().getClientId(),
                getClientDto().getClientName(),
                getClientDto().getAge(),
                getClientDto().getGender(),
                getClientDto().getGoal(),
                getClientDto().getHeight(),
                getClientDto().getWeight(),
                getClientDto().getFatPer(),
                getClientDto().getJoinedDate()
        );
    }
    public ArrayList<ClientDto> getClientData() throws SQLException {
        ArrayList<ClientDto> clientDtos = new ArrayList<>();
        ResultSet rst=Crudutill.execute("select * from Client");
        while (rst.next()) {
            ClientDto clientDto1=new ClientDto();
            clientDto1.setClientId(rst.getString(1));
            clientDto1.setClientName(rst.getString(2));
            clientDto1.setAge(rst.getInt(3));
            clientDto1.setGender(rst.getString(4));
            clientDto1.setGoal(rst.getString(5));
            clientDto1.setHeight(rst.getInt(6));
            clientDto1.setWeight(rst.getInt(7));
            clientDto1.setFatPer(rst.getDouble(8));
            clientDto1.setJoinedDate(rst.getDate(9));
            clientDtos.add(clientDto1);
        }
        return clientDtos;
    }
    public boolean deleteClient(String clientId) throws SQLException {
        boolean isaffectedRows=Crudutill.execute("delete from Client where client_id=?",clientId);
        if(isaffectedRows){
            new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
        return isaffectedRows;
    }
    public boolean updateClient(String clientId, ClientDto clientDto) throws SQLException {
        boolean isClientDataUpdated= Crudutill.execute("update Client set client_name =? ,age=?,gender=?,goal=?,height=?,weight=?,fat_per=?,joined_date=? where client_id=?",clientDto.getClientName(),clientDto.getAge(),clientDto.getGender(),clientDto.getGoal(),clientDto.getHeight(),clientDto.getWeight(),clientDto.getFatPer(),clientDto.getJoinedDate(),clientId);
        if(isClientDataUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Updated Successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
        return isClientDataUpdated;
    }

    @Override
    public int getThisMonthCount() {
        ResultSet rst = null;
        try {
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Client WHERE joined_date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE()");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                return Integer.parseInt(rst.getString(1));
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getLastMonthCount() {
        ResultSet rst = null;
        try {
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Client WHERE MONTH(joined_date) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(joined_date) = YEAR(CURDATE() - INTERVAL 1 MONTH)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                return Integer.parseInt(rst.getString(1));
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCount() {
        ResultSet rst= null;
        try {
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Client");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                return Integer.parseInt(rst.getString(1));
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
public String getClientGoal(String clientId) throws SQLException {
        ResultSet rst=Crudutill.execute("select goal from Client where client_id=?",clientId);
        if(rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
}
public String getGoal(String clientId) throws SQLException {
        ResultSet resultSet=Crudutill.execute("select goal from Client where client_id=?",clientId);
        if(resultSet.next()){
            return resultSet.getString(1);
        }else{
            return null;
        }
}

//    public int getThisMonthCount() throws SQLException {
//        ResultSet rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Client WHERE joined_date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE()");
//        if (rst.next()) {
//            return Integer.parseInt(rst.getString(1));
//        }else{
//            return 0;
//        }
//    }
//
//    public int getLastMonthCount() throws SQLException {
//        ResultSet rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Client WHERE MONTH(joined_date) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(joined_date) = YEAR(CURDATE() - INTERVAL 1 MONTH)");
//        if (rst.next()) {
//            return Integer.parseInt(rst.getString(1));
//        }else{
//            return 0;
//        }
//    }
//
//    public int getClientCount() throws SQLException {
//        ResultSet rst=Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Client");
//        if (rst.next()) {
//            return Integer.parseInt(rst.getString(1));
//        }else{
//            return 0;
//        }
//    }
}
