package pl.com.softproject.currency.integration.sources.exceptions;

/**
 * Class LoadRatesException
 *
 * Exception thrown when currency rate importing task has fail

 * @author Leszek Bednorz
 */
public class LoadRatesException extends Exception {

    public LoadRatesException(String message){
        super(message);
    }

}
