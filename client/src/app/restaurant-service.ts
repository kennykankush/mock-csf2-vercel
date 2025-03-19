import { Injectable } from '@angular/core'
import { Restaurant, Comment } from './models'
import { Observable } from 'rxjs'
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/env/env'


@Injectable({
	providedIn: 'root'
  })
export class RestaurantService {

	constructor(private http: HttpClient){}
	private apiUrl = environment.apiUrl;



	// TODO Task 2 
	// Use the following method to get a list of cuisines
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getCuisineList(): Observable<string[]> {
		// Implememntation in here
		return this.http.get<string[]>(`${this.apiUrl}/api/cuisines`);
	}

	// TODO Task 3 
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getRestaurantsByCuisine(cuisine: string): Observable<string[]> {
		// Implememntation in here
		return this.http.get<string[]>(`${this.apiUrl}/api/${cuisine}/restaurant`);
	}
	
	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public async getRestaurant(restaurantName : string): Promise<Restaurant> { //https://stackoverflow.com/questions/75642287/how-to-change-an-observable-http-request-to-promise-request
		// Implememntation in here
		const response = await fetch(`${this.apiUrl}/api/restaurant/${restaurantName}`);

		const data = await response.json();
		console.log(data);
		console.log(data.name);

		const restaurant: Restaurant = {
			restaurantId: data.restaurantId,
			namd: data.name,
			cusisine: data.cuisine,
			address: data.address,
			coordinates: data.coordinates,
			mapUrl: data.mapURL
			
		};

		return restaurant;
	}

	// TODO Task 5
	// Use this method to submit a comment
	// DO NOT CHANGE THE METHOD'S NAME OR SIGNATURE
	public postComment(comment: Comment): Promise<any> {
		// Implememntation in here
		return fetch(`${this.apiUrl}/api/comment`, {
			method: 'POST',
			headers: { 'Content-Type': 'application/json'},
			body: JSON.stringify(comment)
		}).then(res => {
			return res.json();
		}).then(data => {
			alert(data.message);
		})
	
	}
}
