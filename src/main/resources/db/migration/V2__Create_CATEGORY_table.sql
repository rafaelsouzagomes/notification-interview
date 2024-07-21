CREATE TABLE Category_Message (
    id_category SERIAL PRIMARY KEY,
    type_category VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL 
);
