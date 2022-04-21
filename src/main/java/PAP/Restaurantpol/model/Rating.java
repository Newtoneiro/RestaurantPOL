package PAP.Restaurantpol.model;

import java.util.UUID;

public class Rating {

        private UUID ratingId;// = UUID.randomUUID();
        private String restaurantId;
        private String userId;
        private String descript;
        private String username;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

        private int rating;

        public Rating() {
                this.ratingId = UUID.randomUUID();
        }

        public String getDescript(){
                return descript;
        }

        public UUID getRatingId() {
                return ratingId;
        }

        public String getRestaurantId() {
                return restaurantId;
        }

        public String getUserId() {
                return userId;
        }

        public int getRating() {
                return rating;
        }

        public void setDescript(String descript){
                this.descript = descript;
        }

        public void setRatingId(UUID ratingId) {
                this.ratingId = ratingId;
        }

        public void setRestaurantId(String restaurantId) {
                this.restaurantId = restaurantId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public void setRating(int rating) {
                this.rating = rating;
        }

        public String toString(){
                return "Id: " + ratingId.toString() + "UserId: " + userId + "restaurantId: " + restaurantId + "Descript: " + descript + "Rating: " + rating + "username: " + username;
        }
}
