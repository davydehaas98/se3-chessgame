package chessgameapi;

import chessgamedal.MySQLContext.MySQLPlayerContext;
import chessgamerepository.IPlayerRepository;
import chessgamerepository.PlayerRepository;
import com.google.gson.Gson;

import javax.ws.rs.*;
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
    public Response loginPlayer(LoginDTO request)
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
