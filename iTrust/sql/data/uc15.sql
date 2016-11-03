INSERT INTO cptCode(code, name) VALUES
('90467','Hepatitis C Vaccine');
ON DUPLICATE KEY UPDATE Code = Code;


INSERT INTO icdCode(code, name, is_chronic) VALUES
('U21','Makena Syndrome', 0);

INSERT INTO ndcodes(Code, Description) VALUES
('081096','Aspirin');
