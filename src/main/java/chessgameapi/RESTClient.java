package chessgameapi;

import chessgameapi.dto.*;
import model.Player;

public class RESTClient extends RESTClientBase implements IRESTClient {
    private static final String URL = "http://localhost:8095/chessgame";

    public String getBaseURL() {
        return URL;
    }

    public boolean registerPlayer(String name, String password) {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO(name, password);
        return executeQueryPost(registerRequestDTO, "/player/register", RegisterResultDTO.class).isSuccess();
    }

    public String requestPassword(String name) {
        RequestPasswordRequestDTO requestPasswordRequestDTO = new RequestPasswordRequestDTO(name);
        return executeQueryPost(requestPasswordRequestDTO, "/player/requestpassword", RequestPasswordResultDTO.class).getPassword();
    }

    public Player loginPlayer(String name, String password) {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(name, password);
        return executeQueryPost(loginRequestDTO, "/player/login", LoginResultDTO.class).getPlayer();
    }
}
