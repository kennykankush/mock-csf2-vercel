import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CuisineListComponent } from './components/cuisine-list.component';
import { RestaurantCuisineComponent } from './components/restaurant-cuisine.component';
import { RestaurantDetailsComponent } from './components/restaurant-details.component';
import { provideHttpClient } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

const appRoutes: Routes = [{
  path: '',
  component: CuisineListComponent
  },  
  { 
    path: ':cuisine/restaurant',
    component: RestaurantCuisineComponent
  },
  { 
    path: 'restaurant/:restaurantName',
    component: RestaurantDetailsComponent
    
  }]

@NgModule({
  declarations: [
    AppComponent,
    CuisineListComponent,
    RestaurantCuisineComponent,
    RestaurantDetailsComponent
  ],
  imports: [BrowserModule,
    RouterModule.forRoot(appRoutes, {useHash : false}),
    ReactiveFormsModule

  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
