package pl.com.softproject.currency.service.exception;

/**
 * Excetion IncorrectCurrencyCodeException
 * Thrown usually, when currency code is invalid
 */
public class IncorrectCurrencyCodeException extends Exception {

    public IncorrectCurrencyCodeException(String message){
        super(message);
    }
}
