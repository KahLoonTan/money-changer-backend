create table currency (
    id bigint identity not null,
    created_by nvarchar(255) not null,
    created_time timestamp with time zone not null,
    modified_by nvarchar(255) not null,
    modified_time timestamp with time zone not null,
    record_status int not null,
    version int not null,
    code nvarchar(10) not null,
    description nvarchar(50) not null,
    buy_rate double not null,
    sell_rate double not null,
    smallest_note int not null,
    primary key (id)
);

insert into currency
    (created_by, created_time, modified_by, modified_time, record_status, version, code, description, buy_rate, sell_rate, smallest_note)
    values
    ('system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 1, 0, 'SGD', 'Singapore Dollar', 1.00, 1.00, 100),
    ('system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 1, 0, 'USD', 'US Dollar', 1.3392, 1.3574, 100),
    ('system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 1, 0, 'HKD', 'HK Dollar', 0.1738, 0.1698, 100);

create table transactions (
    id bigint identity not null,
    created_by nvarchar(255) not null,
    created_time timestamp with time zone not null,
    currency_code nvarchar(10) not null,
    transaction_type nvarchar(50) not null,
    transaction_rate double not null,
    receive_amt double not null,
    release_amt double not null,
    primary key (id)
);

insert into transactions
    (created_by, created_time, currency_code, transaction_type, transaction_rate, receive_amt, release_amt)
    values
    ('system', CURRENT_TIMESTAMP, 'USD', 'Buy', 1.3392, 130, 174.10 ),
    ('system', CURRENT_TIMESTAMP, 'USD', 'Sell', 1.3574, 271.50, 200);