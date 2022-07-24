Description: Set up the backend for an online store to buy items.
----------------------------------------------------------------
----------------------------------------------------------------
GitHub: https://github.com/akmagar/sample-1

User Stories
------------
1. Users can register and login to add items to their cart.
2. Then they can place orders.
3. Registered users are are stored in the Users table.
4. Items that users can buy are stored in the Items table.
5. User's carts are stored in the Cart table.
6. Users orders' are stored in the Orders table.

Features Implemented
--------------------
1. Login/Logout - login and logout users
2. Register - register new users
3. Get Users - get all users and their cart Get Items
4. Gets all items that are available in store
5. Add Item to Cart
6. adds selected item to cart

Technologies Used
-----------------
Spring Framework: Spring Boot, Spring Data

Login/Logout
------------

1. Login: POST http: ec2-13-57-7-68.us-west-1.compute.amazonaws.com:8082/login

   Body:
   {
   "username":"Ananda Magar",
   "password":"anandamagar"
   }


3. Logout: POST http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/logout

   Body: Nothing needs to be entered

Users
-----

1. View All Users (Authorization: Anyone): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/users

   Body: Nothing needs to be provided


2. View User By Id (Authorization: ADMIN, EMPLOYEE): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/users/{id}

   Body: {1} User Id needs to be provided in the URI


3. Register New User (Authorization: Anyone): POST http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/users

   Body:
   {
   "username":"Ananda Magar",
   "password":"anandamagar"
   "role":"ADMIN"
   }


4. Update User Information (Authorization: ADMIN, EMPLOYEE, CUSTOMER): PUT http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/users

   Body:
   {
   "id":1
   "username":"Ananda Magar",
   "password":"anandamagar",
   "role":"CUSTOMER" (Role is being updated here)
   }


5. Delete User By Id (Authorization: ADMIN, EMPLOYEE): DELETE http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/{id}

   Body: {1} User Id needs to be provided in the URI


Items
-----
1. View All Items (Authorization: Anyone): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/items

   Body: Nothing needs to be provided


2. View Item By Id (Authorization: Anyone): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/items/{id}

   Body: {1} Item Id needs to be provided in the URI


3. Add New Item (Authorization: ADMIN, EMPLOYEE): POST http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/items

   Body:
   {
   "itemName": "iPhone11",
   "itemPrice": 999,
   "stockQuantity": 20
   }


4. Update Item (Authorization: ADMIN, EMPLOYEE, CUSTOMER): PUT http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/items

   Body:
   {
   "id": 4,
   "itemName": "iPhone11",
   "itemPrice": 999,
   "stockQuantity": 20
   }


5. Delete Item By Id (Authorization: ADMIN, EMPLOYEE): DELETE http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/items/{id}

   Body: {1} Item Id needs to be provided in the URI

Carts
-----
1. View All Carts (Authorization: ADMIN, EMPLOYEE): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/carts

   Body: Nothing needs to be provided


2. View Cart By Id (Authorization: ADMIN, EMPLOYEE): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/carts/{id}

   Body: Cart Id needs to be provided in the URI


3. Add New Cart (Authorization: CUSTOMER): POST http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/carts

   Body:
   {
   "item_id": 50,
   "user_id": 9,
   "total":300,
   "item_quantity":340
   }


5. Update Cart (Authorization: CUSTOMER): PUT http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/carts

   Body:
   {
   "id":1
   "item_id": 50,
   "user_id": 9,
   "total":300,
   "item_quantity":60 (Quantity is being updated here)
   }


6. Delete Item By Id (Authorization: ADMIN, EMPLOYEE): DELETE http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/carts/{id}

   Body: {1} Cart Id needs to be provided in the URI


Orders
------
1. View All Orders (Authorization: ADMIN, EMPLOYEE): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/orders

   Body: Nothing needs to be provided


3. View Order By Id (Authorization: ADMIN, EMPLOYEE): GET http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/orders/{order_id}

   Body: {1} Order Id needs to be provided in the URI


4. Add New Order (Authorization: CUSTOMER): POST http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/orders

   Body:
   {
   "user_id":4,
   "shippingAddress":"1332 S Calgary Way, Denver, CO",
   "total":1298,
   "item_quantity":7
   }


5. Delete Item By Id (Authorization: ADMIN, EMPLOYEE, CUSTOMER): DELETE http://ec2-13-57-7-68.us-west-1.compute.amazonaws.com:/8082/orders/{order_id}

   Body: {1} Order Id needs to be provided in the URI