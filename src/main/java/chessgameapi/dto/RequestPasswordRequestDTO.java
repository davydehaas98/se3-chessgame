package chessgameapi.dto;

public class RequestPasswordRequestDTO extends BaseRequestDTO {
    private String name;

    public RequestPasswordRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
