package pl.dashboard.nbp.service;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.dashboard.nbp.dto.Currency;
import pl.dashboard.nbp.exceptions.InternalServerException;
import pl.dashboard.nbp.exceptions.NotFoundResourceException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EntityToJsonServiceImpl implements EntityToJsonService {

    private final ConnectionService connectionService;

    public EntityToJsonServiceImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }


    public Currency convert(String URL) throws IOException {
        Currency currency = null;
        CloseableHttpResponse response = null;
        try {
            response = connectionService.connect(URL);
            checkForErrors(response);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inStream = entity.getContent();
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(
                        new InputStreamReader(inStream, "UTF-8"));
                currency = mapJSONObjectToCurrencyObject(jsonObject);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return currency;
    }

    private void checkForErrors(CloseableHttpResponse response) {
        if (response == null) {
            throw new InternalServerException("Błąd podczas połączenia z serwerem");
        }
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 404) {
            throw new NotFoundResourceException("404 Not Found - Brak danych");
        }
    }

    private Currency mapJSONObjectToCurrencyObject(JSONObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), Currency.class);
    }


}
