package chessgameapi;

import chessgameapi.dto.BaseRequestDTO;
import chessgameshared.logging.Logger;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public abstract class RESTClientBase {
    private final Gson gson = new Gson();

    abstract String getBaseURL();

    private <T> T executeRequest(HttpUriRequest request, Class<T> clazz)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            return gson.fromJson(entityString, clazz);
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
            return null;
        }
    }

    public  <T> T executeQueryGet(String queryGet, Class<T> clazz) {
        // Build the query for the REST service
        final String query = getBaseURL() + queryGet;
        // Perform the query
        HttpGet httpGet = new HttpGet(query);
        return executeRequest(httpGet, clazz);
    }

    public <T> T executeQueryPost(BaseRequestDTO request, String queryPost , Class<T> clazz) {

        // Build the query for the REST service
        final String query = getBaseURL() + queryPost;

        // Perform the query
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");

        StringEntity params;
        try {
            String json = gson.toJson(request);
            params = new StringEntity(json);
            httpPost.setEntity(params);
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
        }

        return executeRequest(httpPost, clazz);
    }

    public <T> T  executeQueryPut(BaseRequestDTO request, String queryPut , Class<T> clazz) {
        // Build the query for the REST service
        final String query = getBaseURL() + queryPut;
        // Perform the query
        HttpPut httpPut = new HttpPut(query);
        httpPut.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(request));
            httpPut.setEntity(params);
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
        }
        return executeRequest(httpPut, clazz);
    }

    public <T> T executeQueryDelete(String queryDelete, Class<T> clazz) {
        // Build the query for the REST service
        final String query = getBaseURL() + queryDelete;
        // Perform the query
        HttpDelete httpDelete = new HttpDelete(query);
        return executeRequest(httpDelete, clazz);
    }
}
