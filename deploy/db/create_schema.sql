CREATE TABLE IF NOT EXISTS chimiotherapie (
    `id_pec` INT,
    `type_histo` VARCHAR(15) CHARACTER SET utf8,
    `clairance` VARCHAR(5) CHARACTER SET utf8,
    `audiometrie` VARCHAR(25) CHARACTER SET utf8,
    `egfr` VARCHAR(7) CHARACTER SET utf8,
    `alk` VARCHAR(7) CHARACTER SET utf8,
    `braf` VARCHAR(7) CHARACTER SET utf8,
    `ros1` VARCHAR(7) CHARACTER SET utf8,
    `pdl1` VARCHAR(14) CHARACTER SET utf8,
    `ps` VARCHAR(3) CHARACTER SET utf8,
    `tabac` VARCHAR(10) CHARACTER SET utf8,
    `Protocole` VARCHAR(48) CHARACTER SET utf8,
    `id_protocole` INT
);

CREATE TABLE IF NOT EXISTS protocole (
    `id_protocole` INT,
    `protocole` VARCHAR(48) CHARACTER SET utf8,
    `intercure` VARCHAR(2) CHARACTER SET utf8,
    `molecule`  VARCHAR(15) CHARACTER SET utf8,
    `dose` VARCHAR(10) CHARACTER SET utf8,
    `unite` VARCHAR(11) CHARACTER SET utf8,
    `jour_prod` VARCHAR(14) CHARACTER SET utf8,
    `voie_adm` VARCHAR(5) CHARACTER SET utf8
);

CREATE TABLE IF NOT EXISTS prescription (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `molecule` VARCHAR(255) NOT NULL,
    `dose` INT NOT NULL,
    `unite` VARCHAR(255) NOT NULL,
    `poids` DOUBLE NOT NULL,
    `taille` DOUBLE NOT NULL,
    `age` DOUBLE NOT NULL,
    `formule` VARCHAR(255) NOT NULL,
    `sexe` VARCHAR(255) NOT NULL,
    `clcr` DOUBLE NOT NULL,
    `creatinine` DOUBLE NOT NULL,
    `jour_prod` DATE NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_molecule_dose_unite (molecule, dose, unite)
);

