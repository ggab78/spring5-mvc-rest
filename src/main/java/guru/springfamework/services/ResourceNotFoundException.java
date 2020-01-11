package guru.springfamework.services;

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(){}

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable t){
        super(message, t);
    }

    public ResourceNotFoundException(Throwable t){
        super(t);

    }
    public ResourceNotFoundException(String message, Throwable t, boolean enableSuppression, boolean writableStackTrace){
        super(message,t,enableSuppression,writableStackTrace);
    }

}
