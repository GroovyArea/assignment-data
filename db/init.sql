CREATE DATABASE IF NOT EXISTS assignment;
USE assignment;

CREATE TABLE card_transactions
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration_number     VARCHAR(255) NOT NULL,
    card_transaction_number INT          NOT NULL,
    status                  VARCHAR(255) NOT NULL,
    transaction_date        DATE         NOT NULL,
    transaction_time        TIME         NOT NULL,
    issuer_name             VARCHAR(255) NOT NULL,
    affiliate_issuer_name   VARCHAR(255) NOT NULL,
    card_number             VARCHAR(255) NOT NULL,
    auth_number             VARCHAR(255) NOT NULL,
    auth_amount             VARCHAR(255) NOT NULL,
    quota                   VARCHAR(255) NOT NULL,
    created_at              TIMESTAMP    NOT NULL,
    updated_at              TIMESTAMP    NOT NULL
) ENGINE = InnoDB;

CREATE INDEX idx_registration_created_at ON card_transactions (registration_number, created_at);

INSERT INTO card_transactions (registration_number, card_transaction_number, status, transaction_date, transaction_time,
                               issuer_name, affiliate_issuer_name, card_number, auth_number, auth_amount, quota,
                               created_at, updated_at)
VALUES ('REG123', 1001, 'completed', '2024-07-01', '12:00:00', 'Issuer1', 'Affiliate1', '1234-5678-9012-3456',
        'AUTH001', '1000', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG124', 1002, 'pending', '2024-07-02', '13:00:00', 'Issuer2', 'Affiliate2', '1234-5678-9012-3457', 'AUTH002',
        '2000', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG125', 1003, 'failed', '2024-07-03', '14:00:00', 'Issuer3', 'Affiliate3', '1234-5678-9012-3458', 'AUTH003',
        '3000', '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG126', 1004, 'completed', '2024-07-04', '15:00:00', 'Issuer4', 'Affiliate4', '1234-5678-9012-3459',
        'AUTH004', '4000', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG127', 1005, 'pending', '2024-07-05', '16:00:00', 'Issuer5', 'Affiliate5', '1234-5678-9012-3460', 'AUTH005',
        '5000', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG128', 1006, 'failed', '2024-07-06', '17:00:00', 'Issuer6', 'Affiliate6', '1234-5678-9012-3461', 'AUTH006',
        '6000', '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG129', 1007, 'completed', '2024-07-07', '18:00:00', 'Issuer7', 'Affiliate7', '1234-5678-9012-3462',
        'AUTH007', '7000', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG130', 1008, 'pending', '2024-07-08', '19:00:00', 'Issuer8', 'Affiliate8', '1234-5678-9012-3463', 'AUTH008',
        '8000', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG131', 1009, 'failed', '2024-07-09', '20:00:00', 'Issuer9', 'Affiliate9', '1234-5678-9012-3464', 'AUTH009',
        '9000', '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG132', 1010, 'completed', '2024-07-10', '21:00:00', 'Issuer10', 'Affiliate10', '1234-5678-9012-3465',
        'AUTH010', '10000', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG132', 1011, 'completed', '2024-01-12', '10:45:00', 'Issuer11', 'Affiliate11', '1234-5678-9012-3466',
        'AUTH011', '11000', '1', '2024-01-12 10:45:00', '2024-01-12 10:45:00'),
       ('REG132', 1012, 'pending', '2024-02-25', '11:30:00', 'Issuer12', 'Affiliate12', '1234-5678-9012-3467',
        'AUTH012', '12000', '2', '2024-02-25 11:30:00', '2024-02-25 11:30:00'),
       ('REG132', 1013, 'failed', '2024-03-15', '12:15:00', 'Issuer13', 'Affiliate13', '1234-5678-9012-3468', 'AUTH013',
        '13000', '3', '2024-03-15 12:15:00', '2024-03-15 12:15:00'),
       ('REG132', 1014, 'completed', '2024-04-10', '13:00:00', 'Issuer14', 'Affiliate14', '1234-5678-9012-3469',
        'AUTH014', '14000', '1', '2024-04-10 13:00:00', '2024-04-10 13:00:00'),
       ('REG132', 1015, 'pending', '2024-05-18', '13:45:00', 'Issuer15', 'Affiliate15', '1234-5678-9012-3470',
        'AUTH015', '15000', '2', '2024-05-18 13:45:00', '2024-05-18 13:45:00'),
       ('REG132', 1016, 'failed', '2024-06-05', '14:30:00', 'Issuer16', 'Affiliate16', '1234-5678-9012-3471', 'AUTH016',
        '16000', '3', '2024-06-05 14:30:00', '2024-06-05 14:30:00'),
       ('REG132', 1017, 'completed', '2024-06-20', '15:15:00', 'Issuer17', 'Affiliate17', '1234-5678-9012-3472',
        'AUTH017', '17000', '1', '2024-06-20 15:15:00', '2024-06-20 15:15:00'),
       ('REG132', 1018, 'pending', '2024-07-02', '16:00:00', 'Issuer18', 'Affiliate18', '1234-5678-9012-3473',
        'AUTH018', '18000', '2', '2024-07-02 16:00:00', '2024-07-02 16:00:00'),
       ('REG132', 1019, 'failed', '2024-07-10', '16:45:00', 'Issuer19', 'Affiliate19', '1234-5678-9012-3474', 'AUTH019',
        '19000', '3', '2024-07-10 16:45:00', '2024-07-10 16:45:00'),
       ('REG132', 1020, 'completed', '2024-01-05', '17:30:00', 'Issuer20', 'Affiliate20', '1234-5678-9012-3475',
        'AUTH020', '20000', '1', '2024-01-05 17:30:00', '2024-01-05 17:30:00'),
       ('REG132', 1021, 'pending', '2024-02-14', '18:15:00', 'Issuer21', 'Affiliate21', '1234-5678-9012-3476',
        'AUTH021', '21000', '2', '2024-02-14 18:15:00', '2024-02-14 18:15:00'),
       ('REG132', 1022, 'failed', '2024-03-22', '19:00:00', 'Issuer22', 'Affiliate22', '1234-5678-9012-3477', 'AUTH022',
        '22000', '3', '2024-03-22 19:00:00', '2024-03-22 19:00:00'),
       ('REG132', 1023, 'completed', '2024-04-30', '19:45:00', 'Issuer23', 'Affiliate23', '1234-5678-9012-3478',
        'AUTH023', '23000', '1', '2024-04-30 19:45:00', '2024-04-30 19:45:00'),
       ('REG132', 1024, 'pending', '2024-05-12', '20:30:00', 'Issuer24', 'Affiliate24', '1234-5678-9012-3479',
        'AUTH024', '24000', '2', '2024-05-12 20:30:00', '2024-05-12 20:30:00'),
       ('REG132', 1025, 'failed', '2024-06-25', '21:15:00', 'Issuer25', 'Affiliate25', '1234-5678-9012-3480', 'AUTH025',
        '25000', '3', '2024-06-25 21:15:00', '2024-06-25 21:15:00'),
       ('REG132', 1026, 'completed', '2024-07-01', '22:00:00', 'Issuer26', 'Affiliate26', '1234-5678-9012-3481',
        'AUTH026', '26000', '1', '2024-07-01 22:00:00', '2024-07-01 22:00:00'),
       ('REG132', 1027, 'pending', '2024-07-05', '22:45:00', 'Issuer27', 'Affiliate27', '1234-5678-9012-3482',
        'AUTH027', '27000', '2', '2024-07-05 22:45:00', '2024-07-05 22:45:00'),
       ('REG132', 1028, 'failed', '2024-07-10', '23:30:00', 'Issuer28', 'Affiliate28', '1234-5678-9012-3483', 'AUTH028',
        '28000', '3', '2024-07-10 23:30:00', '2024-07-10 23:30:00'),
       ('REG132', 1029, 'completed', '2024-01-15', '23:59:00', 'Issuer29', 'Affiliate29', '1234-5678-9012-3484',
        'AUTH029', '29000', '1', '2024-01-15 23:59:00', '2024-01-15 23:59:00');

CREATE TABLE receive_card_transactions
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_transaction_number INT          NOT NULL,
    status                  VARCHAR(255) NOT NULL,
    transaction_date        DATE         NOT NULL,
    transaction_time        TIME         NOT NULL,
    issuer_name             VARCHAR(255) NOT NULL,
    affiliate_issuer_name   VARCHAR(255) NOT NULL,
    card_number             VARCHAR(255) NOT NULL,
    auth_number             VARCHAR(255) NOT NULL,
    auth_amount             VARCHAR(255) NOT NULL,
    quota                   VARCHAR(255) NOT NULL,
    created_at              TIMESTAMP    NOT NULL,
    updated_at              TIMESTAMP    NOT NULL
) ENGINE = InnoDB;

INSERT INTO receive_card_transactions (card_transaction_number, status, transaction_date, transaction_time,
                                       issuer_name, affiliate_issuer_name, card_number, auth_number, auth_amount, quota,
                                       created_at, updated_at)
VALUES (1001, 'completed', '2024-07-01', '12:00:00', 'Issuer1', 'Affiliate1', '1234-5678-9012-3456', 'AUTH001', '1000',
        '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1002, 'pending', '2024-07-02', '13:00:00', 'Issuer2', 'Affiliate2', '1234-5678-9012-3457', 'AUTH002', '2000',
        '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1003, 'failed', '2024-07-03', '14:00:00', 'Issuer3', 'Affiliate3', '1234-5678-9012-3458', 'AUTH003', '3000',
        '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1004, 'completed', '2024-07-04', '15:00:00', 'Issuer4', 'Affiliate4', '1234-5678-9012-3459', 'AUTH004', '4000',
        '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1005, 'pending', '2024-07-05', '16:00:00', 'Issuer5', 'Affiliate5', '1234-5678-9012-3460', 'AUTH005', '5000',
        '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1006, 'failed', '2024-07-06', '17:00:00', 'Issuer6', 'Affiliate6', '1234-5678-9012-3461', 'AUTH006', '6000',
        '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1007, 'completed', '2024-07-07', '18:00:00', 'Issuer7', 'Affiliate7', '1234-5678-9012-3462', 'AUTH007', '7000',
        '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1008, 'pending', '2024-07-08', '19:00:00', 'Issuer8', 'Affiliate8', '1234-5678-9012-3463', 'AUTH008', '8000',
        '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1009, 'failed', '2024-07-09', '20:00:00', 'Issuer9', 'Affiliate9', '1234-5678-9012-3464', 'AUTH009', '9000',
        '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1010, 'completed', '2024-07-10', '21:00:00', 'Issuer10', 'Affiliate10', '1234-5678-9012-3465', 'AUTH010',
        '10000', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

CREATE TABLE connection_agreements
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration_number  VARCHAR(255) NOT NULL,
    connection_agreed    BOOLEAN      NOT NULL,
    connection_agreed_at TIMESTAMP NULL,
    created_at           TIMESTAMP    NOT NULL,
    updated_at           TIMESTAMP    NOT NULL
) ENGINE = InnoDB;

ALTER TABLE connection_agreements
    ADD CONSTRAINT unique_registration_number UNIQUE (registration_number);

INSERT INTO connection_agreements (registration_number, connection_agreed, connection_agreed_at, created_at,
                                   updated_at)
VALUES ('REG123', TRUE, '2024-07-01 12:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG124', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG125', TRUE, '2024-07-03 14:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG126', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG127', TRUE, '2024-07-05 16:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG128', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG129', TRUE, '2024-07-07 18:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG130', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG131', TRUE, '2024-07-09 20:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

CREATE TABLE data_transfer_agreements
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration_number     VARCHAR(255) NOT NULL,
    data_transfer_agreed    BOOLEAN      NOT NULL,
    data_transfer_agreed_at TIMESTAMP    NOT NULL,
    created_at              TIMESTAMP    NOT NULL,
    updated_at              TIMESTAMP    NOT NULL
) ENGINE = InnoDB;

ALTER TABLE data_transfer_agreements
    ADD CONSTRAINT unique_registration_number UNIQUE (registration_number);

INSERT INTO data_transfer_agreements (registration_number, data_transfer_agreed, data_transfer_agreed_at, created_at,
                                      updated_at)
VALUES ('REG123', TRUE, '2024-07-01 12:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG124', TRUE, '2024-07-02 13:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG125', TRUE, '2024-07-03 14:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG126', TRUE, '2024-07-04 15:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG127', TRUE, '2024-07-05 16:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG128', TRUE, '2024-07-06 17:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG129', TRUE, '2024-07-07 18:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG130', TRUE, '2024-07-08 19:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG131', TRUE, '2024-07-09 20:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
