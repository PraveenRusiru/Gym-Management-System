package org.example.fitness.model;

import javafx.scene.control.Alert;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminModel {
        public boolean isUserValide(String username,String password) throws SQLException {
            boolean isValid = false;
            ResultSet rst = Crudutill.execute("SELECT AES_DECRYPT(password, 'your_secret_key') AS decrypted_password FROM Admin WHERE user_name = ?",username );
            if(rst.next()){
                String decrypted_password = rst.getString("decrypted_password");
                isValid= password.equals(decrypted_password);
            }
            return isValid;
    }
    public boolean isPasswordUpdated(String newPasswrod) throws SQLException {
            boolean isUpdated = Crudutill.execute("UPDATE Admin SET password=AES_ENCRYPT(?,'your_secret_key') WHERE admin_id='A001'",newPasswrod);
            if(isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Password updated successfully.").show();
                return true;
            }else{
                new Alert(Alert.AlertType.ERROR,"Password not updated successfully.").show();
                return false;
            }
    }
}
