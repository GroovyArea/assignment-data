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
        'AUTH010', '10000', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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
    is_connection_agreed BOOLEAN      NOT NULL,
    connection_agreed_at TIMESTAMP    NULL,
    created_at           TIMESTAMP    NOT NULL,
    updated_at           TIMESTAMP    NOT NULL
) ENGINE = InnoDB;

ALTER TABLE connection_agreements
    ADD CONSTRAINT unique_registration_number UNIQUE (registration_number);

INSERT INTO connection_agreements (registration_number, is_connection_agreed, connection_agreed_at, created_at,
                                   updated_at)
VALUES ('REG123', TRUE, '2024-07-01 12:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG124', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG125', TRUE, '2024-07-03 14:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG126', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG127', TRUE, '2024-07-05 16:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG128', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG129', TRUE, '2024-07-07 18:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG130', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG131', TRUE, '2024-07-09 20:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG132', FALSE, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


CREATE TABLE data_transfer_agreements
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration_number     VARCHAR(255) NOT NULL,
    data_transfer_agreed    BOOLEAN      NOT NULL,
    data_transfer_agreed_at TIMESTAMP    NOT NULL,
    created_at              TIMESTAMP    NOT NULL,
    updated_at              TIMESTAMP    NOT NULL
) ENGINE = InnoDB;

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
       ('REG131', TRUE, '2024-07-09 20:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('REG132', TRUE, '2024-07-10 21:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
