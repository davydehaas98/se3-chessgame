package chessgameapi;

import chessgameapi.dto.RegisterDTO;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public final class RESTClient {
    private final String url = "http://localhost:8009/chessgame";
    private final Gson gson = new Gson();

    public boolean registerPlayer(String name, String password){
//        RegisterDTO registerDTO = new RegisterDTO(name,password);
//        String input = gson.toJson(registerDTO);
//        final String query = "/registerplayer";
//        StringEntity inputEntity = new StringEntity(input, ContentType.APPLICATION_JSON);
//        HttpEntity resultHttpEntity = executeQueryPost(inputEntity, query);
//        String resultstring = null;
//        try {
//            resultstring = EntityUtils.toString(resultHttpEntity);
//        }catch (Exception exc){
//
//        }if(resultstring != null){
//            return gson.fromJson(resultstring, RegisterResultDTO.class).isSuccess();
//        }
        return false;
    }

    private HttpEntity executeRequest(HttpUriRequest request) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return response.getEntity();
        } catch (Exception exc) {
            return null;
        }
    }

    private HttpEntity executeQueryGet(String queryGet) {
        // Build the query for the REST service
        final String query = url + queryGet;
        // Execute the HTTP GET request
        HttpGet httpGet = new HttpGet(query);
        return executeRequest(httpGet);
    }

    private HttpEntity executeQueryPost(StringEntity parameters, String queryPost) {
        final String query = url + queryPost;
        // Execute the HTTP POST request
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        httpPost.setEntity(parameters);
        return executeRequest(httpPost);
    }

    private HttpEntity executeQueryPut(StringEntity parameters, String queryPut) {
        final String query = url + queryPut;
        // Execute the HTTP PUT request
        HttpPut httpPut = new HttpPut(query);
        httpPut.addHeader("content-type", "application/json");
        httpPut.setEntity(parameters);
        return executeRequest(httpPut);
    }

    private HttpEntity executeQueryDelete(String queryDelete) {
        final String query = url + queryDelete;
        // Execute the HTTP DELETE request
        HttpDelete httpDelete = new HttpDelete(query);
        return executeRequest(httpDelete);
    }
}
