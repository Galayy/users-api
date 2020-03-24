CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE role AS ENUM ('ROLE_USER', 'ROLE_ADMIN' );

CREATE TABLE "user"
(
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  first_name TEXT        NOT NULL,
  last_name  TEXT,
  phone      TEXT,
  login      TEXT UNIQUE NOT NULL,
  password   TEXT UNIQUE NOT NULL,
  created_at timestamp   NOT NULL,
  updated_at timestamp,
  deleted_at timestamp,
  role       role        NOT NULL
);
