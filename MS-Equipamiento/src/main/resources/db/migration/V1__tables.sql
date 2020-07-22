CREATE TABLE Categoria (
    IdCategoria uuid DEFAULT uuid_generate_v4 (),
    Nombre VARCHAR(256) NOT NULL,
    Descripcion TEXT,
    Estado INT NOT NULL,
    PRIMARY KEY (IdCategoria)
);

CREATE TABLE Equipamiento (
    IdEquipamiento uuid DEFAULT uuid_generate_v4 (),
    IdCategoria UUID NOT NULL,
    Nombre VARCHAR(256) NOT NULL,
    Descripcion TEXT,
    Estado INT NOT NULL,
    PRIMARY KEY (IdEquipamiento),
    FOREIGN KEY (IdCategoria) REFERENCES Categoria(IdCategoria)
);


CREATE TABLE Log (
    IdLog SERIAL PRIMARY KEY,
    IdEquipamiento UUID,
    IdCategoria UUID,
    AccionRealizada INT NOT NULL,
    Fecha INT NOT NULL,
    FOREIGN KEY (IdEquipamiento) REFERENCES Equipamiento(IdEquipamiento),
    FOREIGN KEY (IdCategoria) REFERENCES Categoria(IdCategoria)

);

CREATE TABLE Modificaciones (
    IdModificacion SERIAL PRIMARY KEY,
    IdLog INT NOT NULL,
    AntesCambio TEXT NOT NULL,
    DespuesCambio TEXT NOT NULL,
    FOREIGN KEY (IdLog) REFERENCES Log(IdLog)

);

