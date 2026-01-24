package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.dto.MembershipDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MembershipModel implements FirstAnalysis {
    static MembershipDto membershipDto;
    private ClientModel clientModel;
    public MembershipModel() {
        clientModel = new ClientModel();
    }
    public static String getNextMembershipId() throws SQLException {
        ResultSet rst = Crudutill.execute("select member_id from Membership order by member_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("M%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "M001"; // Return the default customer ID if no data is found
    }
    public void setMembershipDto(MembershipDto membershipDto) {
        MembershipModel.membershipDto = membershipDto;
    }
    public MembershipDto getMembershipDto() {
        return membershipDto;
    }
    public boolean isMembershipSaved() throws SQLException {
        return Crudutill.execute("insert into Membership values (?,?,?,?,?)",
                getMembershipDto().getMembershipId(),
                clientModel.getClientDto().getClientId(),
                getMembershipDto().getStartDate(),
                getMembershipDto().getEndDate(),
                getMembershipDto().getMembershipType()
                );
    }
    public ArrayList<String[]> getMembershipData() throws SQLException {

        ResultSet rst=Crudutill.execute("select * from Membership");
        ArrayList<String[]> membershipArrays=new ArrayList<>();
        while (rst.next()) {
            String[] membershipData = new String[5];
            membershipData[0] = rst.getString(1);
            membershipData[1] = rst.getString(2);
            membershipData[2] = rst.getString(3);
            membershipData[3] = rst.getString(4);
            membershipData[4] = rst.getString(5);
            membershipArrays.add(membershipData);
        }
        return membershipArrays;
    }
    public boolean deleteMembership(String membershipID) throws SQLException {
        boolean isMembershipDeleted=Crudutill.execute("delete from Membership where member_id=?", membershipID);
        if(isMembershipDeleted){
            new Alert(Alert.AlertType.INFORMATION, "Membership deleted").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Membership could not be deleted").show();
        }
        return isMembershipDeleted;
    }
    public boolean isMembershipUpdated() throws SQLException {
        boolean updated=Crudutill.execute("update Membership set start_date=?,end_date=?,membership_type=? where member_id=?",membershipDto.getStartDate(),membershipDto.getEndDate(),membershipDto.getMembershipType(),membershipDto.getMembershipId());
        if(updated){
            System.out.println("Membership updated");
        }else{
            System.out.println("Membership not updated");
        }
        return updated;
    }

    @Override
    public int getThisMonthCount() {
        ResultSet rst = null;
        try {
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Membership WHERE start_date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE()");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                try {
                    return Integer.parseInt(rst.getString(1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Membership WHERE MONTH(start_date) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(start_date) = YEAR(CURDATE() - INTERVAL 1 MONTH)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                try {
                    return Integer.parseInt(rst.getString(1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Membership");
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
//    public int getThisMonthCount() throws SQLException {
//        ResultSet rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Membership WHERE start_date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE()");
//        if (rst.next()) {
//            return Integer.parseInt(rst.getString(1));
//        }else{
//            return 0;
//        }
//    }
//
//    public int getLastMonthCount() throws SQLException {
//        ResultSet rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Membership WHERE MONTH(start_date) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(start_date) = YEAR(CURDATE() - INTERVAL 1 MONTH)");
//        if (rst.next()) {
//            return Integer.parseInt(rst.getString(1));
//        }else{
//            return 0;
//        }
//    }
//
//    public int getCount() throws SQLException {
//        ResultSet rst=Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Membership");
//        if (rst.next()) {
//            return Integer.parseInt(rst.getString(1));
//        }else{
//            return 0;
//        }
//    }
}
