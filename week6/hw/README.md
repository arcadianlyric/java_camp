#### optimization with SQL
Design a table for a E-commerce scenario, including user, commodities, orders.  

```
CREATE TABLE Commodities(
    commodity_id INT PRIMARY KEY AUTO_INCREMENT,
    commodity_name VARCHAR(255),
    retail_price DECIMAL(255),
    stock_count INT,
    );

CREATE TABLE Users(
    user_id INT PRIMARY KEY AUTO_INCREMENT, 
    name VARCHAR(255), 
    login_time TIMESTAMP,
    logout_time TIMESTAMP
    );

CREATE TABLE Orders(
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    commodity_id INT,
    commodity_count INT,
    order_time TIMESTAMP,
    foreign key(user_id) references Users(user_id),
    foreign key(commodity_id) references Commodities(commodity_id)
);

```
