drop table if exists Order_TB;

CREATE TABLE Order_TB (
    id   INTEGER NOT NULL AUTO_INCREMENT,
    order_name VARCHAR(400) NOT NULL,
    price INTEGER NOT NULL,
	quantity INTEGER NOT NULL,
    purchaseDate DATE,
    PRIMARY KEY (id)
);