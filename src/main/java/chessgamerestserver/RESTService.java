package chessgamerestserver;

import chessgamedal.IDataContext;
import chessgamedal.IPlayerRepository;
import jdk.nashorn.internal.objects.annotations.Getter;
import model.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.ws.Response;

@Path("/chessgame")
public class RESTService {
    private IPlayerRepository playerRepository;
    private IDataContext dataContext;
    public RESTService() {

    }

    @GET
    @Path("/player/{playerid}")
    @Produces
    public Response getPlayer(@PathParam("playerid") String playeridAsString) {
        System.out.println("[Server getPlayer]");
        int playerId = Integer.parseInt(playeridAsString);
        return null;
    }
}
