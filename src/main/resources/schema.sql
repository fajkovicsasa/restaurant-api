CREATE TABLE IF NOT EXISTS TABLE_LOCATIONS
(
    ID        BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME      VARCHAR NOT NULL,
    IS_ACTIVE BOOLEAN NOT NULL,
    PARENT_ID BIGINT
);

CREATE TABLE IF NOT EXISTS TABLES
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    CAPACITY    INT     NOT NULL,
    LOCATION_ID INT     NOT NULL,
    IS_ACTIVE   BOOLEAN NOT NULL,
    FOREIGN KEY (LOCATION_ID) REFERENCES TABLE_LOCATIONS (ID)
);

CREATE TABLE IF NOT EXISTS RESERVATIONS
(
    ID           BIGINT PRIMARY KEY AUTO_INCREMENT,
    PEOPLE_COUNT BIGINT   NOT NULL,
    DATE_TIME    DATETIME NOT NULL,
    TABLE_ID     BIGINT   NOT NULL,
    FOREIGN KEY (TABLE_ID) REFERENCES TABLES (ID)
);

CREATE TABLE IF NOT EXISTS FOOD_CATEGORIES
(
    ID                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME               VARCHAR NOT NULL,
    SERVING_TIME_FROM  TIME    NOT NULL,
    SERVING_TIME_UNTIL TIME,
    IS_ACTIVE          BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS RESERVATIONS_FOOD_CATEGORIES
(
    RESERVATION_ID   BIGINT NOT NULL,
    FOOD_CATEGORY_ID BIGINT NOT NULL,
    FOREIGN KEY (RESERVATION_ID) REFERENCES RESERVATIONS (ID),
    FOREIGN KEY (FOOD_CATEGORY_ID) REFERENCES FOOD_CATEGORIES (ID),
    PRIMARY KEY (RESERVATION_ID, FOOD_CATEGORY_ID)
);

CREATE TABLE IF NOT EXISTS APPLICATION_SETTINGS
(
    ID    BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME  VARCHAR UNIQUE NOT NULL,
    VALUE VARCHAR        NOT NULL
);

CREATE TABLE IF NOT EXISTS USERS
(
    ID        BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    IS_ACTIVE BOOLEAN      NOT NULL,
    USERNAME  VARCHAR(50)  NOT NULL UNIQUE,
    PASSWORD  VARCHAR(100) NOT NULL
);


