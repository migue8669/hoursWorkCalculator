CREATE TABLE REPORT (
    ID_NUMBER_REPORT varchar primary key,
    ID_NUMBER_TECHNICIAN varchar not null ,
    DATE_INIT varchar not null,
    DATE_FINISH varchar not null,
    HOUR_INIT varchar not null,
    HOUR_FINISH varchar not null,
    NUM_WEEK varchar not null

);

CREATE TABLE REPORT_WEEKLY (
   ID_NUMBER_TECHNICIAN varchar not null,
   ID_NUMBER_REPORT varchar not null,
   NORMAL_HOUR varchar not null,
   NOCTURNAL_HOUR varchar not null,
   SUNDAY_HOUR varchar not null,
   EXTRA_NORMAL_HOUR varchar not null,
   EXTRA_NOCTURNAL_HOUR varchar not null,
   EXTRA_SUNDAY_HOUR varchar not null

);