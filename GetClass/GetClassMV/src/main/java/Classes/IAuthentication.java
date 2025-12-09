package Classes;

public interface IAuthentication {


    boolean login(String email, String password);
    void logout();
    void setNewPassword(String oldPassword, String newPassword);
    boolean validatePassword(String password);


}
