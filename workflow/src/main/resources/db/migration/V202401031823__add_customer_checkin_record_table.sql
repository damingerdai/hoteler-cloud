CREATE TABLE customer_checkin_record (
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    customer_id uuid NOT NULL,
    room_id uuid NOT NULL,
    begin_date timestamp without time zone,
    end_date timestamp without time zone,
    create_dt timestamp without time zone,
    create_user character varying(50),
    update_dt timestamp without time zone,
    update_user character varying(50),
    deleted_at timestamp with time zone
);

ALTER TABLE customer_checkin_record
  ADD FOREIGN KEY (customer_id) REFERENCES customers("id"),
  ADD FOREIGN KEY (room_id) REFERENCES rooms("id");