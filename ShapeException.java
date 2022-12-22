public class ShapeException extends CheckedRuntimeException {

    protected ShapeException(String message, Exception maskedRuntimeException) {
        super(message, maskedRuntimeException);
    }
}
