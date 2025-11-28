package Classes;

public interface IAuthentication {


    public boolean login(String email, String password);
    public void logout();
    public void setNewPassword(String oldPassword, String newPassword);
    public boolean validatePassword(String password);

    
}
