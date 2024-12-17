CREATE TABLE WORK_ROLE (
                           ROLE_ID INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                           TITLE VARCHAR(50) NOT NULL,
                           DESCRIPTION VARCHAR(200) NOT NULL,
                           SALARY DOUBLE NOT NULL,
                           CREATION_DATE DATE NOT NULL
);