# food-order-service-app
Food Order Service Application

******************************************************************************************************************************************************************************

API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/load-data/restaurant        To Load Raw Restaurant JSON Data into Database

https://food-order-service-app.herokuapp.com/load-data/user              To Load Raw User JSON Data into Database

HTTP Method : GET

Request Params : Not Required

Request Body : Optional

Body Format : 	{
    			"url": "https://gist.githubusercontent.com/seahyc/b9ebbe264f8633a1bf167cc6a90d4b57/raw/021d2e0d2c56217bad524119d1c31419b2938505/restaurant_with_menu.json"
				}

Sample Response : 	{
   						 "response": true,
    					 "status": {
       					 "code": 200,
        				 "message": "Data Loaded Successfully",
        				 "status": "SUCCESS"
    					}
					}
******************************************************************************************************************************************************************************


API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/fetch/open-restaurants       To Fetch Restaurants that are opened in the specified duration

HTTP Method : POST

Request Params : Not Required

Request Body : Required

Body Format : 	{
    				"inputDate" : "2020-08-01 11:00:00"
				}

Sample Response : 	{
    "response": [
        "'Ulu Ocean Grill and Sushi Lounge",
        "100% de Agave",
        "51Fifteen Restaurant & Lounge",
        "Ai Sushi Sake Grill",
        "Allegria",
        "Aquarium Restaurant - Downtown Denver",
        "Argyll Whisky Beer",
        "BLD",
        "Badmaash",
        "Bean Around the World Coffees",
        "Beano's Cabin",
        "Benihana - Sugar Land",
        "Binkley's Kitchen & Bar",
        "Bistro 310"
    ],
    "status": {
        "code": 200,
        "message": null,
        "status": "SUCCESS"
    }
}
******************************************************************************************************************************************************************************

API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/fetch/restaurants-by-hours       To Fetch Restaurants that are MORE or LESS than specified hours

HTTP Method : POST

Request Params : Not Required

Request Body : Required

Body Format : 	{
				   "relation": "MORE",
    			   "duration" : "DAY",
                   "hours" : 18
                }

Possible Values:                relation : MORE or LESS
                				duration : DAY or WEEK

Sample Response : 	

{
    "response": [
        "Mon Ami Gabi - Las Vegas - Main Dining Room",
        "Rio Grande - Denver",
        "Delancey",
        "Ocean House Restaurant",
        "Yo Sake Downtown Sushi Lounge",
        "1808 American Bistro",
        "Stay C's Kitchen",
        "Jimmy Buffett's Waikiki",
        "Cafe Verona",
        "Swiss Chalet",
        "Rosso @ Hotel Sorella Country Club Plaza",
        "Campagnia",
        "The Mark Dine & Tap",
        "The Capital Grille - Dallas - Uptown"
    ],
    "status": {
        "code": 200,
        "message": null,
        "status": "SUCCESS"
    }
}
*****************************************************************************************************************************************************************************


API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/fetch/restaurants-by-dish-price      To Fetch Restaurants that are having MORE or LESS than No of Of Dishes in the Specified 
                                                                                  Price Range

HTTP Method : POST

Request Params : Not Required

Request Body : Required

Body Format : 	{
				   "relation": "MORE",
    			   "fromPrice" : "10.00",
                   "toPrice" : "12.00",
                   "noOfDishes" : 5
                }

Possible Values:                relation : MORE or LESS

Sample Response : 	

{
    "response": [
        "Raintree Restaurant",
        "Kinship",
        "Kaunaoa Bar & Grill",
        "Kyoto Sushi",
        "Brasserie Max & Julie",
        "A-1 Cafe Restaurant",
        "L'ATELIER - Boulder",
        "Alan Wong's Restaurant",
        "El Barzon",
        "Bistro Al Vino",
        "Kauai Ono",
        "Cafe Genevieve",
        "Cheeky Monk Belgian Beer Cafe",
        "Restauration",
        "D'Agnese's at White Pond",
        "Mo's a Place for Steaks - Houston",
        "Billy Bob's Steak House & Saloon",
        "Prime Steakhouse",
        "House. Wine. & Bistro",
        "Three's Bar & Grill",
        "Due Amici",
        "Kenny's Smoke House",
        "Kiepersol Enterprises",
        "Game Creek Restaurant",
        "Jay's Bistro",
        "Tamayo Restaurant"
    ],
    "status": {
        "code": 200,
        "message": null,
        "status": "SUCCESS"
    }
}
}


*****************************************************************************************************************************************************************************


API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/fetch/top-users      To Fetch Top N Users By Transaction Amount in specified Date range

HTTP Method : POST

Request Params : Not Required

Request Body : Required

Body Format : 	

{
    "fromDate": "2020-07-01 00:00:00",
    "toDate": "2020-10-01 00:00:00",
    "topUsers": 5
}


Sample Response : 	
{
    "response": [
        {
            "total": 400.0,
            "userId": 674,
            "userName": "Keith Rossi"
        },
        {
            "total": 150.0,
            "userId": 900,
            "userName": "Kim Harbison"
        },
        {
            "total": 87.28,
            "userId": 593,
            "userName": "Paulette Jackson"
        },
        {
            "total": 63.75,
            "userId": 39,
            "userName": "Jesse Council"
        },
        {
            "total": 60.0,
            "userId": 383,
            "userName": "Linda Bray"
        }
    ],
    "status": {
        "code": 200,
        "message": null,
        "status": "SUCCESS"
    }
}


*****************************************************************************************************************************************************************************


API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/fetch/by-transactions     To Fetch Popular Restaurants by Transaction Count or Amount

HTTP Method : POST

Request Params : Not Required

Request Body : Required

Body Format : 	

{
    "fetchType" : "TRANSACTION_COUNT"
}

Possible Values:                

fetchType : TRANSACTION_COUNT or TRANSACTION_AMOUNT

Sample Response : 	
{
    "response": [
        {
            "totalCount": 15,
            "restaurantName": "Gaetano's"
        },
        {
            "totalCount": 13,
            "restaurantName": "Cafe 4"
        },
        {
            "totalCount": 13,
            "restaurantName": "PRISM"
        },
        {
            "totalCount": 12,
            "restaurantName": "Masraff's"
        },
        {
            "totalCount": 11,
            "restaurantName": "Marker 32"
        },
        {
            "totalCount": 11,
            "restaurantName": "Driftwood"
        },
        {
            "totalCount": 11,
            "restaurantName": "Tabernash Tavern"
        },
        {
            "totalCount": 11,
            "restaurantName": "Ouisie's Table"
        },
        {
            "totalCount": 11,
            "restaurantName": "Mezzodi's"
        },
        {
            "totalCount": 11,
            "restaurantName": "Provisions"
        }

    ],
    "status": {
        "code": 200,
        "message": null,
        "status": "SUCCESS"
    }
}


*****************************************************************************************************************************************************************************


API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/fetch/total-users    To Fetch Total Users who has more than specified amount of transactions in specified date range

HTTP Method : POST

Request Params : Not Required

Request Body : Required

Body Format : 	

{
    "relation" : "MORE",
    "fromDate": "2020-07-01 00:00:00",
    "toDate": "2020-10-01 00:00:00",
    "amount" : "50"
}

Possible Values:                

relation : MORE or LESS

Sample Response : 	
{
    "response": {
        "totalUsers": 6
    },
    "status": {
        "code": 200,
        "message": null,
        "status": "SUCCESS"
    }
}

*****************************************************************************************************************************************************************************


API End Point : 																Purpose

https://food-order-service-app.herokuapp.com/place-order     Places and Order and Performs the Atomic Transactions accordingly

HTTP Method : POST

Request Params : Not Required

Request Body : Required

Body Format : 	

{
    "userId": 0,
    "restaurantName": "Steak House No. 316",
    "menuDetails": [
        {
            "dishName": "LIME BEANS",
            "quantity": "3"
        },
        {
            "dishName": "Egg Pie",
            "quantity": "2"
        }
    ]
}



Sample Response : 	
{
    "response": true,
    "status": {
        "code": 200,
        "message": "Placed Order Successfully",
        "status": "SUCCESS"
    }
}
