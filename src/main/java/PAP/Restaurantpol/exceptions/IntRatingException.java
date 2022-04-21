package PAP.Restaurantpol.exceptions;

public class IntRatingException extends Exception {
    public IntRatingException(int rating) {
        super("Rating is a values between 0 and 5. There is " + rating);
    }
}
