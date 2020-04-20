# **Web service / Viabill interview**

REST / JSON web service in Java using Spring Boot (RestController) with an API that
supports basic products CRUD:

● Create a new product: `POST to /product`

● Get a list of all products: `GET to /products`

● Update a product: `PUT to /product/{productId}`


The API should also support:

● Placing an order: `POST to /order`

● Retrieving all orders within a given time period: `GET to /orders with query params`

A product has a name, a BigInteger as the price and a currency for it.

Each order has a list of products. It should also has an e-mail, and the
time the order was placed. The total value of the order is always calculated, based on the prices
of the products in it.

It is possible to change the product’s price, but this shouldn’t affect the total value of orders which
have already been placed.

### Requirements met

● Document your REST-API: Documented using this readme file and Swagger. Once the service is running, the page is available on `http://{address}/swagger-ui.html`.
A Postman collection is also provided under /postman. There is a collection and an environment which points by default to localhost:8080.

● Provide a storage solution for persisting the web service’s state: MongoDB has been selected as the database. A docker compose file is provided to be run.

● Have a way to run the service with its dependencies (database etc) locally: A file docker-compose.yml can be found under the root of the folder. It will run a MongoDB instance.


#### Additional info

● You do not need to add authentication to your web service, but propose a protocol / method and
justify your choice: HTTPS can be used for encrypted communication. A basic auth might be used to get a JWT token which would be required for querying the API.

● How can you make the service redundant? What considerations should you do?: Probably an Optimistic locking strategy should be used for MongoDb. Dividing the service in two (one for products and one for orders) might be useful to allow different paces on creating/updating each service, and scale them independently.
