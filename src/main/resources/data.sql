CREATE TABLE salary_survey AS SELECT * FROM CSVREAD('./src/main/resources/salary_survey-3.csv');
ALTER TABLE salary_survey ADD COLUMN id IDENTITY ;