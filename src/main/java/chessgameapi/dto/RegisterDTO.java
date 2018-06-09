package chessgameapi.dto;

public class RegisterDTO {
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

    public RegisterDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
