ALTER TABLE "groups"
    ADD "founder_id" integer;
ALTER TABLE "groups" ADD FOREIGN KEY ("founder_id") REFERENCES "users" ("id");
