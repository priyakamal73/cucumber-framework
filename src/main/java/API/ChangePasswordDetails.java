package API;

public class ChangePasswordDetails {
    String currentPassword;

    String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword1(String newPassword) {
        this.newPassword = newPassword;
    }
}