CREATE TABLE Log_Message_Sent (
    id_log_message_sent SERIAL PRIMARY KEY,
    message TEXT,
    user_origin_id INT,
    user_destination_id INT,
    category_id INT,
    channel_id INT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_origin_id) REFERENCES USER_CUSTOMER(id_user),
    FOREIGN KEY (user_destination_id) REFERENCES USER_CUSTOMER(id_user),
    FOREIGN KEY (category_id) REFERENCES Category_Message(id_category),
    FOREIGN KEY (channel_id) REFERENCES Channel_Notification(id_channel_notification)
);
