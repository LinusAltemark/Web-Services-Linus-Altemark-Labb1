package spi;

public interface CurrencyConverter {
    String getSourceCurrency();
    double convertToSek(double amount);
}
