

CREATE INDEX idx_usercustomer_iduser ON user_customer (id_user);

CREATE INDEX idx_user_subscribed_categories_user_id ON user_subscribed_categories (user_id);
CREATE INDEX idx_user_subscribed_categories_category_id ON user_subscribed_categories (category_id);

CREATE INDEX idx_user_channels_user_id ON user_channels (user_id);
CREATE INDEX idx_user_channels_channel_id ON user_channels (channel_id);