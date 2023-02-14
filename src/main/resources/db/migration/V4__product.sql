CREATE TABLE IF NOT EXISTS product(
    id SERIAL NOT NULL,
    description VARCHAR (100),
    brand VARCHAR (100),
    stock INT,
    category_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(category_id) REFERENCES category(id)
    );