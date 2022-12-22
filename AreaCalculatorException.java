public class AreaCalculatorException extends CheckedRuntimeException{
    protected AreaCalculatorException(String message, Exception maskedRuntimeException) {
        super(message, maskedRuntimeException);
    }
}
