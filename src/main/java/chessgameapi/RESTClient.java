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
        final String query = "/register";
        RegisterResultDTO result = executeQueryPost(registerRequestDTO, query, RegisterResultDTO.class);
        return result.isSuccess();
    }

    public String requestPassword(String name) {
        RequestPasswordRequestDTO requestPasswordRequestDTO = new RequestPasswordRequestDTO(name);
        final String query = "/requestpassword";
        RequestPasswordResultDTO result = executeQueryPost(requestPasswordRequestDTO, query, RequestPasswordResultDTO.class);
        return result.getPassword();
    }

    public Player loginPlayer(String name, String password) {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(name, password);
        final String query = "/login";
        LoginResultDTO result = executeQueryPost(loginRequestDTO, query, LoginResultDTO.class);
        return result.getPlayer();
    }
}
