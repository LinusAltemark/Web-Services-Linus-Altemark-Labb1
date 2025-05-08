package spi;

public class EuroToSekConverter implements CurrencyConverter {

    @Override
    public String getSourceCurrency() {
        return "EUR";
    }

    @Override
    public double convertToSek(double amount) {
        return amount * 10.93;
    }
}
