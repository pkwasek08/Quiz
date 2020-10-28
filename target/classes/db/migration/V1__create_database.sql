DROP TABLE IF EXISTS "Users" CASCADE;
CREATE TABLE "Users" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "lastname" varchar,
  "degree" varchar,
  "email" varchar,
  "department" varchar,
  "major" varchar,
  "group_id" integer,
  "password" varchar
);

DROP TABLE IF EXISTS "Groups" CASCADE;
CREATE TABLE "Groups" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar
);

DROP TABLE IF EXISTS "User_Group_Subject" CASCADE;
CREATE TABLE "User_Group_Subject" (
  "id" SERIAL PRIMARY KEY,
  "user_id" integer,
  "group_id" integer,
  "subject_id" integer,
  "teacher_id" integer
);

DROP TABLE IF EXISTS "Subjects" CASCADE;
CREATE TABLE "Subjects" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar
);

DROP TABLE IF EXISTS "Tests" CASCADE;
CREATE TABLE "Tests" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "subject_id" integer,
  "full_points" integer,
  "date" timestamp,
  "time" time
);

DROP TABLE IF EXISTS "Results" CASCADE;
CREATE TABLE "Results" (
  "id" SERIAL PRIMARY KEY,
  "mark" integer,
  "points" integer,
  "user_id" integer,
  "generate_test_id" integer,
  "previous_version" integer
);

DROP TABLE IF EXISTS "Tasks" CASCADE;
CREATE TABLE "Tasks" (
  "id" SERIAL PRIMARY KEY,
  "question" varchar,
  "type" varchar,
  "image" varchar,
  "test_id" integer,
  "points" integer
);

DROP TABLE IF EXISTS "Answers" CASCADE;
CREATE TABLE "Answers" (
  "id" SERIAL PRIMARY KEY,
  "answer" varchar,
  "correct" boolean,
  "tasks_id" integer
);

DROP TABLE IF EXISTS "Generate_Tests" CASCADE;
CREATE TABLE "Generate_Tests" (
  "id" SERIAL PRIMARY KEY,
  "test_id" integer
);

DROP TABLE IF EXISTS "User_Subject" CASCADE;
CREATE TABLE "User_Subject" (
  "id" SERIAL PRIMARY KEY,
  "subject_id" integer,
  "user_id" integer
);

DROP TABLE IF EXISTS "Generate_Tasks" CASCADE;
CREATE TABLE "Generate_Tasks" (
  "id" SERIAL PRIMARY KEY,
  "tasks_id" integer,
  "generate_test" integer
);

DROP TABLE IF EXISTS "Chosen_Answers" CASCADE;
CREATE TABLE "Chosen_Answers" (
  "id" SERIAL PRIMARY KEY,
  "generate_tasks_id" integer,
  "answer_id" integer,
  "descripted_answer" varchar
);

ALTER TABLE "Tests" ADD FOREIGN KEY ("subject_id") REFERENCES "Subjects" ("id");

ALTER TABLE "Results" ADD FOREIGN KEY ("user_id") REFERENCES "Users" ("id");

ALTER TABLE "Tasks" ADD FOREIGN KEY ("test_id") REFERENCES "Tests" ("id");

ALTER TABLE "Results" ADD FOREIGN KEY ("previous_version") REFERENCES "Results" ("id");

ALTER TABLE "Generate_Tests" ADD FOREIGN KEY ("test_id") REFERENCES "Tests" ("id");

ALTER TABLE "Results" ADD FOREIGN KEY ("generate_test_id") REFERENCES "Generate_Tests" ("id");

ALTER TABLE "Generate_Tasks" ADD FOREIGN KEY ("tasks_id") REFERENCES "Tasks" ("id");

ALTER TABLE "Generate_Tasks" ADD FOREIGN KEY ("generate_test") REFERENCES "Generate_Tests" ("id");

ALTER TABLE "Answers" ADD FOREIGN KEY ("tasks_id") REFERENCES "Tasks" ("id");

ALTER TABLE "User_Subject" ADD FOREIGN KEY ("user_id") REFERENCES "Users" ("id");

ALTER TABLE "User_Subject" ADD FOREIGN KEY ("subject_id") REFERENCES "Subjects" ("id");

ALTER TABLE "Chosen_Answers" ADD FOREIGN KEY ("answer_id") REFERENCES "Answers" ("id");

ALTER TABLE "Chosen_Answers" ADD FOREIGN KEY ("generate_tasks_id") REFERENCES "Generate_Tasks" ("id");

ALTER TABLE "User_Group_Subject" ADD FOREIGN KEY ("user_id") REFERENCES "Users" ("id");

ALTER TABLE "User_Group_Subject" ADD FOREIGN KEY ("group_id") REFERENCES "Groups" ("id");

ALTER TABLE "User_Group_Subject" ADD FOREIGN KEY ("subject_id") REFERENCES "Subjects" ("id");

ALTER TABLE "User_Group_Subject" ADD FOREIGN KEY ("teacher_id") REFERENCES "Users" ("id");
