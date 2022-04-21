package PAP.Restaurantpol.exceptions;

public class IncorrectTestException extends Exception {
    public IncorrectTestException(String test_name) {
        super("The test should not pass. The test name: " + test_name);
    }
}
