DROP TABLE IF EXISTS todolist;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
     id INT PRIMARY KEY NOT NULL UNIQUE,
     name varchar(250) NOT NULL,
    username varchar(250) NOT NULL,
    email varchar(250) NOT NULL,
    phone varchar(250) NOT NULL,
    website varchar(250) NOT NULL
    );
CREATE TABLE IF NOT EXISTS todolist (
    userId INT NOT NULL,
    id INT NOT NULL UNIQUE ,
    title varchar(250) NOT NULL,
    completed BOOLEAN,
    version INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(id)
    );
