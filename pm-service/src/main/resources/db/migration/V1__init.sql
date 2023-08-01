CREATE TABLE chat_rooms (
                            id bigserial NOT NULL,
                            chat_id varchar(255) NULL,
                            recipient_id varchar(255) NULL,
                            sender_id varchar(255) NULL,
                            CONSTRAINT chat_rooms_pkey PRIMARY KEY (id)
);
CREATE TABLE user_messages (
                               id bigserial NOT NULL,
                               create_date timestamp NULL,
                               message varchar(255) NULL,
                               message_status int2 NULL,
                               recipient_id varchar(255) NULL,
                               sender_id varchar(255) NULL,
                               CONSTRAINT user_messages_pkey PRIMARY KEY (id)
);