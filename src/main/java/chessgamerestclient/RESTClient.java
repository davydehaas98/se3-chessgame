package chessgamerestclient;

import chessgamerestshared.BaseRequestDTO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RESTClient {
    private final String url = "http://localhost:8009/chessgame";
    private final Gson gson = new Gson();
    private final int NOTDEFINED = -1;

    public RESTClient(){

    }

    private <T> T executeRequest(HttpUriRequest request, Class<T> clazz) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request);) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            T responseObject = gson.fromJson(entityString, clazz);
            return responseObject;
        } catch (IOException | JsonSyntaxException e) {
            return null;
        }
    }

    private <T> T executeQueryGet(String queryGet, Class<T> clazz) {
        // Build the query for the REST service
        final String query = url + queryGet;
        System.out.println("[Query Get] : " + query);
        // Execute the HTTP GET request
        HttpGet httpGet = new HttpGet(query);
        return executeRequest(httpGet, clazz);
    }

    private <T> T executeQueryPost(BaseRequestDTO request, String queryPost, Class<T> clazz) {
        final String query = url + queryPost;
        // Execute the HTTP POST request
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(request));
            httpPost.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(PetStoreClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeRequest(httpPost, clazz);
    }

    private <T> T executeQueryPut(BaseRequestDTO request, String queryPut, Class<T> clazz) {
        final String query = url + queryPut;
        // Execute the HTTP PUT request
        HttpPut httpPut = new HttpPut(query);
        httpPut.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(request));
            httpPut.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(PetStoreClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeRequest(httpPut, clazz);
    }

    private <T> T executeQueryDelete(String queryDelete, Class<T> clazz) {
        final String query = url + queryDelete;
        // Execute the HTTP DELETE request
        HttpDelete httpDelete = new HttpDelete(query);
        return executeRequest(httpDelete, clazz);
    }
}
