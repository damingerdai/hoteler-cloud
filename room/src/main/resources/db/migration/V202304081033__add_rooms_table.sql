CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS "rooms" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid(),
	"name" VARCHAR(200) NOT NULL,
	"status" INTEGER NOT NULL DEFAULT '1',
	"price" NUMERIC NOT NULL DEFAULT '0',
	"create_dt" TIMESTAMP NOT NULL DEFAULT NOW(),
	"create_user" VARCHAR(50) NOT NULL DEFAULT 'system',
	"update_dt" TIMESTAMP NOT NULL DEFAULT NOW(),
	"update_user" VARCHAR(50) NOT NULL,
	"deleted_at" TIMESTAMP,
	PRIMARY KEY ("id")
);

COMMENT ON TABLE rooms IS 'rooms';
COMMENT ON COLUMN "rooms"."id" IS '';
COMMENT ON COLUMN "rooms"."name" IS '';
COMMENT ON COLUMN "rooms"."status" IS '';
COMMENT ON COLUMN "rooms"."price" IS '';
COMMENT ON COLUMN "rooms"."create_dt" IS '';
COMMENT ON COLUMN "rooms"."create_user" IS '';
COMMENT ON COLUMN "rooms"."update_dt" IS '';
COMMENT ON COLUMN "rooms"."update_user" IS '';
COMMENT ON COLUMN "rooms"."deleted_at" IS '';