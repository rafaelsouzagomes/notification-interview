CREATE TABLE Inconsistent_Message (
    id_inconsistent_message SERIAL PRIMARY KEY,
    channel_id INT,
    category_id INT,
    error TEXT,
    message TEXT,
    FOREIGN KEY (channel_id) REFERENCES Channel_Notification(id_channel_notification),
    FOREIGN KEY (category_id) REFERENCES Category_Message(id_category)
);
