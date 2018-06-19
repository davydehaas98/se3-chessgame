package chessgameapi;

import chessgameapi.dto.LoginRequestDTO;
import chessgameapi.dto.RegisterRequestDTO;
import chessgameapi.dto.RequestPasswordRequestDTO;
import chessgamedal.interfaces.IPlayerContext;
import chessgamerepository.PlayerRepository;
import chessgamerepository.interfaces.IPlayerRepository;
import com.google.gson.Gson;
import model.Player;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/chessgame")
public class RESTService {
    private IPlayerRepository playerRepository;
    private Gson gson;

    public RESTService() {
        playerRepository = new PlayerRepository();
        gson = new Gson();
    }

    public RESTService(IPlayerContext context) {
        playerRepository = new PlayerRepository(context);
        gson = new Gson();
    }

    @POST
    @Path("/player/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerPlayer(String input) {
        System.out.println("[REST] RegisterPlayer");
        if (input != null) {
            RegisterRequestDTO registerRequestDTO = gson.fromJson(input, RegisterRequestDTO.class);
            boolean result = playerRepository.registerPlayer(registerRequestDTO.getName(), registerRequestDTO.getPassword());
            return Response.status(200).entity(ResponseHelper.getRegisterResultDTOResponse(result)).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }

    @POST
    @Path("/player/requestpassword")
    @Consumes("application/json")
    @Produces("application/json")
    public Response requestPassword(String input) {
        System.out.println("[REST] RequestPassword");
        if (input != null) {
            RequestPasswordRequestDTO requestPasswordRequestDTO = gson.fromJson(input, RequestPasswordRequestDTO.class);
            String result = playerRepository.requestPassword(requestPasswordRequestDTO.getName());
            return Response.status(200).entity(ResponseHelper.getRequestPasswordResultDTOResponse(result)).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }

    @POST
    @Path("/player/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginPlayer(String input) {
        System.out.println("[REST] LoginPlayer");
        if (input != null) {
            LoginRequestDTO loginRequestDTO = gson.fromJson(input, LoginRequestDTO.class);
            Player result = playerRepository.loginPlayer(loginRequestDTO.getName(), loginRequestDTO.getPassword());
            return Response.status(200).entity(ResponseHelper.getLoginResultDTOResponse(result)).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }
}
