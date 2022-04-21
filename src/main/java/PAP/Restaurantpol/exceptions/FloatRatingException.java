package PAP.Restaurantpol.exceptions;

public class FloatRatingException extends Exception {
    public FloatRatingException(float rating) {
        super("Rating is a values between 0.0 and 5.0, there is " + rating);
    }
}
