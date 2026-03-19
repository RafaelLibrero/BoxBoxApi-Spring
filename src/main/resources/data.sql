-- TEAMS
INSERT INTO teams (team_id, team_name, logo, points, active) VALUES
(1, 'Red Bull', 'redbull_logo.png', 8, true),
(2, 'Mercedes', 'mercedes_logo.png', 43, true),
(3, 'Ferrari', 'ferrari_logo.png', 27, true),
(4, 'McLaren', 'mclaren_logo.png', 10, true),
(5, 'Aston Martin', 'astonmartin_logo.png', 0, true),
(6, 'Alpine', 'alpine_logo.png', 1, true),
(7, 'Haas', 'haas_logo.png', 6, true),
(8, 'Racing Bulls', 'racingbulls_logo.png', 4, true),
(9, 'Audi', 'audi_logo.png', 2, true),
(10, 'Williams', 'williams_logo.png', 0, true),
(11, 'Cadillac', 'cadillac_logo.png', 0, true);

-- HISTORIC TEAMS
INSERT INTO teams (team_id, team_name, logo, points, active) VALUES
(12, 'Toro Rosso', 'tororosso.png', 0, false),
(13, 'Minardi', 'minardi.png', 0, false),
(14, 'Renault', 'renault.png', 0, false),
(15, 'Force India / Racing Point', 'forceindia.png', 0, false),
(16, 'Sauber', 'sauber.png', 0, false),
(17, 'Manor', 'manor.png', 0, false),
(18, 'AlphaTauri', 'alphatauri.png', 0, false),
(19, 'Alfa Romeo', 'alfaromeo.png', 0, false);

-- DRIVERS
INSERT INTO drivers (driver_name, car_number, team_id, flag, imagen, points) VALUES
('Max Verstappen', 1, 1, 'flag_netherlands.png', 'max_verstappen.png', 8),
('Isack Hadjar', 6, 1, 'flag_france.png', 'isack_hadjar.png', 0),

('George Russell', 63, 2, 'flag_uk.png', 'george_russell.png', 25),
('Kimi Antonelli', 12, 2, 'flag_italy.png', 'kimi_antonelli.png', 18),

('Charles Leclerc', 16, 3, 'flag_monaco.png', 'charles_leclerc.png', 15),
('Lewis Hamilton', 44, 3, 'flag_uk.png', 'lewis_hamilton.png', 12),

('Lando Norris', 4, 4, 'flag_uk.png', 'lando_norris.png', 10),
('Oscar Piastri', 81, 4, 'flag_australia.png', 'oscar_piastri.png', 0),

('Fernando Alonso', 14, 5, 'flag_spain.png', 'fernando_alonso.png', 0),
('Lance Stroll', 18, 5, 'flag_canada.png', 'lance_stroll.png', 0),

('Pierre Gasly', 10, 6, 'flag_france.png', 'pierre_gasly.png', 1),
('Franco Colapinto', 43, 6, 'flag_argentina.png', 'franco_colapinto.png', 0),

('Esteban Ocon', 31, 7, 'flag_france.png', 'esteban_ocon.png', 0),
('Oliver Bearman', 50, 7, 'flag_uk.png', 'ollie_bearman.png', 6),

('Liam Lawson', 30, 8, 'flag_new_zealand.png', 'liam_lawson.png', 0),
('Arvid Lindblad', 41, 8, 'flag_uk.png', 'arvid_lindblad.png', 4),

('Nico Hulkenberg', 27, 9, 'flag_germany.png', 'nico_hulkenberg.png', 0),
('Gabriel Bortoleto', 5, 9, 'flag_brazil.png', 'gabriel_bortoleto.png', 2),

('Alexander Albon', 23, 10, 'flag_thailand.png', 'alex_albon.png', 0),
('Carlos Sainz', 55, 10, 'flag_spain.png', 'carlos_sainz.png', 0),

('Sergio Perez', 11, 11, 'flag_mexico.png', 'sergio_perez.png', 0),
('Valtteri Bottas', 77, 11, 'flag_finland.png', 'valtteri_bottas.png', 0);

-- RACES
INSERT INTO races (race_name, image, location, end_date, winner_driver_id, status) VALUES
('Australian Grand Prix', 'australia_gp.png', 'Melbourne', '2026-03-08', 3, 'FINISHED'), -- Russell
('Chinese Grand Prix', 'china_gp.png', 'Shanghai', '2026-03-15', 4, 'FINISHED'), -- Antonelli
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

-- PREVIOUS TEAMS

-- Max Verstappen
INSERT INTO previous_teams (driver_id, team_id) VALUES (1, 12);

-- Isack Hadjar
INSERT INTO previous_teams (driver_id, team_id) VALUES (2, 8);

-- George Russell
INSERT INTO previous_teams (driver_id, team_id) VALUES (3, 10);

-- Charles Leclerc
INSERT INTO previous_teams (driver_id, team_id) VALUES (5, 16);

-- Lewis Hamilton
INSERT INTO previous_teams (driver_id, team_id) VALUES (6, 4), (6, 2);

-- Fernando Alonso
INSERT INTO previous_teams (driver_id, team_id) VALUES (9, 13), (9, 14), (9, 4), (9, 3), (9, 6);

-- Lance Stroll
INSERT INTO previous_teams (driver_id, team_id) VALUES (10, 10), (10, 15);

-- Pierre Gasly
INSERT INTO previous_teams (driver_id, team_id) VALUES (11, 12), (11, 1), (11, 18);

--Franco Colapinto
INSERT INTO previous_teams (driver_id, team_id) VALUES (12, 10);

-- Esteban Ocon
INSERT INTO previous_teams (driver_id, team_id) VALUES (13, 17), (13, 15), (13, 6);

--Liam Lawson
INSERT INTO previous_teams (driver_id, team_id) VALUES (15, 1);

-- Nico Hülkenberg
INSERT INTO previous_teams (driver_id, team_id) VALUES (17, 10), (17, 15), (17, 16), (17, 14), (17, 7);

-- Gabriel Bortoleto
INSERT INTO previous_teams (driver_id, team_id) VALUES (18, 16);

-- Alexander Albon
INSERT INTO previous_teams (driver_id, team_id) VALUES (19, 12), (19, 1);

-- Carlos Sainz Jr.
INSERT INTO previous_teams (driver_id, team_id) VALUES (20, 12), (20, 14), (20, 4), (20, 3);

-- Sergio Pérez
INSERT INTO previous_teams (driver_id, team_id) VALUES (21, 16), (21, 15), (21, 1);

-- Valtteri Bottas
INSERT INTO previous_teams (driver_id, team_id) VALUES (22, 10), (22, 2), (22, 19), (22, 16);