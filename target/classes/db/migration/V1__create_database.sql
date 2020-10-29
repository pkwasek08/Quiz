DROP TABLE IF EXISTS "users" CASCADE;
CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "lastname" varchar,
  "degree" varchar,
  "email" varchar,
  "department" varchar,
  "major" varchar,
  "group_id" integer,
  "password" varchar,
  "login" varchar,
  "role" varchar
);

DROP TABLE IF EXISTS "groups" CASCADE;
CREATE TABLE "groups" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar
);

DROP TABLE IF EXISTS "user_group_subject" CASCADE;
CREATE TABLE "user_group_subject" (
  "id" SERIAL PRIMARY KEY,
  "user_id" integer,
  "group_id" integer,
  "subject_id" integer,
  "teacher_id" integer
);

DROP TABLE IF EXISTS "subjects" CASCADE;
CREATE TABLE "subjects" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar
);

DROP TABLE IF EXISTS "tests" CASCADE;
CREATE TABLE "tests" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "subject_id" integer,
  "full_points" integer,
  "date" timestamp,
  "time" time
);

DROP TABLE IF EXISTS "results" CASCADE;
CREATE TABLE "results" (
  "id" SERIAL PRIMARY KEY,
  "mark" integer,
  "points" integer,
  "user_id" integer,
  "generate_test_id" integer,
  "previous_version" integer
);

DROP TABLE IF EXISTS "tasks" CASCADE;
CREATE TABLE "tasks" (
  "id" SERIAL PRIMARY KEY,
  "question" varchar,
  "type" varchar,
  "image" varchar,
  "test_id" integer,
  "points" integer
);

DROP TABLE IF EXISTS "answers" CASCADE;
CREATE TABLE "answers" (
  "id" SERIAL PRIMARY KEY,
  "answer" varchar,
  "correct" boolean,
  "tasks_id" integer
);

DROP TABLE IF EXISTS "generate_tests" CASCADE;
CREATE TABLE "generate_tests" (
  "id" SERIAL PRIMARY KEY,
  "test_id" integer
);

DROP TABLE IF EXISTS "user_subject" CASCADE;
CREATE TABLE "user_subject" (
  "id" SERIAL PRIMARY KEY,
  "subject_id" integer,
  "user_id" integer
);

DROP TABLE IF EXISTS "generate_tasks" CASCADE;
CREATE TABLE "generate_tasks" (
  "id" SERIAL PRIMARY KEY,
  "tasks_id" integer,
  "generate_test" integer
);

DROP TABLE IF EXISTS "chosen_answers" CASCADE;
CREATE TABLE "chosen_answers" (
  "id" SERIAL PRIMARY KEY,
  "generate_tasks_id" integer,
  "answer_id" integer,
  "descripted_answer" varchar
);

ALTER TABLE "tests" ADD FOREIGN KEY ("subject_id") REFERENCES "subjects" ("id");

ALTER TABLE "results" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "tasks" ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "results" ADD FOREIGN KEY ("previous_version") REFERENCES "results" ("id");

ALTER TABLE "generate_tests" ADD FOREIGN KEY ("test_id") REFERENCES "tests" ("id");

ALTER TABLE "results" ADD FOREIGN KEY ("generate_test_id") REFERENCES "generate_tests" ("id");

ALTER TABLE "generate_tasks" ADD FOREIGN KEY ("tasks_id") REFERENCES "tasks" ("id");

ALTER TABLE "generate_tasks" ADD FOREIGN KEY ("generate_test") REFERENCES "generate_tests" ("id");

ALTER TABLE "answers" ADD FOREIGN KEY ("tasks_id") REFERENCES "tasks" ("id");

ALTER TABLE "user_subject" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_subject" ADD FOREIGN KEY ("subject_id") REFERENCES "subjects" ("id");

ALTER TABLE "chosen_answers" ADD FOREIGN KEY ("answer_id") REFERENCES "answers" ("id");

ALTER TABLE "chosen_answers" ADD FOREIGN KEY ("generate_tasks_id") REFERENCES "generate_tasks" ("id");

ALTER TABLE "user_group_subject" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_group_subject" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id");

ALTER TABLE "user_group_subject" ADD FOREIGN KEY ("subject_id") REFERENCES "subjects" ("id");

ALTER TABLE "user_group_subject" ADD FOREIGN KEY ("teacher_id") REFERENCES "users" ("id");
