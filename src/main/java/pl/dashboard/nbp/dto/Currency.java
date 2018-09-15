package pl.dashboard.nbp.dto;

import java.util.List;

public final class Currency {

    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

    public Currency() {}

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<Rate> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                '}';
    }
}
