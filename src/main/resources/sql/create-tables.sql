
CREATE TABLE products (
                      product_id SERIAL PRIMARY KEY,
                      product_name VARCHAR(100) NOT NULL,
                      price DOUBLE PRECISION NOT NULL
);

CREATE TABLE carts (
                      cart_id SERIAL PRIMARY KEY
);

CREATE TABLE cart_product (
                    cart_id INT NOT NULL,
                    product_id INT NOT NULL,
                    quantity INT NOT NULL,
                    PRIMARY KEY (cart_id, product_id),
                    FOREIGN KEY (cart_id) REFERENCES carts(cart_id),
                    FOREIGN KEY (product_id) REFERENCES products(product_id)
);