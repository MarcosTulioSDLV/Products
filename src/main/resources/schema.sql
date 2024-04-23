    -- Table for Customer
    CREATE TABLE IF NOT EXISTS TB_CUSTOMER (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        full_name VARCHAR(255) NOT NULL UNIQUE,
        address VARCHAR(255) NOT NULL UNIQUE,
        phone_number VARCHAR(255) NOT NULL UNIQUE,
        city VARCHAR(255) NOT NULL
    );


  -- Table for Invoice
  CREATE TABLE IF NOT EXISTS TB_INVOICE (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      payment_method VARCHAR(255) NOT NULL,
      payment_date TIMESTAMP NOT NULL,
      purchase_total_price DOUBLE NOT NULL,
      customer_id BIGINT,
      FOREIGN KEY (customer_id) REFERENCES TB_CUSTOMER(id)
  );

    -- Table for Product
    CREATE TABLE IF NOT EXISTS TB_PRODUCT (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        product_number VARCHAR(255) NOT NULL UNIQUE,
        name VARCHAR(255) NOT NULL UNIQUE,
        price DOUBLE NOT NULL,
        stock_quantity INT NOT NULL,
        creation_date TIMESTAMP NOT NULL,
        country VARCHAR(255) NOT NULL,
        product_section VARCHAR(255) NOT NULL
     );

  -- Table for Invoice Product
  CREATE TABLE IF NOT EXISTS TB_INVOICE_PRODUCT (
      invoice_id BIGINT,
      product_id BIGINT,
      product_quantity INT NOT NULL,
      product_total_price DOUBLE NOT NULL,
      PRIMARY KEY (invoice_id, product_id),
      FOREIGN KEY (invoice_id) REFERENCES TB_INVOICE(id),
      FOREIGN KEY (product_id) REFERENCES TB_PRODUCT(id)
  );

