ALTER TABLE diagnostic ADD COLUMN os_name VARCHAR(100);
ALTER TABLE diagnostic ADD COLUMN arch VARCHAR(100);
ALTER TABLE diagnostic ADD COLUMN processors INTEGER;
ALTER TABLE diagnostic ADD COLUMN system_load DOUBLE PRECISION;
ALTER TABLE diagnostic ADD COLUMN "timestamp" BIGINT;