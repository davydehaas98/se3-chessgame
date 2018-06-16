package chessgameapi;

import chessgameapi.dto.BaseResultDTO;
import chessgameapi.dto.LoginResultDTO;
import chessgameapi.dto.RegisterResultDTO;
import chessgameapi.dto.RequestPasswordResultDTO;
import com.google.gson.Gson;
import model.Player;

class ResponseHelper {
    private static final Gson gson = new Gson();

    static String getErrorResponse() {
        BaseResultDTO result = new BaseResultDTO();
        result.setSuccess(false);
        return gson.toJson(result);
    }

    static String getRegisterResultDTOResponse(boolean success) {
        RegisterResultDTO result = new RegisterResultDTO();
        result.setSuccess(success);
        return gson.toJson(result);
    }

    static String getRequestPasswordResultDTOResponse(String password) {
        RequestPasswordResultDTO result = new RequestPasswordResultDTO();
        result.setPassword(password);
        return gson.toJson(result);
    }

    static String getLoginResultDTOResponse(Player player) {
        LoginResultDTO result = new LoginResultDTO();
        result.setPlayer(player);
        return gson.toJson(result);
    }
}
