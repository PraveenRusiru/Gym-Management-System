package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.dto.ClientContactDto;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientContactModel {
  static  ClientContactDto clientContactDto;
  public void setClientContactDto(ClientContactDto clientContactDto) {
      this.clientContactDto = clientContactDto;
  }
  public ClientContactDto getClientContactDto() {
      return clientContactDto;
  }
  public boolean isClientContactSaved() throws SQLException {
      return Crudutill.execute("insert into ClientContact values (?,?,?,?)",
              getClientContactDto().getNic(),
              getClientContactDto().getPhone(),
              getClientContactDto().getEmail(),
              getClientContactDto().getClientId()
              );
  }
    public ArrayList<ClientContactDto> getClientContactData() throws SQLException {
        ArrayList<ClientContactDto> clientContactDtosDtos = new ArrayList<>();
        ResultSet rst=Crudutill.execute("select * from ClientContact order by Client_Id asc");
        while (rst.next()) {
            ClientContactDto clientContactDto1=new ClientContactDto();
            clientContactDto1.setNic(rst.getString(1));
            clientContactDto1.setPhone(rst.getString(2));
            clientContactDto1.setEmail(rst.getString(3));
            clientContactDto1.setClientId(rst.getString(4));
            clientContactDtosDtos.add(clientContactDto1);
        }
        return clientContactDtosDtos;
    }
    public boolean updateClientContact(ClientContactDto clientContactDto,String clientId) throws SQLException {
      ResultSet rst= Crudutill.execute("select NIC_Number from ClientContact where Client_Id=?",clientId);
        String nic="";
      if(rst.next()){
           nic=rst.getString(1);

      }else{
          System.out.println("No such client");
      }
        boolean isClientContactUpdated=false;
        System.out.println("NIC "+nic);
      if(clientContactDto.getNic().equals(nic)){
          isClientContactUpdated=Crudutill.execute("update ClientContact set Phone_Number=?,Email=? where Client_Id=?",clientContactDto.getPhone(),clientContactDto.getEmail(),clientId);
          if(isClientContactUpdated){
              new Alert(Alert.AlertType.INFORMATION,"Client Contact Updated").show();
          }else{
              new Alert(Alert.AlertType.ERROR,"Client Contact Not Updated").show();
          }
      }else{
          isClientContactUpdated=Crudutill.execute("update ClientContact set NIC_Number=?,Phone_Number=?,Email=? where Client_Id=?",clientContactDto.getNic(),clientContactDto.getPhone(),clientContactDto.getEmail(),clientId);
          if(isClientContactUpdated){
              new Alert(Alert.AlertType.INFORMATION,"Client Contact Updated").show();
          }else{
              new Alert(Alert.AlertType.ERROR,"Client Contact Not Updated").show();
          }
      }

        return isClientContactUpdated;
  }

  public boolean deleteClientContact(String clientId) throws SQLException {
      boolean isClientContactDeleted=Crudutill.execute("delete from ClientContact where Client_Id=?",clientId);
      if(isClientContactDeleted){
          new Alert(Alert.AlertType.INFORMATION,"Client Contact Deleted").show();
      }else{
          new Alert(Alert.AlertType.ERROR,"Client Contact  Delete Fail").show();
      }
      return isClientContactDeleted;
  }


  public String getClientId(String nic) throws SQLException {
        ResultSet rst=Crudutill.execute("select Client_Id from ClientContact where NIC_Number=?",nic);
        String clientId="";
        if(rst.next()){
            clientId=rst.getString(1);
        }else{
            new Alert(Alert.AlertType.INFORMATION,"No such client with NIC "+nic).show();
        }
        return clientId;
  }
}
