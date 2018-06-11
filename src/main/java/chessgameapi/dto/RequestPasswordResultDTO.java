package chessgameapi.dto;

public class RequestPasswordResultDTO extends BaseResultDTO{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
