post method
------------
public getCuisineList(): Observable<string[]> {
		return this.http.get<string[]>('/api/cuisines');
	}

-------------

two ways to go about getting the response

component.ts

1. cuisines!: string[];  <- declare as a string[]

component.ts
this.rs.getCuisineList().subscribe({
 next: (response) => {console.log(response),
 this.cuisines = response; <- assign the string[] here
},
error: (error) => {console.log(error)},
complete: () => {console.log('Done')}
});

HTML
<p *ngFor="let cuisine of cuisines"> 
    {{cuisine}}
</p> 


2. cuisines$!: Observable<string[]>; <- declare as a Observable

component.ts
this.cuisines$ = this.rs.getCuisineList();

HTML
<div *ngIf="cuisines$ | async as cuisines">
    <p *ngFor="let cuisine of cuisines">
    {{cuisine}}
    </p>
</div>




