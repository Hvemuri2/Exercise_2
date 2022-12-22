public class MeasuringUnitException extends CheckedRuntimeException {

    protected MeasuringUnitException(String message, Exception maskedRuntimeException) {
        super(message, maskedRuntimeException);
    }
}
