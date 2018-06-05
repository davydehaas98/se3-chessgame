package chessgameapi.dto;

public class LoginDTO {
    private String name;
    private String password;


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
