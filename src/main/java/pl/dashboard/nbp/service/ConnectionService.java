package pl.dashboard.nbp.service;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface ConnectionService {

    CloseableHttpResponse connect(String URL);
}
