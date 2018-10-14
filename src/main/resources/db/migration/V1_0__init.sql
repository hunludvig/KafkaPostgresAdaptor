CREATE SEQUENCE diagnostic_id_seq;
CREATE TABLE diagnostic (
	id BIGINT PRIMARY KEY DEFAULT nextval('diagnostic_id_seq')
);