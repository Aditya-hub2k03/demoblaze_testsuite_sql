-- Create the database
CREATE DATABASE IF NOT EXISTS demoblaze_testdb;

-- Use the database
USE demoblaze_testdb;

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the products table
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Create the orders table
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);


-- Insert sample users
INSERT INTO users (username, password) VALUES
('user1', 'password1'),
('user2', 'password2');

-- Insert sample products
INSERT INTO products (name, category, price) VALUES
('Samsung galaxy s6', 'Phones', 360.00),
('Nokia lumia 1520', 'Phones', 820.00),
('Sony vaio i5', 'Laptops', 790.00),
('Apple monitor 24', 'Monitors', 400.00);

-- Insert sample orders
INSERT INTO orders (user_id, product_id, quantity, total_price) VALUES
(1, 1, 1, 360.00),
(2, 2, 1, 820.00);
