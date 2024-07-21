CREATE TABLE Channel_Notification (
    id_channel_notification SERIAL PRIMARY KEY,
    type_channel VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL 
);
