# Pizza Shop - Assessment
A pizza joint in Hong Kong would like to try a new way for customers to order pizza online. You're tasked with creating a working prototype of the backend.
It should have the following features:
- A service should be created to generate a pizza order message with name, quantity and price (the values used do not matter) and send that message to the second service.
- A service should be created to receive that order message and store it in a database.

## This project utilise technologies
    - kubernetes
    - istio (service-mesh)
    - java 1.8
    - docker
    - maven
    - microservice
    - RESTful apis
    - postman


It comprise of 2 seperate folder for seperate backend microservices. Both services can communicate on  REST http protocol.
    - order service
    - pizza service
    - istio service definetions

Order Service utilize Pizza service to get the pizza detail and can communicate on each other.

Get number of orders
- http://localhost/v1/order
    will return how many orders are in queue [ 0 ]

Post Order to add an order 
- http://localhost/v1/order
```
{
    "id" : 1212121214,
    "recipient": "test",
    "totalPrice": 50.0,
    "orderItems": [
        {
            "name": "Pizza2", 
            "price" : 20.0,
            "pizzaId": 2,
            "quantity": 1
        }
    ]
}
```

Pizza Service is standalone microserivce which is for pizza type, name, size and price for Order Service

Testing Pizza Service to add new pizza

Get pizza
- http://localhost/v1/pizza
    will return how many pizzas available [ 0 ]

Post Pizza 
- http://localhost/v1/pizza
```
{
    "id": 90929292,
    "name" : "Pizza1",
    "size": "Large",
    "price": 30.0
}
```

# Installation

I used desktop docker and kubernetes for this project.

For Istio I followed install/uninstall
	
	https://istio.io/latest/docs/setup/install/istioctl/


### To install Istio

	curl -L https://istio.io/downloadIstio | sh - 
	cd istio-1.11.3


	Add the istioctl client to your pat
		export PATH=$PWD/bin:$PATH



# Build

You can use below commands to compile, build the docker images.
The java build proceess will happen on the docker images.

For PizzaService project, run on root of project.
   ``` docker build -t assessment/pizzaservice . ```

For OrderService project, run on root of project.
   ``` docker build -t assessment/orderservice .```

## Test docker images
After build, you can test the images by running below commands for temporary container in interactive mode.
    ``` docker run -p5000:5000 -t -i --rm assessment/pizzaservice ```


## Setting up istio service mesh and cluster.

To run pods, services, deployments you are required to execute below command.
   ``` $ kubectl apply -f pizzashop-isitio/platform/pizzashop-services.yaml```

To run virtual service and open the services to public for gateway, you are required to execute below command.
   ``` $ kubectl apply -f pizzashop-isitio/networking/pizzashop-gateway.yaml```
