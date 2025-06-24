DROP DATABASE IF EXISTS taprog3;
CREATE DATABASE taprog3;
USE taprog3;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP TABLE IF EXISTS actividad;
DROP TABLE IF EXISTS aplicacion;
DROP TABLE IF EXISTS configuracion;
DROP TABLE IF EXISTS disp_app;
DROP TABLE IF EXISTS disp_conf;
DROP TABLE IF EXISTS disp_firm;
DROP TABLE IF EXISTS dispositivo;
DROP TABLE IF EXISTS firmware;
DROP TABLE IF EXISTS grupo;
DROP TABLE IF EXISTS metricauso;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS usuario;

-- Tabla rol
CREATE TABLE rol (
  rolid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50),
  descripcion VARCHAR(254),
  activo CHAR(1) DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla usuario
CREATE TABLE usuario (
  usuarioid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50),
  apellido VARCHAR(50),
  correo VARCHAR(254),
  contrasena VARCHAR(254),
<<<<<<< HEAD
  fechacreacion TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
=======
  fechacreacion DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
  rol_rolid INT NOT NULL,
  activo CHAR(1) DEFAULT 's',
  CONSTRAINT usuario_rol_fk FOREIGN KEY (rol_rolid) REFERENCES rol(rolid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla grupo
CREATE TABLE grupo (
  grupoid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80),
  descripcion VARCHAR(254),
<<<<<<< HEAD
  fechacreacion TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
=======
  fechacreacion DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
  ubicacion VARCHAR(254),
  activo CHAR(1) DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla dispositivo
CREATE TABLE dispositivo (
  dispositivoid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(90),
  modelo VARCHAR(254),
  numeroserie VARCHAR(254),
<<<<<<< HEAD
  fecharegistro TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  ubicacion VARCHAR(254),
  nivelbateria INT,
  ultimaconexion TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
=======
  fecharegistro DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  ubicacion VARCHAR(254),
  nivelbateria INT,
  ultimaconexion DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
  estado_conexion ENUM(
    'CONECTADO',
    'DESCONECTADO',
    'EN_USO',
    'EN_MANTENIMIENTO'
  ),
  grupo_grupoid INT NOT NULL,
  activo CHAR(1) DEFAULT 's',
  CONSTRAINT dispositivo_grupo_fk FOREIGN KEY (grupo_grupoid) REFERENCES grupo(grupoid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla configuracion (relación separada en disp_conf)
CREATE TABLE configuracion (
  configuracionid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(254),
  descripcion VARCHAR(254),
<<<<<<< HEAD
  fechacreacion TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
=======
  fechacreacion DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
  valor VARCHAR(254),
  tipo ENUM(
    'AUDIO',
    'VIDEO',
    'CONTROL',
    'RED',
    'SEGURIDAD',
    'SISTEMA'
  ),
  activo CHAR(1) DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla firmware (sin FK directa a dispositivo)
CREATE TABLE firmware (
  firmwareid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(254),
  version VARCHAR(254),
  descripcion VARCHAR(254),
  activo CHAR(1) DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla aplicacion
CREATE TABLE aplicacion (
  aplicacionid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  version VARCHAR(254),
  desarrollador VARCHAR(254),
  descripcion VARCHAR(254),
  tamanio DOUBLE(10,2),
  categoria ENUM(
    'EDUCATIVA',
    'ENTRETENIMIENTO',
    'TERAPEUTICA',
    'ENTRENAMIENTO',
    'PRODUCTIVIDAD',
    'SIMULACION',
    'MULTIMEDIA'
  ),
  activo CHAR(1) DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla disp_app (relación muchos a muchos entre dispositivo y aplicacion)
CREATE TABLE disp_app (
  dispositivo_dispositivoid INT NOT NULL,
  aplicacion_aplicacionid INT NOT NULL,
  PRIMARY KEY (dispositivo_dispositivoid, aplicacion_aplicacionid),
  FOREIGN KEY (dispositivo_dispositivoid) REFERENCES dispositivo(dispositivoid),
  FOREIGN KEY (aplicacion_aplicacionid) REFERENCES aplicacion(aplicacionid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla disp_firm (relación muchos a muchos entre dispositivo y firmware)
CREATE TABLE disp_firm (
  dispositivo_dispositivoid INT NOT NULL,
  firmware_firmwareid INT NOT NULL,
  PRIMARY KEY (dispositivo_dispositivoid, firmware_firmwareid),
  FOREIGN KEY (dispositivo_dispositivoid) REFERENCES dispositivo(dispositivoid),
  FOREIGN KEY (firmware_firmwareid) REFERENCES firmware(firmwareid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla disp_conf (relación muchos a muchos entre dispositivo y configuracion)
CREATE TABLE disp_conf (
  configuracion_configuracionid INT NOT NULL,
  dispositivo_dispositivoid INT NOT NULL,
  PRIMARY KEY (configuracion_configuracionid, dispositivo_dispositivoid),
  INDEX disp_conf_dispositivo_fk (dispositivo_dispositivoid ASC),
  CONSTRAINT disp_conf_configuracion_fk
    FOREIGN KEY (configuracion_configuracionid) REFERENCES configuracion(configuracionid),
  CONSTRAINT disp_conf_dispositivo_fk
    FOREIGN KEY (dispositivo_dispositivoid) REFERENCES dispositivo(dispositivoid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla metricauso
CREATE TABLE metricauso (
  metricaid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
<<<<<<< HEAD
  fecharegistro TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
=======
  fecharegistro DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
  tiempousominutos INT,
  nivelbateriainicial INT,
  nivelbateriafinal INT,
  usuario_usuarioid INT NOT NULL,
  dispositivo_dispositivoid INT NOT NULL,
  activo CHAR(1) DEFAULT 's',
  FOREIGN KEY (usuario_usuarioid) REFERENCES usuario(usuarioid),
  FOREIGN KEY (dispositivo_dispositivoid) REFERENCES dispositivo(dispositivoid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla actividad
CREATE TABLE actividad (
  actividadid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
<<<<<<< HEAD
  fechahora TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
=======
  fechahora DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
>>>>>>> 72e72ce (Ignorar archivos temporales de Visual Studio y build)
  descripcion VARCHAR(254),
  detallestecnicos VARCHAR(254),
  tipo ENUM(
    'INICIO_SESION',
    'CIERRE_SESION',
    'REGISTRO_DISPOSITIVO',
    'ACTUALIZACION_FIRMWARE',
    'INSTALACION_APLICACION',
    'DESINSTALACION_APLICACION',
    'CONFIGURACION_DISPOSITIVO',
    'CAMBIO_ESTADO_DISPOSITIVO',
    'ASIGNACION_GRUPO',
    'CREACION_USUARIO',
    'MODIFICACION_USUARIO',
    'CREACION_GRUPO',
    'MODIFICACION_GRUPO'
  ),
  usuario_usuarioid INT NOT NULL,
  dispositivo_dispositivoid INT NOT NULL,
  activo CHAR(1) DEFAULT 's',
  FOREIGN KEY (usuario_usuarioid) REFERENCES usuario(usuarioid),
  FOREIGN KEY (dispositivo_dispositivoid) REFERENCES dispositivo(dispositivoid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Restaurar valores originales
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
