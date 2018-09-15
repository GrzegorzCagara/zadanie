package pl.dashboard.nbp.dto;

public final class Rate {

    private String no;
    private String effectiveDate;
    private String bid;
    private String ask;

    public Rate() {}

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }


    public String getBid() {
        return bid;
    }


    public String getAsk() {
        return ask;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", bid='" + bid + '\'' +
                ", ask='" + ask + '\'' +
                '}';
    }
}
