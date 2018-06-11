package chessgameapi;

import chessgameapi.dto.BaseResultDTO;
import chessgameapi.dto.LoginRequestDTO;
import chessgameapi.dto.RegisterRequestDTO;
import chessgameapi.dto.RequestPasswordRequestDTO;
import chessgamedal.MySQLContext.MySQLPlayerContext;
import chessgamerepository.PlayerRepository;
import chessgamerepository.interfaces.IPlayerRepository;
import com.google.gson.Gson;
import model.Player;
import org.eclipse.persistence.annotations.Partitioned;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/chessgame")
public class RESTService {
    private IPlayerRepository playerRepository;

    public RESTService() {
        playerRepository = new PlayerRepository(new MySQLPlayerContext());
    }

    @GET
    @Path("/get")
    public Response defaultGet() {
        System.out.println("[REST] defaultGet");
        return Response.status(200).entity(new BaseResultDTO()).build();
    }

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerPlayer(RegisterRequestDTO requestDTO) {
        System.out.println("[REST] RegisterPlayer");
        if (requestDTO != null) {
            boolean result = playerRepository.registerPlayer(requestDTO.getName(), requestDTO.getPassword());
            return Response.status(200).entity(ResponseHelper.getRegisterResultDTOResponse(result)).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }
    @POST
    @Path("/requestpassword")
    @Consumes("application/json")
    @Produces("application/json")
    public Response requestPassword(RequestPasswordRequestDTO requestDTO) {
        System.out.println("[REST] RequestPassword");
        if (requestDTO != null) {
            String result = playerRepository.requestPassword(requestDTO.getName());
            return Response.status(200).entity(ResponseHelper.getRequestPasswordResultDTOResponse(result)).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginPlayer(LoginRequestDTO requestDTO) {
        System.out.println("[REST] LoginPlayer");
        if(requestDTO != null){
            Player result = playerRepository.loginPlayer(requestDTO.getName(), requestDTO.getPassword());
            return  Response.status(200).entity(ResponseHelper.getLoginResultDTOResponse(result)).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }
}
