package PAP.Restaurantpol.model;

import PAP.Restaurantpol.exceptions.IntRatingException;

import java.util.UUID;

public class Review {
	private String review_id;
	private String username;
	private int rating;
	private String descript;

	public Review() {
		this.review_id = UUID.randomUUID().toString();
	}

	public Review(String review_id, String username, int rating, String descript) throws IntRatingException {
		this.review_id = review_id;
		this.username = username;
		this.descript = descript;

		if (rating < 0 || rating > 5) {
			throw new IntRatingException(rating);
		} else {
			this.rating = rating;
		}
	}

	public String getId() {
		return this.review_id;
	}

	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}

	public String getusername() {
		return this.username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) throws IntRatingException {
		if (rating < 0 || rating > 5) {
			throw new IntRatingException(rating);
		} else {
			this.rating = rating;
		}
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "ID: " + this.review_id + " username: " + this.username + "Rating: " + this.rating + "descript: "
				+ this.descript;
	}
}
