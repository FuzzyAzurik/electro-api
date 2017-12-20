CREATE TABLE BLINK (
  ID          BIGSERIAL,
  LIGHT_VALUE INT                                 NULL,
  LIGHT_RATIO NUMERIC(10, 2)                      NULL,
  INSERTED_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  METER_ID    BIGINT                              NOT NULL,
  KWH_VALUE   NUMERIC(10, 8)                      NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (METER_ID) REFERENCES METER(ID)
);

CREATE INDEX BLINK_INDEX
  ON BLINK (
    ID,
    INSERTED_TIME
  )