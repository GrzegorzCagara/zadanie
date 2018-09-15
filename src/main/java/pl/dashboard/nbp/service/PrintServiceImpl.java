package pl.dashboard.nbp.service;

import pl.dashboard.nbp.dto.Currency;
import pl.dashboard.nbp.dto.Rate;

import java.io.IOException;

public class PrintServiceImpl implements PrintService {


    private static final String EUR = "http://api.nbp.pl/api/exchangerates/rates/c/eur/";
    private static final String CHF = "http://api.nbp.pl/api/exchangerates/rates/c/chf/";
    private static final String USD = "http://api.nbp.pl/api/exchangerates/rates/c/usd/";
    private static final String GBP = "http://api.nbp.pl/api/exchangerates/rates/c/gbp/";
    private static final String JSON_FORMAT = "/?format=json";

    private final ConnectionService connectionService;
    private final EntityToJsonService entityToJsonService;

    public PrintServiceImpl(ConnectionService connectionService, EntityToJsonService entityToJsonService) {
        this.connectionService = connectionService;
        this.entityToJsonService = entityToJsonService;
    }

    public void printMessage(String data) {
        final String EUR_URL = EUR + data + JSON_FORMAT;
        final String CHF_URL = CHF + data + JSON_FORMAT;
        final String USD_URL = USD + data + JSON_FORMAT;
        final String GBP_URL = GBP + data + JSON_FORMAT;


        Currency euro = null;
        Currency chf = null;
        Currency usd = null;
        Currency gbp = null;
        try {
            euro = entityToJsonService.convert(EUR_URL);
            chf = entityToJsonService.convert(CHF_URL);
            usd = entityToJsonService.convert(USD_URL);
            gbp = entityToJsonService.convert(GBP_URL);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Wystąpił błąd podczas konwersji");
            return;
        }

        Rate euroRate = euro.getRates().get(0);
        Rate chfRate = chf.getRates().get(0);
        Rate usdRate = usd.getRates().get(0);
        Rate gbpRate = gbp.getRates().get(0);

        String message = String.format("Data: %s\n" +
                        "Waluta = kupno; sprzedaż\n" +
                        "EUR = %s; %s\n" +
                        "CHF = %s; %s\n" +
                        "USD = %s; %s\n" +
                        "GBP = %s; %s\n", data,
                euroRate.getBid(), euroRate.getAsk(),
                chfRate.getBid(), chfRate.getAsk(),
                usdRate.getBid(), usdRate.getAsk(),
                gbpRate.getBid(), gbpRate.getAsk());

        System.out.println(message);
    }
}
