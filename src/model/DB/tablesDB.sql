CREATE TABLE IF NOT EXISTS "users" (
	id                       SERIAL                            PRIMARY KEY,
	login                    VARCHAR (3)                   UNIQUE NOT NULL,
	password                 VARCHAR (150)                        NOT NULL,
	name                     VARCHAR (50)                  UNIQUE NOT NULL,
	email                    VARCHAR (60)                         NOT NULL,
	ref_function      	 INT                                          ,
	date_create              TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
	state                    INT                        DEFAULT 1 NOT NULL
);

CREATE TABLE IF NOT EXISTS "departments" (
	id                       SERIAL                            PRIMARY KEY,
	name                     VARCHAR (50)                  UNIQUE NOT NULL,
	description              TEXT                                 NOT NULL,
        ref_user_manager         INT                                  NOT NULL
);

CREATE TABLE IF NOT EXISTS "sectors" (
	id                       SERIAL                            PRIMARY KEY,
	name                     VARCHAR (50)                         NOT NULL,
	description              TEXT                                 NOT NULL,
        ref_departments          INT                                  NOT NULL,
        ref_user_leader          INT                                  NOT NULL
);

CREATE TABLE IF NOT EXISTS "functions" (
	id                       SERIAL                            PRIMARY KEY,
	name                     VARCHAR (50)                  	      NOT NULL,
	description              TEXT                                 NOT NULL,
        ref_sector               INT                                  NOT NULL
);

CREATE TABLE IF NOT EXISTS "tasks" (
	id                       SERIAL                            PRIMARY KEY,
	name                     VARCHAR (50)                  	      NOT NULL,
        ref_user_created         INT                                  NOT NULL,
        ref_user_analyze         INT                                          ,
        ref_user_doit            INT                                          ,
        ref_user_check           INT                                          ,
        date_create              TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
        date_analyze             TIMESTAMP                                    ,
        date_doit                TIMESTAMP                                    ,
        date_check               TIMESTAMP                            	      ,
        description_create_task  TEXT                                 NOT NULL,
        description_analyze_task TEXT                                         ,
        description_doit_task    TEXT                                         ,
        description_check_task   TEXT                                 	      ,
        ref_user_responsibility  INT                                  NOT NULL,
        state                    INT                        DEFAULT 1 NOT NULL
);


ALTER TABLE "users" 
ADD FOREIGN KEY (ref_function) REFERENCES "functions" (id);

ALTER TABLE "departments" 
ADD FOREIGN KEY (ref_user_manager) REFERENCES "users" (id);

ALTER TABLE "sectors" 
ADD FOREIGN KEY (ref_departments) REFERENCES "departments" (id),
ADD FOREIGN KEY (ref_user_leader) REFERENCES "users" (id);

ALTER TABLE "functions"
ADD FOREIGN KEY (ref_sector) REFERENCES "sectors" (id);

ALTER TABLE "tasks" 
ADD FOREIGN KEY (ref_user_created) REFERENCES "users" (id),
ADD FOREIGN KEY (ref_user_analyze) REFERENCES "users" (id),
ADD FOREIGN KEY (ref_user_doit) REFERENCES "users" (id),
ADD FOREIGN KEY (ref_user_check) REFERENCES "users" (id),
ADD FOREIGN KEY (ref_user_responsibility) REFERENCES "users" (id);