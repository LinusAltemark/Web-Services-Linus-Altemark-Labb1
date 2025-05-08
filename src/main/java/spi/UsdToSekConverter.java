package spi;

public class UsdToSekConverter implements CurrencyConverter {

    @Override
    public String getSourceCurrency() {
        return "USD";
    }

    @Override
    public double convertToSek(double amount) {
        return amount * 9.68;
    }
}
