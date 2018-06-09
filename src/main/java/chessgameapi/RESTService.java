package chessgameapi;

import chessgameapi.dto.LoginDTO;
import chessgamedal.MySQLContext.MySQLPlayerContext;
import chessgamerepository.PlayerRepository;
import chessgamerepository.interfaces.IPlayerRepository;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/chessgame")
public class RESTService {
    private IPlayerRepository playerRepository;
    private Gson gson;

    public RESTService() {
        playerRepository = new PlayerRepository(new MySQLPlayerContext());
        gson = new Gson();
    }
    @GET
    @Path("/loginplayer")
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginPlayer(LoginDTO request){
        if(request == null){
//            return Response.status(400).entity(BaseResponseHelper.get)
        }
        return null;
    }
//    @GET
//    @Path("/player/{playerid}")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public Response getPlayer(@PathParam("playerid") String playeridAsString) {
//        System.out.println("[Server getPlayer]");
//        int playerId = Integer.parseInt(playeridAsString);
//        return null;
//    }
}
