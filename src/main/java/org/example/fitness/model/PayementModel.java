package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.DB.DBConnection;
import org.example.fitness.dto.PaymentDto;
import org.example.fitness.utill.Crudutill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayementModel {
    private final ClientModel clientModel;
    private final ClientContactModel clientContactModel;
    private final MembershipModel membershipModel;
    private final SheduleModel sheduleModel;
    private final ExerciseInSheduleModel exerciseInSheduleModel;
    private final NutritionProgrammeModel nutritionProgrammeModel;
    private final NoteForClientModel noteForClientModel;
    public PayementModel() {
        clientModel = new ClientModel();
        clientContactModel = new ClientContactModel();
        membershipModel = new MembershipModel();
        sheduleModel = new SheduleModel();
        exerciseInSheduleModel = new ExerciseInSheduleModel();
        nutritionProgrammeModel = new NutritionProgrammeModel();
        noteForClientModel = new NoteForClientModel();
    }
    public static String getNextPaymentId() throws SQLException {
        ResultSet rst = Crudutill.execute("select payment_id from Payment order by payment_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001"; // Return the default customer ID if no data is found
    }

    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        boolean isMembershipSaved=false;
        boolean isNutrationDataSaved=false;
        try {
            connection.setAutoCommit(false);
            boolean isClientBio=clientModel.isClientSaved();
            if (isClientBio) {
                boolean isClientContactSaved=clientContactModel.isClientContactSaved();
                if (isClientContactSaved) {
                    if(membershipModel.membershipDto!=null) {
                        System.out.println("membership ID :"+membershipModel.membershipDto.getMembershipId());
                         isMembershipSaved=membershipModel.isMembershipSaved();
                    }if(NoteForClientModel.clientNoteDto!=null) {
                        System.out.println("note ID :"+NoteForClientModel.clientNoteDto.getNoteId());
                        boolean isNoteForClientSaved=noteForClientModel.isClientNoteSaved();
                        if(isNoteForClientSaved) {
                            if(SheduleModel.sheduleDto!=null) {
                                boolean isSheduleSaved=sheduleModel.isSheduleDataSaved();
                                if (isSheduleSaved) {
                                    boolean isExercisesDataSaved=exerciseInSheduleModel.isSavedExerciseInShedule();
                                    if(isExercisesDataSaved) {
                                         isNutrationDataSaved=nutritionProgrammeModel.isNutrationProgrammeSaved();
                                    }
                                    else{
                                        connection.rollback();
                                        System.out.println("Nutration Programme data error");
                                        return false;
                                    }
                                }else{
                                    connection.rollback();
                                    System.out.println("Shedule data error");
                                    return false;
                                }
                            }else{
                                connection.rollback();
                                System.out.println("SheduleDto is null");
                                return false;
                            }
                        }else{
                            connection.rollback();
                            System.out.println("NoteForClientDto Error");
                            return false;
                        }

                    }
                    if (isMembershipSaved || isNutrationDataSaved ) {
                        boolean isPaymentSaved=Crudutill.execute("insert into Payment values (?,?,?,?,?,?)",
                                paymentDto.getPaymentId(),
                                membershipModel.getMembershipDto()!=null?membershipModel.getMembershipDto().getMembershipId():null,
                                paymentDto.getAmount(),
                                paymentDto.getDate(),
                                paymentDto.getStatus(),
                                sheduleModel.getSheduleDto()!=null?sheduleModel.getSheduleDto().getSheduleId():null
                        );
                        if(isPaymentSaved) {
                            connection.commit();
                            makeEveryDtoNull();
                            new Alert(Alert.AlertType.CONFIRMATION, "Payment Saved").show();
                            System.out.println("Payment Saved");
                            return true;
                        }else{
                            connection.rollback();
                            System.out.println("IsPaymentSaved error");
                            return false;
                        }
                    }else{
                        connection.rollback();
                        System.out.println("IsMembershipSaved error");
                        return false;
                    }


                }else{
                    connection.rollback();
                    System.out.println("isClientContactSaved error");
                    return false;
                }
            }else{
                connection.rollback();
                System.out.println("isClientSaved error");
                return false;
            }


        }catch (Exception e){
        e.printStackTrace();
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
        //return false;
    }

    public boolean isNewMembershipSaved(PaymentDto paymentDto) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isMembershipSaved=membershipModel.isMembershipSaved();
            if(isMembershipSaved) {
                boolean isPaymentSavedForNewMembership=PaymentSaving(paymentDto);
                if(isPaymentSavedForNewMembership) {
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION, "Membership Saved").show();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
            makeEveryDtoNull();
        }

    }
    public boolean IsMembershipUpdatedPayment(PaymentDto paymentDto) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isMembershipUpdated=membershipModel.isMembershipUpdated();
            if(isMembershipUpdated) {
                boolean isPaymentSavedForUpdatedMembership=PaymentSaving(paymentDto);
                if(isPaymentSavedForUpdatedMembership) {
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION, "Membership Updated").show();
                    return true;
                }
            }
            connection.rollback();
            new Alert(Alert.AlertType.ERROR, "Membership Updating Failed ! ").show();
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
            makeEveryDtoNull();
        }
    }
    public boolean PaymentSaving(PaymentDto paymentDto) throws SQLException {
        return Crudutill.execute("insert into Payment values (?,?,?,?,?,?)",
                paymentDto.getPaymentId(),
                membershipModel.getMembershipDto()!=null?membershipModel.getMembershipDto().getMembershipId():null,
                paymentDto.getAmount(),
                paymentDto.getDate(),
                paymentDto.getStatus(),
                sheduleModel.getSheduleDto()!=null?sheduleModel.getSheduleDto().getSheduleId():null
        );
    }
    public boolean isNewSheduleSaved(PaymentDto paymentDto) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            if(noteForClientModel.isClientNoteSaved()) {
                if(sheduleModel.isSheduleDataSaved()){
                    if(exerciseInSheduleModel.isSavedExerciseInShedule()){
                        if(nutritionProgrammeModel.isNutrationProgrammeSaved()){
                            if(PaymentSaving(paymentDto)) {
                                connection.commit();
                                System.out.println("Payment Saved");
                                return true;
                            }
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }finally {
            connection.setAutoCommit(true);
            makeEveryDtoNull();
        }

    }
    private void makeEveryDtoNull() {

        clientModel.setClientDto(null);
        clientContactModel.setClientContactDto(null);
        membershipModel.setMembershipDto(null);
        sheduleModel.setSheduleDto(null);
        noteForClientModel.setClientNoteDto(null);
        exerciseInSheduleModel.setSheduleDataDtos(null);
        nutritionProgrammeModel.setNutrationDataDto(null);
    }
}
