import { Component, inject, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant-service';
import { Observable } from 'rxjs';

@Component({
    selector: 'app-cuisine-list',
    templateUrl: './cuisine-list.component.html',
    styleUrls: ['./cuisine-list.component.css'],
    standalone: false
})
export class CuisineListComponent implements OnInit {
    // cuisines!: string[];
    cuisines$!: Observable<string[]>;
    private rs = inject(RestaurantService);

	// TODO Task 2
	// For View 1
    ngOnInit(): void {

        // this.rs.getCuisineList().subscribe({
        //     next: (response) => {console.log(response),
        //         this.cuisines = response;
        //     },
        //     error: (error) => {console.log(error)},
        //     complete: () => {console.log('Done')}
        // });

        //OR

        this.cuisines$ = this.rs.getCuisineList();

    }

}
