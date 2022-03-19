insert into currency
    (created_by, created_time, modified_by, modified_time, record_status, version, code, description, buy_rate, sell_rate, smallest_note)
    values
    ('system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 1, 0, 'SGD', 'Singapore Dollar', 1.00, 1.00, 100),
    ('system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 1, 0, 'USD', 'US Dollar', 1.3392, 1.3574, 100),
    ('system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 1, 0, 'HKD', 'HK Dollar', 0.1738, 0.1698, 100);

