package vttp2022.csf.assessment.server.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.LatLng;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.SpacesService;


@Repository
public class RestaurantRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SpacesService spacesService;
	
	private String collectionName = "restaurant_trim";

	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method 
	// * db.restaurant.distinct("cuisine")  https://www.mongodb.com/docs/manual/reference/method/db.collection.distinct/

	// * https://stackoverflow.com/questions/31635677/mongodb-mongotemplate-get-distinct-field-with-some-criteria
	// * https://canvas.nus.edu.sg/courses/68942/files/folder/slides/03_PAF?preview=5672337 MongoQuerying Slides
	public List<String> getCuisines() {
		// Implmementation in here
	   return mongoTemplate.findDistinct(new Query(), "cuisine", collectionName, String.class);

	}

	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	//  db.restaurant_trim.find({
	//     cuisine: "{cuisine}"
	// },
	// {
	// 	   name: 1
	// })

	public List<String> getRestaurantsByCuisine(String cuisine) {
		String toUpperCase = cuisine.substring(0,1).toUpperCase() + cuisine.substring(1);
		System.out.println(cuisine + " , " + toUpperCase); //https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
		// Implmementation in here
		Criteria criteria = Criteria.where("cuisine").is(toUpperCase);
		Query query = Query.query(criteria);
		query.fields().include("name");
		query.fields().exclude("_id");
		List<Document> documents = mongoTemplate.find(query, Document.class, collectionName);

		List<String> restaurantNames = new ArrayList<>();

		for (Document document: documents){
			String restaurantName = document.get("name").toString();
			restaurantNames.add(restaurantName);

		}
		return restaurantNames;


	}

	// public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
	// 	String toUpperCase = cuisine.substring(0,1).toUpperCase() + cuisine.substring(1);
	// 	System.out.println(cuisine + " , " + toUpperCase); //https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
	// 	// Implmementation in here
	// 	Criteria criteria = Criteria.where("cuisine").is(toUpperCase);
	// 	Query query = Query.query(criteria);
	// 	List<Document> documents = mongoTemplate.find(query, Document.class, collectionName);

	// 	List<Restaurant> restaurants = new ArrayList<>();

	// 	for (Document document: documents){ //https://stackoverflow.com/questions/63307545/mapping-a-bson-mongodb-document-to-a-myclass-class-object
	// 		Restaurant restaurant = new Restaurant();
	// 		restaurant.setRestaurantId((String) document.get("restaurant_id"));

	// 		restaurant.setCuisine((String) document.get("cuisine"));
	// 		restaurant.setName((String) document.get("name"));

	// 		Document addressLevel = (Document) document.get("address");

	// 		String building = (String) addressLevel.get("building");
	// 		String street = (String) addressLevel.get("street");
	// 		String zipcode = (String) addressLevel.get("zipcode");
	// 		String borough = (String) document.get("borough");

	// 		StringBuilder addressBuilder = new StringBuilder();
	// 		addressBuilder.append(building).append(", ").append(street).append(", ").append(zipcode).append(", ").append(borough);

	// 		restaurant.setAddress(addressBuilder.toString());

	// 		 List<Double> latLong = (List<Double>) addressLevel.get("coord");
			
	// 		LatLng latLng = new LatLng();
	// 		latLng.setLatitude(latLong.get(0).floatValue());
	// 		latLng.setLongitude(latLong.get(1).floatValue());

	// 		restaurant.setCoordinates(latLng);
	// 		restaurant.setMapURL("placeholder.com");

	// 		restaurants.add(restaurant);

	// 	}

	// 	return restaurants;


	// }

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	// db.restaurant_trim.findOne({
	// 	name: "Wendy'S"
	// })
	 
	public Optional<Restaurant> getRestaurant(String restaurantName) {

		Criteria criteria = Criteria.where("name").is(restaurantName);
		Query query = Query.query(criteria);
		
		// JsonNode getRestaurant = mongoTemplate.findOne(query, JsonNode.class, collectionName);

		// Restaurant restaurant = new Restaurant();
		// restaurant.setRestaurantId(getRestaurant.get("address_id").asText());
		// restaurant.setName(getRestaurant.get("name").asText());
		// restaurant.setCuisine(getRestaurant.get("cuisine").asText());

		// JsonNode addressLevel = getRestaurant.get("address");

		// String building = addressLevel.get("building").asText();
		// String street = addressLevel.get("street").asText();
		// String zipcode = addressLevel.get("zipcode").asText();
		// String borough = getRestaurant.get("borough").asText();

		// StringBuilder addressBuilder = new StringBuilder();
		// addressBuilder.append(building).append(", ").append(street).append(", ").append(zipcode).append(", ").append(borough);
		// restaurant.setAddress(addressBuilder.toString());

		// JsonNode coordinateLevel = addressLevel.get("coord");
		// LatLng latlng = new LatLng();
		// float lat = (float) coordinateLevel.get(0).asDouble();
		// float lng = (float) coordinateLevel.get(1).asDouble();
		// latlng.setLatitude(lat);
		// latlng.setLongitude(lng);

		// restaurant.setCoordinates(latlng);

		Document document = mongoTemplate.findOne(query, Document.class, collectionName);

		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(document.getString("restaurant_id"));

		restaurant.setCuisine(document.getString("cuisine"));
		restaurant.setName(document.getString("name"));

		Document addressLevel = (Document) document.get("address");

		String building = addressLevel.getString("building");
		String street = addressLevel.getString("street");
		String zipcode =addressLevel.getString("zipcode");
		String borough = document.getString("borough");

		StringBuilder addressBuilder = new StringBuilder();
		addressBuilder.append(building).append(", ").append(street).append(", ").append(zipcode).append(", ").append(borough);

		restaurant.setAddress(addressBuilder.toString());

		List<Double> latLong = (List<Double>) addressLevel.get("coord");
			
		LatLng latLng = new LatLng();
		latLng.setLatitude(latLong.get(0).floatValue());
		latLng.setLongitude(latLong.get(1).floatValue());

		restaurant.setCoordinates(latLng);
		restaurant.setMapURL(spacesService.getDefaultImage());

		return Optional.ofNullable(restaurant); //https://www.baeldung.com/java-optional

	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	public void addComment(Comment comment) {
		// Implmementation in here
		mongoTemplate.insert(comment, "comments");
	}
	
	// You may add other methods to this class

}
