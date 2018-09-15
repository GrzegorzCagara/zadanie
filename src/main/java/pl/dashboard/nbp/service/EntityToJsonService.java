package pl.dashboard.nbp.service;

import org.apache.http.HttpEntity;
import pl.dashboard.nbp.dto.Currency;

import java.io.IOException;

public interface EntityToJsonService {

    Currency convert(String data) throws IOException;
}
