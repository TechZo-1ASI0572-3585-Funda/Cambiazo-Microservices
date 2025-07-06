CREATE USER IF NOT EXISTS 'cambiazo'@'%' IDENTIFIED BY 'cambiazo123';

GRANT ALL PRIVILEGES ON userDb.* TO 'cambiazo'@'%';
GRANT ALL PRIVILEGES ON productDb.* TO 'cambiazo'@'%';
GRANT ALL PRIVILEGES ON exchangeDb.* TO 'cambiazo'@'%';
GRANT ALL PRIVILEGES ON donationDb.* TO 'cambiazo'@'%';

FLUSH PRIVILEGES;
