import { Component, inject, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant-service';
import { Comment, Restaurant } from '../models';
import { firstValueFrom } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-restaurant-details',
    templateUrl: './restaurant-details.component.html',
    styleUrls: ['./restaurant-details.component.css'],
    standalone: false
})
export class RestaurantDetailsComponent implements OnInit{

    // TODO Task 4 and Task 5
	// For View 3

    private rs = inject(RestaurantService);
    private activatedRoute = inject(ActivatedRoute);
    private fb = inject(FormBuilder);
    restaurant!: Restaurant;
    param!: string;
    commentForm!: FormGroup;

    ngOnInit(): void {
        this.param = this.activatedRoute.snapshot.params['restaurantName']
        this.getRestaurant(this.param);
        this.createForm();

    }

    createForm(): FormGroup{
        return this.commentForm = this.fb.group({
            name: ['',[Validators.required, Validators.minLength(3)]],
            rating: [1,[Validators.required, Validators.min(1), Validators.max(5)]],
            text:['',[Validators.required]]
        })
    }

    async getRestaurant(name: string){
        this.restaurant = await this.rs.getRestaurant(this.param);
    }

    isValid(): boolean {
        return !this.commentForm.valid;
    }

    onSubmit(): void {

        const comment: Comment = {
            name: this.commentForm.value.name,
            rating: this.commentForm.value.rating,
            text: this.commentForm.value.text,
            restaurantId: this.restaurant.restaurantId

        }

        this.rs.postComment(comment);

    }
    
	


}
