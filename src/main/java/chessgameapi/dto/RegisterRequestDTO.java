package chessgameapi.dto;

public class RegisterRequestDTO extends BaseRequestDTO {
    private String name;
    private String password;

    public RegisterRequestDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
