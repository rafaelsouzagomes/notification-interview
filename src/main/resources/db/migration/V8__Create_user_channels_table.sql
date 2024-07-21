CREATE TABLE user_channels (
    user_id INT NOT NULL,
    channel_id INT NOT NULL,
    PRIMARY KEY (user_id, channel_id),
    FOREIGN KEY (user_id) REFERENCES USER_CUSTOMER(id_user),
    FOREIGN KEY (channel_id) REFERENCES Channel_Notification(id_channel_notification)
);
