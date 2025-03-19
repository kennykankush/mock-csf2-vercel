// Do not change these interfaces
export interface Restaurant {
	restaurantId: string
	namd: string
	cusisine: string
	address: string
	coordinates: number[]
	mapUrl: string
}

export interface Comment {
	name: string
	rating: number
	restaurantId: string
	text: string
}
