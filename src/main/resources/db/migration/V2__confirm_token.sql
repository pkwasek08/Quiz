DROP TABLE IF EXISTS "confirmation_token" CASCADE;
CREATE TABLE "confirmation_token" (
                          "id" SERIAL PRIMARY KEY,
                          "confirmation_token" varchar,
                          "created_date"  timestamp,
                          "user_id" integer
);

ALTER TABLE "confirmation_token" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
