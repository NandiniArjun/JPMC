# JPMC
The archive has both FE and BE modules. FE coded in Angular and BE coded using Java 17, Spring Boot and Microservices architecture.

BE has 2 modules, PortfolioMS and StockportfolioMS.
PortfolioMS : This Microservice runs on port 9090 and fetches the data from alpha vantage api.
StockportfolioMS: This Microservice runs on port 8080 and performs all CRUD operations for stocks.

StockPortfolioFE: This FE module uses Material Grid to display the stocks data as a grid. It has Add, Delete and row wise Edit options. When an Add Row is clicked, a default row is added to the grid with default symbol and name. This doesn't adds to the database. When the row is edited and clicked on Save does the persistence to DB. To run, execute "ng serve" and the FE can be accessed with the URL "http://localhost:4200". This module connects to CRUD operating microservice with HttpClient.

I was not very clear on the data to display and hence used a few columns of Stock data. The DB script is as below,
-----------------------------------
 CREATE TABLE Stock(  
    id int NOT NULL AUTO_INCREMENT,  
    name varchar(45) NOT NULL,  
    symbol varchar(35) NOT NULL,  
    previousPrice double DEFAULT '0' NOT NULL ,
    currentPrice double DEFAULT '0' NOT NULL ,
    PRIMARY KEY (id)  
);
---------------------------------------
Run the Alpha vantage microservice and the endpoint is,
http://localhost:9090/real-time-stock-prices/IBM

I have not completed calculating the total value based on current stock price since I dont know what formula to use. But have written a JPA query to fetch the sum of price for a given symbol.

Please let me know your questions.
