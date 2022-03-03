package genelectrovise.magiksmostevile.item.pixiecourier;

public class CourierException extends Exception {
    private static final long serialVersionUID = 1L;

    public CourierException(String message) {
        super(message);
    }

    public CourierException(Throwable cause) {
        super(cause);
    }

    public CourierException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
