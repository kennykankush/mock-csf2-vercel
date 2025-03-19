import { Component, inject, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-restaurant-cuisine',
    templateUrl: './restaurant-cuisine.component.html',
    styleUrls: ['./restaurant-cuisine.component.css'],
    standalone: false
})
export class RestaurantCuisineComponent implements OnInit{
	
	// TODO Task 3
	// For View 2
    cuisine: string = 'not set';
    restaurants!: Restaurant[];
    restaurantNames!: string[];

    private rs = inject(RestaurantService);
    private activatedRoute = inject(ActivatedRoute);
    private router = inject(Router);

    ngOnInit(): void {
        this.cuisine = this.activatedRoute.snapshot.params['cuisine']
        // this.restaurants$ = this.rs.getRestaurantsByCuisine(this.cuisine);
        this.rs.getRestaurantsByCuisine(this.cuisine).subscribe({
            next: (response) => {
                this.restaurantNames = response
                console.log(response);
            },
            error: (error) => {
                console.warn(error)
            },
            complete: () => {
                console.log("Retrieved Restaurant Names")
            }
        })
    //     this.rs.getRestaurantsByCuisine(this.cuisine).subscribe({
    //         next: (response) => {
    //             this.restaurants = response.map((r:any) => ({
    //                 restaurantId: r.restaurantId,
    //                 coordinates: [r.coordinates.latitude, r.coordinates.longitude],
    //                 namd: r.name,
    //                 cusisine: r.cuisine,
    //                 address: r.address
    //                 })
    //             );
    //             console.log(this.restaurants);
    //         },
    //         error: (error) => {
    //             console.log(error);
    //         },
    //         complete: () => {
    //             console.log("Retrived");
    //         }
    //     })

    }

    clickStuff(){
        this.router.navigate([`/restaurant/${this.restaurantNames}`]);
    }

}
