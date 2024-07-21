CREATE TABLE Archived_Message (
    id_archived_message SERIAL PRIMARY KEY,
    channel_id INT,
    category_id INT,
    message TEXT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_origin_id INT,
    FOREIGN KEY (channel_id) REFERENCES Channel_Notification(id_channel_notification),
    FOREIGN KEY (category_id) REFERENCES Category_Message(id_category),
    FOREIGN KEY (user_origin_id) REFERENCES USER_CUSTOMER(id_user)
);
