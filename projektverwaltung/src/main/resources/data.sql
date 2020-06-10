
-- Rollenzuweisung
REPLACE INTO `roles` VALUES (1,'ADMIN'), (2,'MEMBER');

-- Testdaten - 2 Teamleiter - 3 Mitarbeiter
REPLACE INTO `users` VALUES (1,1, "Heike.Neustadt@thd-projektverwaltung.de", "Teamleiter","Neustadt", 6, "Heike", "$2a$10$dRk99Fs/pKlMVCKHREknFO14J2tdCbqT4sGA.PdnYR9yOAHNPBnSO", "hneustadt", 100),
                            (2,1, "Stephanie.Braun@projektverwaltung.de", "Projektleiter","Braun", 6, "Stephanie", "$2a$10$dRk99Fs/pKlMVCKHREknFO14J2tdCbqT4sGA.PdnYR9yOAHNPBnSO", "sbraun", 100),
                            (3,1, "Benjamin.Zweig@projektverwaltung.de", "Entwickler","Benjamin", 6, "Zweig", "$2a$10$dRk99Fs/pKlMVCKHREknFO14J2tdCbqT4sGA.PdnYR9yOAHNPBnSO", "bzweig", 100),
                            (4,1, "Marko.Junker@projektverwaltung.de", "SAP-Berater","Junker", 6, "Marko", "$2a$10$dRk99Fs/pKlMVCKHREknFO14J2tdCbqT4sGA.PdnYR9yOAHNPBnSO", "mjunker", 100),
                            (5,1, "Dirk.Decker@projektverwaltung.de", "Solution Architekt","Decker", 6, "Dirk", "$2a$10$dRk99Fs/pKlMVCKHREknFO14J2tdCbqT4sGA.PdnYR9yOAHNPBnSO", "ddecker", 100);

-- Rollen - 2 Teamleiter - 3 Mitarbeiter
REPLACE INTO `user_role` VALUES (1,1),
                                (2,1),
                                (3,2),
                                (4,2),
                                (5,2);

-- Projektdaten
REPLACE INTO `customers` VALUES (1,"BMW"),
                                (2,"AUDI"),
                                (3, "ZF");

-- Projektdaten
REPLACE INTO `projects` VALUES (1,"SAP-Rollout", 15000, 25, 1,1),
                               (2,"SAP-Customizing", 5000, 15, 2,2),
                               (3, "HR-Portal Einführung", 65000, 100, 3,1);
