INSERT INTO cptcodes(Code, name) VALUES
('90281','IG'),
('90632','Hep A, adult'),
('90633','Hep A, ped/adol, 2 dose'),
('90634','Hep A, ped/adol, 3 dose'),
('90649', 'HPV, quadrivalent'),
('90650', 'HPV, bivalent'),
('90653', 'influenza, trivalent, adjuvanted')
ON DUPLICATE KEY UPDATE Code = Code;
