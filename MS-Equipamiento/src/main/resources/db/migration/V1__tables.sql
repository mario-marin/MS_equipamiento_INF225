CREATE TABLE categoria (
    idcategoria uuid DEFAULT uuid_generate_v4 (),
    nombre VARCHAR(256) NOT NULL,
    descripcion TEXT,
    estado INT NOT NULL,
    PRIMARY KEY (idcategoria)
);

CREATE TABLE equipamiento (
    idequipamiento uuid DEFAULT uuid_generate_v4 (),
    idcategoria UUID NOT NULL,
    nombre VARCHAR(256) NOT NULL,
    descripcion TEXT,
    estado INT NOT NULL,
    PRIMARY KEY (idequipamiento),
    FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria)
);


CREATE TABLE log (
    idlog SERIAL PRIMARY KEY,
    idequipamiento UUID,
    idcategoria UUID,
    accionrealizada INT NOT NULL,
    fecha INT NOT NULL,
    FOREIGN KEY (idequipamiento) REFERENCES equipamiento(idequipamiento),
    FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria)

);

CREATE TABLE modificaciones (
    idmodificacion SERIAL PRIMARY KEY,
    idlog INT NOT NULL,
    antescambio TEXT NOT NULL,
    despuescambio TEXT NOT NULL,
    FOREIGN KEY (idlog) REFERENCES log(idlog)

);

