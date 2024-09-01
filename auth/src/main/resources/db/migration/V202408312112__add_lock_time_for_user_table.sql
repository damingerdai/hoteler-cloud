ALTER TABLE users
    ADD COLUMN failed_login_attempts INT DEFAULT 0 AFTER password_type,
    ADD COLUMN account_non_locked BOOLEAN DEFAULT TRUE AFTER failed_login_attempts,
    ADD COLUMN lock_time TIMESTAMP AFTER account_non_locked;