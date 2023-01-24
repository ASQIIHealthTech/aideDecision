CREATE TABLE IF NOT EXISTS Chimiotherapie (
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