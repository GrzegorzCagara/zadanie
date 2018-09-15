package pl.dashboard.nbp.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ConnectionServiceImpl implements ConnectionService {


    public CloseableHttpResponse connect(String URL) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(URL);
        try {
            return httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
