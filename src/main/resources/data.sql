-- RESET (opcional pero recomendado)
DELETE FROM previous_teams;
DELETE FROM races;
DELETE FROM drivers;
DELETE FROM teams;

-- ======================
-- TEAMS
-- ======================
INSERT INTO teams (team_name, logo, points, active) VALUES
('Red Bull', 'redbull_logo.png', 8, true),
('Mercedes', 'mercedes_logo.png', 43, true),
('Ferrari', 'ferrari_logo.png', 27, true),
('McLaren', 'mclaren_logo.png', 10, true),
('Aston Martin', 'astonmartin_logo.png', 0, true),
('Alpine', 'alpine_logo.png', 1, true),
('Haas', 'haas_logo.png', 6, true),
('Racing Bulls', 'racingbulls_logo.png', 4, true),
('Audi', 'audi_logo.png', 2, true),
('Williams', 'williams_logo.png', 0, true),
('Cadillac', 'cadillac_logo.png', 0, true),

('Toro Rosso', 'tororosso.png', 0, false),
('Minardi', 'minardi.png', 0, false),
('Renault', 'renault.png', 0, false),
('Force India / Racing Point', 'forceindia.png', 0, false),
('Sauber', 'sauber.png', 0, false),
('Manor', 'manor.png', 0, false),
('AlphaTauri', 'alphatauri.png', 0, false),
('Alfa Romeo', 'alfaromeo.png', 0, false);

-- ======================
-- DRIVERS
-- ======================
INSERT INTO drivers (driver_name, car_number, team_id, flag, imagen, points) VALUES
('Max Verstappen', 1, (SELECT team_id FROM teams WHERE team_name='Red Bull'), 'flag_netherlands.png', 'max_verstappen.png', 8),
('Isack Hadjar', 6, (SELECT team_id FROM teams WHERE team_name='Red Bull'), 'flag_france.png', 'isack_hadjar.png', 0),

('George Russell', 63, (SELECT team_id FROM teams WHERE team_name='Mercedes'), 'flag_uk.png', 'george_russell.png', 25),
('Kimi Antonelli', 12, (SELECT team_id FROM teams WHERE team_name='Mercedes'), 'flag_italy.png', 'kimi_antonelli.png', 18),

('Charles Leclerc', 16, (SELECT team_id FROM teams WHERE team_name='Ferrari'), 'flag_monaco.png', 'charles_leclerc.png', 15),
('Lewis Hamilton', 44, (SELECT team_id FROM teams WHERE team_name='Ferrari'), 'flag_uk.png', 'lewis_hamilton.png', 12),

('Lando Norris', 4, (SELECT team_id FROM teams WHERE team_name='McLaren'), 'flag_uk.png', 'lando_norris.png', 10),
('Oscar Piastri', 81, (SELECT team_id FROM teams WHERE team_name='McLaren'), 'flag_australia.png', 'oscar_piastri.png', 0),

('Fernando Alonso', 14, (SELECT team_id FROM teams WHERE team_name='Aston Martin'), 'flag_spain.png', 'fernando_alonso.png', 0),
('Lance Stroll', 18, (SELECT team_id FROM teams WHERE team_name='Aston Martin'), 'flag_canada.png', 'lance_stroll.png', 0),

('Pierre Gasly', 10, (SELECT team_id FROM teams WHERE team_name='Alpine'), 'flag_france.png', 'pierre_gasly.png', 1),
('Franco Colapinto', 43, (SELECT team_id FROM teams WHERE team_name='Alpine'), 'flag_argentina.png', 'franco_colapinto.png', 0),

('Esteban Ocon', 31, (SELECT team_id FROM teams WHERE team_name='Haas'), 'flag_france.png', 'esteban_ocon.png', 0),
('Oliver Bearman', 50, (SELECT team_id FROM teams WHERE team_name='Haas'), 'flag_uk.png', 'ollie_bearman.png', 6),

('Liam Lawson', 30, (SELECT team_id FROM teams WHERE team_name='Racing Bulls'), 'flag_new_zealand.png', 'liam_lawson.png', 0),
('Arvid Lindblad', 41, (SELECT team_id FROM teams WHERE team_name='Racing Bulls'), 'flag_uk.png', 'arvid_lindblad.png', 4),

('Nico Hulkenberg', 27, (SELECT team_id FROM teams WHERE team_name='Audi'), 'flag_germany.png', 'nico_hulkenberg.png', 0),
('Gabriel Bortoleto', 5, (SELECT team_id FROM teams WHERE team_name='Audi'), 'flag_brazil.png', 'gabriel_bortoleto.png', 2),

('Alexander Albon', 23, (SELECT team_id FROM teams WHERE team_name='Williams'), 'flag_thailand.png', 'alex_albon.png', 0),
('Carlos Sainz', 55, (SELECT team_id FROM teams WHERE team_name='Williams'), 'flag_spain.png', 'carlos_sainz.png', 0),

('Sergio Perez', 11, (SELECT team_id FROM teams WHERE team_name='Cadillac'), 'flag_mexico.png', 'sergio_perez.png', 0),
('Valtteri Bottas', 77, (SELECT team_id FROM teams WHERE team_name='Cadillac'), 'flag_finland.png', 'valtteri_bottas.png', 0);

-- ======================
-- RACES
-- ======================
INSERT INTO races (race_name, image, location, end_date, winner_driver_id, status) VALUES
('Australian Grand Prix', 'australia_gp.png', 'Melbourne', '2026-03-08',
 (SELECT driver_id FROM drivers WHERE driver_name='George Russell'), 'FINISHED'),

('Chinese Grand Prix', 'china_gp.png', 'Shanghai', '2026-03-15',
 (SELECT driver_id FROM drivers WHERE driver_name='Kimi Antonelli'), 'FINISHED'),

('Japanese Grand Prix', 'japan_gp.png', 'Suzuka', '2026-03-29', NULL, 'SCHEDULED'),
('Bahrain Grand Prix', 'bahrain_gp.png', 'Sakhir', '2026-04-12', NULL, 'SCHEDULED'),
('Saudi Arabian Grand Prix', 'saudi_gp.png', 'Jeddah', '2026-04-19', NULL, 'SCHEDULED'),
('Miami Grand Prix', 'miami_gp.png', 'Miami', '2026-05-03', NULL, 'SCHEDULED'),
('Canadian Grand Prix', 'canada_gp.png', 'Montreal', '2026-05-24', NULL, 'SCHEDULED'),
('Monaco Grand Prix', 'monaco_gp.png', 'Monaco', '2026-06-07', NULL, 'SCHEDULED'),
('Spanish Grand Prix', 'barcelona_gp.png', 'Barcelona', '2026-06-14', NULL, 'SCHEDULED'),
('Austrian Grand Prix', 'austria_gp.png', 'Spielberg', '2026-06-28', NULL, 'SCHEDULED'),
('British Grand Prix', 'britain_gp.png', 'Silverstone', '2026-07-05', NULL, 'SCHEDULED'),
('Belgian Grand Prix', 'belgium_gp.png', 'Spa-Francorchamps', '2026-07-19', NULL, 'SCHEDULED'),
('Hungarian Grand Prix', 'hungary_gp.png', 'Budapest', '2026-07-26', NULL, 'SCHEDULED'),
('Dutch Grand Prix', 'netherlands_gp.png', 'Zandvoort', '2026-08-23', NULL, 'SCHEDULED'),
('Italian Grand Prix', 'italy_gp.png', 'Monza', '2026-09-06', NULL, 'SCHEDULED'),
('Madrid Grand Prix', 'madrid_gp.png', 'Madrid', '2026-09-13', NULL, 'SCHEDULED'),
('Azerbaijan Grand Prix', 'azerbaijan_gp.png', 'Baku', '2026-09-27', NULL, 'SCHEDULED'),
('Singapore Grand Prix', 'singapore_gp.png', 'Singapore', '2026-10-11', NULL, 'SCHEDULED'),
('United States Grand Prix', 'usa_gp.png', 'Austin', '2026-10-25', NULL, 'SCHEDULED'),
('Mexico City Grand Prix', 'mexico_gp.png', 'Mexico City', '2026-11-01', NULL, 'SCHEDULED'),
('Sao Paulo Grand Prix', 'brazil_gp.png', 'Sao Paulo', '2026-11-08', NULL, 'SCHEDULED'),
('Las Vegas Grand Prix', 'vegas_gp.png', 'Las Vegas', '2026-11-21', NULL, 'SCHEDULED'),
('Qatar Grand Prix', 'qatar_gp.png', 'Lusail', '2026-11-29', NULL, 'SCHEDULED'),
('Abu Dhabi Grand Prix', 'abudhabi_gp.png', 'Yas Marina', '2026-12-06', NULL, 'SCHEDULED');

-- ======================
-- PREVIOUS TEAMS
-- ======================
INSERT INTO previous_teams (driver_id, team_id) VALUES

-- Verstappen
((SELECT driver_id FROM drivers WHERE driver_name='Max Verstappen'),
 (SELECT team_id FROM teams WHERE team_name='Toro Rosso')),

-- Hadjar
((SELECT driver_id FROM drivers WHERE driver_name='Isack Hadjar'),
 (SELECT team_id FROM teams WHERE team_name='Racing Bulls')),

-- Russell
((SELECT driver_id FROM drivers WHERE driver_name='George Russell'),
 (SELECT team_id FROM teams WHERE team_name='Williams')),

-- Leclerc
((SELECT driver_id FROM drivers WHERE driver_name='Charles Leclerc'),
 (SELECT team_id FROM teams WHERE team_name='Sauber')),

-- Hamilton
((SELECT driver_id FROM drivers WHERE driver_name='Lewis Hamilton'),
 (SELECT team_id FROM teams WHERE team_name='McLaren')),
((SELECT driver_id FROM drivers WHERE driver_name='Lewis Hamilton'),
 (SELECT team_id FROM teams WHERE team_name='Mercedes')),

-- Alonso
((SELECT driver_id FROM drivers WHERE driver_name='Fernando Alonso'),
 (SELECT team_id FROM teams WHERE team_name='Minardi')),
((SELECT driver_id FROM drivers WHERE driver_name='Fernando Alonso'),
 (SELECT team_id FROM teams WHERE team_name='Renault')),
((SELECT driver_id FROM drivers WHERE driver_name='Fernando Alonso'),
 (SELECT team_id FROM teams WHERE team_name='McLaren')),
((SELECT driver_id FROM drivers WHERE driver_name='Fernando Alonso'),
 (SELECT team_id FROM teams WHERE team_name='Ferrari')),
((SELECT driver_id FROM drivers WHERE driver_name='Fernando Alonso'),
 (SELECT team_id FROM teams WHERE team_name='Alpine')),

-- Stroll
((SELECT driver_id FROM drivers WHERE driver_name='Lance Stroll'),
 (SELECT team_id FROM teams WHERE team_name='Williams')),
((SELECT driver_id FROM drivers WHERE driver_name='Lance Stroll'),
 (SELECT team_id FROM teams WHERE team_name='Force India / Racing Point')),

-- Gasly
((SELECT driver_id FROM drivers WHERE driver_name='Pierre Gasly'),
 (SELECT team_id FROM teams WHERE team_name='Toro Rosso')),
((SELECT driver_id FROM drivers WHERE driver_name='Pierre Gasly'),
 (SELECT team_id FROM teams WHERE team_name='Red Bull')),
((SELECT driver_id FROM drivers WHERE driver_name='Pierre Gasly'),
 (SELECT team_id FROM teams WHERE team_name='AlphaTauri'));