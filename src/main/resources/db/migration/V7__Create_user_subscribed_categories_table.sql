CREATE TABLE user_subscribed_categories (
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (user_id, category_id),
    FOREIGN KEY (user_id) REFERENCES USER_CUSTOMER(id_user),
    FOREIGN KEY (category_id) REFERENCES Category_Message(id_category)
);
