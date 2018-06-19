package integration;

import memorycontext.PlayerMemoryContext;
import chessgameapi.RESTService;
import chessgameapi.ResponseHelper;
import chessgameapi.dto.LoginRequestDTO;
import chessgameapi.dto.RegisterRequestDTO;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RESTIntegrationTest {
    private RESTService service;
    private PlayerMemoryContext context;
    private Gson gson;

    @Before
    public void setUp() {
        context = new PlayerMemoryContext();
        service = new RESTService(context);
        gson = new Gson();
    }

    @Test
    public void aRegisterPlayerTest() {
        String input = gson.toJson(new RegisterRequestDTO("Davy", "abcdef"));
        assertEquals(ResponseHelper.getRegisterResultDTOResponse(true), service.registerPlayer(input).getEntity());
        assertEquals(1, context.getAll().size());
    }

    @Test
    public void bRegisterSecondPlayerTest() {
        String input = gson.toJson(new RegisterRequestDTO("Davy", "abcdef"));
        assertEquals(ResponseHelper.getRegisterResultDTOResponse(false), service.registerPlayer(input).getEntity());
        assertEquals(1, context.getAll().size());
    }

    @Test
    public void cLoginPlayerTest() {
        String input = gson.toJson(new LoginRequestDTO("Davy", "abcdef"));
        assertNotEquals(ResponseHelper.getLoginResultDTOResponse(null), service.loginPlayer(input).getEntity());
    }

    @Test
    public void dLoginNonRegisteredPlayerTest() {
        String input = gson.toJson(new LoginRequestDTO("Joris", "abcdef"));
        assertEquals(ResponseHelper.getLoginResultDTOResponse(null), service.loginPlayer(input).getEntity());
    }
}
