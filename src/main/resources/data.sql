-- Insert Products
INSERT INTO TB_PRODUCT (product_number,name,price,stock_quantity,creation_date,country,product_section)
VALUES ('123','Book',15,5,CURRENT_TIME,'Colombia','Academic');

INSERT INTO TB_PRODUCT (product_number,name,price,stock_quantity,creation_date,country,product_section)
VALUES ('124','Pc',1000,5,CURRENT_TIME,'Colombia','Tech');


-- Insert Customers
INSERT INTO TB_CUSTOMER (full_name,address,phone_number,city)
VALUES ('Marcos Soto','Add1','1234567','Bogota');

INSERT INTO TB_CUSTOMER (full_name,address,phone_number,city)
VALUES ('Lina Diaz','Add2','1234568','Medellin');
