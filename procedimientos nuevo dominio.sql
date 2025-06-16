USE taprog3;

DELIMITER //

-- Drops para las tablas 
DROP PROCEDURE IF EXISTS insertar_usuario;
DROP PROCEDURE IF EXISTS actualizar_usuario;
DROP PROCEDURE IF EXISTS eliminar_usuario;
DROP PROCEDURE IF EXISTS listar_usuarios;
DROP PROCEDURE IF EXISTS obtener_usuario;

DROP PROCEDURE IF EXISTS insertar_grupo;
DROP PROCEDURE IF EXISTS actualizar_grupo;
DROP PROCEDURE IF EXISTS eliminar_grupo;
DROP PROCEDURE IF EXISTS listar_grupo;
DROP PROCEDURE IF EXISTS obtener_grupo;

DROP PROCEDURE IF EXISTS insertar_dispositivo;
DROP PROCEDURE IF EXISTS actualizar_dispositivo;
DROP PROCEDURE IF EXISTS eliminar_dispositivo;
DROP PROCEDURE IF EXISTS listar_dispositivo;
DROP PROCEDURE IF EXISTS obtener_dispositivo;

DROP PROCEDURE IF EXISTS insertar_actividad;
DROP PROCEDURE IF EXISTS actualizar_actividad;
DROP PROCEDURE IF EXISTS eliminar_actividad;
DROP PROCEDURE IF EXISTS listar_actividad;
DROP PROCEDURE IF EXISTS obtener_actividad;

DROP PROCEDURE IF EXISTS insertar_aplicacion;
DROP PROCEDURE IF EXISTS actualizar_aplicacion;
DROP PROCEDURE IF EXISTS eliminar_aplicacion;
DROP PROCEDURE IF EXISTS listar_aplicacion;
DROP PROCEDURE IF EXISTS obtener_aplicacion;

DROP PROCEDURE IF EXISTS insertar_configuracion;
DROP PROCEDURE IF EXISTS actualizar_configuracion;
DROP PROCEDURE IF EXISTS eliminar_configuracion;
DROP PROCEDURE IF EXISTS listar_configuracion;
DROP PROCEDURE IF EXISTS obtener_configuracion;

DROP PROCEDURE IF EXISTS insertar_firmware;
DROP PROCEDURE IF EXISTS actualizar_firmware;
DROP PROCEDURE IF EXISTS eliminar_firmware;
DROP PROCEDURE IF EXISTS listar_firmware;
DROP PROCEDURE IF EXISTS obtener_firmware;

DROP PROCEDURE IF EXISTS insertar_metricauso;
DROP PROCEDURE IF EXISTS actualizar_metricauso;
DROP PROCEDURE IF EXISTS eliminar_metricauso;
DROP PROCEDURE IF EXISTS listar_metricauso;
DROP PROCEDURE IF EXISTS obtener_metricauso;

DROP PROCEDURE IF EXISTS insertar_rol;
DROP PROCEDURE IF EXISTS actualizar_rol;
DROP PROCEDURE IF EXISTS eliminar_rol;
DROP PROCEDURE IF EXISTS listar_rol;
DROP PROCEDURE IF EXISTS obtener_rol;

DELIMITER ;
-- Procedimientos para la tabla "usuario"

DELIMITER //

-- Insertar nuevo usuario
CREATE PROCEDURE insertar_usuario(
    OUT p_usuarioid INT,
    IN p_nombre VARCHAR(50),
    IN p_apellido VARCHAR(50),
    IN p_correo VARCHAR(254),
    IN p_contrasena VARCHAR(254),
    IN p_rol_rolid INT
)
BEGIN
    INSERT INTO usuario(nombre, apellido, correo, contrasena, rol_rolid)
    VALUES (p_nombre, p_apellido, p_correo, p_contrasena, p_rol_rolid);

    SET p_usuarioid = LAST_INSERT_ID();
END;
//

-- Actualizar datos de usuario
CREATE PROCEDURE actualizar_usuario(
    IN p_usuarioid INT,
    IN p_nombre VARCHAR(50),
    IN p_apellido VARCHAR(50),
    IN p_correo VARCHAR(254),
    IN p_contrasena VARCHAR(254),
    IN p_rol_rolid INT
)
BEGIN
    UPDATE usuario
    SET nombre = p_nombre,
        apellido = p_apellido,
        correo = p_correo,
        contrasena = p_contrasena,
        rol_rolid = p_rol_rolid
    WHERE usuarioid = p_usuarioid;
END;
//

-- Eliminar usuario (eliminación lógica)
CREATE PROCEDURE eliminar_usuario(
    IN p_usuarioid INT
)
BEGIN
    UPDATE usuario
    SET activo = 'n'
    WHERE usuarioid = p_usuarioid;
END;
//

-- Listar usuarios activos
CREATE PROCEDURE listar_usuarios()
BEGIN
    SELECT *
    FROM usuario
    WHERE activo = 's';
END;
//

-- Obtener un usuario por su ID
CREATE PROCEDURE obtener_usuario(
    IN p_usuarioid INT
)
BEGIN
    SELECT *
    FROM usuario
    WHERE usuarioid = p_usuarioid AND activo = 's';
END;
//

DELIMITER ;


-- Procedimientos para la tabla "grupo"


DELIMITER //

-- Insertar nuevo grupo
CREATE PROCEDURE insertar_grupo(
    OUT p_grupoid INT,
    IN p_nombre VARCHAR(80),
    IN p_descripcion VARCHAR(254),
    IN p_ubicacion VARCHAR(254)
)
BEGIN
    INSERT INTO grupo(nombre, descripcion, ubicacion)
    VALUES (p_nombre, p_descripcion, p_ubicacion);

    SET p_grupoid = LAST_INSERT_ID();
END;
//

-- Actualizar datos del grupo
CREATE PROCEDURE actualizar_grupo(
    IN p_grupoid INT,
    IN p_nombre VARCHAR(80),
    IN p_descripcion VARCHAR(254),
    IN p_ubicacion VARCHAR(254)
)
BEGIN
    UPDATE grupo
    SET nombre = p_nombre,
        descripcion = p_descripcion,
        ubicacion = p_ubicacion
    WHERE grupoid = p_grupoid;
END;
//

-- Eliminar grupo (eliminación lógica)
CREATE PROCEDURE eliminar_grupo(
    IN p_grupoid INT
)
BEGIN
    UPDATE grupo
    SET activo = 'n'
    WHERE grupoid = p_grupoid;
END;
//

-- Listar grupos activos
CREATE PROCEDURE listar_grupo()
BEGIN
    SELECT *
    FROM grupo
    WHERE activo = 's';
END;
//

-- Obtener un grupo por su ID
CREATE PROCEDURE obtener_grupo(
    IN p_grupoid INT
)
BEGIN
    SELECT *
    FROM grupo
    WHERE grupoid = p_grupoid AND activo = 's';
END;
//

DELIMITER ;


-- Procedimientos para la tabla "dispositivo"

DELIMITER //

-- Insertar nuevo dispositivo
CREATE PROCEDURE insertar_dispositivo(
    OUT p_dispositivoid INT,
    IN p_nombre VARCHAR(90),
    IN p_modelo VARCHAR(254),
    IN p_numeroserie VARCHAR(254),
    IN p_ubicacion VARCHAR(254),
    IN p_nivelbateria INT,
    IN p_estado_conexion ENUM('CONECTADO', 'DESCONECTADO', 'EN_USO', 'EN_MANTENIMIENTO'),
    IN p_grupo_grupoid INT
)
BEGIN
    INSERT INTO dispositivo(
        nombre, modelo, numeroserie, ubicacion, nivelbateria
        , estado_conexion, grupo_grupoid
    )
    VALUES (
        p_nombre, p_modelo, p_numeroserie, p_ubicacion,
        p_nivelbateria, p_estado_conexion, p_grupo_grupoid
    );

    SET p_dispositivoid = LAST_INSERT_ID();
END;
//

-- Actualizar dispositivo
CREATE PROCEDURE actualizar_dispositivo(
    IN p_dispositivoid INT,
    IN p_nombre VARCHAR(90),
    IN p_modelo VARCHAR(254),
    IN p_numeroserie VARCHAR(254),
    IN p_ubicacion VARCHAR(254),
    IN p_nivelbateria INT,
    IN p_ultimaconexion TIMESTAMP,
    IN p_estado_conexion ENUM('CONECTADO', 'DESCONECTADO', 'EN_USO', 'EN_MANTENIMIENTO'),
    IN p_grupo_grupoid INT
)
BEGIN
    UPDATE dispositivo
    SET nombre = p_nombre,
        modelo = p_modelo,
        numeroserie = p_numeroserie,
        ubicacion = p_ubicacion,
        nivelbateria = p_nivelbateria,
        ultimaconexion = p_ultimaconexion,
        estado_conexion = p_estado_conexion,
        grupo_grupoid = p_grupo_grupoid
    WHERE dispositivoid = p_dispositivoid;
END;
//

-- Eliminar dispositivo (lógica)
CREATE PROCEDURE eliminar_dispositivo(
    IN p_dispositivoid INT
)
BEGIN
    UPDATE dispositivo
    SET activo = 'n'
    WHERE dispositivoid = p_dispositivoid;
END;
//

-- Listar dispositivos activos
CREATE PROCEDURE listar_dispositivo()
BEGIN
    SELECT *
    FROM dispositivo
    WHERE activo = 's';
END;
//

-- Obtener un dispositivo por su ID
CREATE PROCEDURE obtener_dispositivo(
    IN p_dispositivoid INT
)
BEGIN
    SELECT *
    FROM dispositivo
    WHERE dispositivoid = p_dispositivoid AND activo = 's';
END;
//

DELIMITER ;

-- Procedimientos para la tabla "actividad"

DELIMITER //

-- Insertar nueva actividad
CREATE PROCEDURE insertar_actividad(
    OUT p_actividadid INT,
    IN p_descripcion VARCHAR(254),
    IN p_detallestecnicos VARCHAR(254),
    IN p_tipo ENUM(
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
    IN p_usuario_usuarioid INT,
    IN p_dispositivo_dispositivoid INT
)
BEGIN
    INSERT INTO actividad(
        descripcion, detallestecnicos, tipo,
        usuario_usuarioid, dispositivo_dispositivoid
    )
    VALUES (
        p_descripcion, p_detallestecnicos, p_tipo,
        p_usuario_usuarioid, p_dispositivo_dispositivoid
    );

    SET p_actividadid = LAST_INSERT_ID();
END;
//

-- Actualizar actividad
CREATE PROCEDURE actualizar_actividad(
    IN p_actividadid INT,
    IN p_descripcion VARCHAR(254),
    IN p_detallestecnicos VARCHAR(254),
    IN p_tipo ENUM(
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
    IN p_usuario_usuarioid INT,
    IN p_dispositivo_dispositivoid INT
)
BEGIN
    UPDATE actividad
    SET descripcion = p_descripcion,
        detallestecnicos = p_detallestecnicos,
        tipo = p_tipo,
        usuario_usuarioid = p_usuario_usuarioid,
        dispositivo_dispositivoid = p_dispositivo_dispositivoid
    WHERE actividadid = p_actividadid;
END;
//

-- Eliminar actividad (lógica)
CREATE PROCEDURE eliminar_actividad(
    IN p_actividadid INT
)
BEGIN
    UPDATE actividad
    SET activo = 'n'
    WHERE actividadid = p_actividadid;
END;
//

-- Listar actividades activas
CREATE PROCEDURE listar_actividad()
BEGIN
    SELECT *
    FROM actividad
    WHERE activo = 's';
END;
//

-- Obtener una actividad por su ID
CREATE PROCEDURE obtener_actividad(
    IN p_actividadid INT
)
BEGIN
    SELECT *
    FROM actividad
    WHERE actividadid = p_actividadid AND activo = 's';
END;
//

DELIMITER ;

-- Procedimientos para la tabla "aplicacion"

DELIMITER //

-- Insertar nueva aplicación
CREATE PROCEDURE insertar_aplicacion(
    OUT p_aplicacionid INT,
    IN p_nombre VARCHAR(100),
    IN p_version VARCHAR(254),
    IN p_desarrollador VARCHAR(254),
    IN p_descripcion VARCHAR(254),
    IN p_tamanio DOUBLE(10,2),
    IN p_categoria ENUM(
        'EDUCATIVA',
        'ENTRETENIMIENTO',
        'TERAPEUTICA',
        'ENTRENAMIENTO',
        'PRODUCTIVIDAD',
        'SIMULACION',
        'MULTIMEDIA'
    )
)
BEGIN
    INSERT INTO aplicacion(
        nombre, version, desarrollador, descripcion, tamanio, categoria
    )
    VALUES (
        p_nombre, p_version, p_desarrollador, p_descripcion, p_tamanio, p_categoria
    );

    SET p_aplicacionid = LAST_INSERT_ID();
END;
//

-- Actualizar aplicación
CREATE PROCEDURE actualizar_aplicacion(
    IN p_aplicacionid INT,
    IN p_nombre VARCHAR(100),
    IN p_version VARCHAR(254),
    IN p_desarrollador VARCHAR(254),
    IN p_descripcion VARCHAR(254),
    IN p_tamanio DOUBLE(10,2),
    IN p_categoria ENUM(
        'EDUCATIVA',
        'ENTRETENIMIENTO',
        'TERAPEUTICA',
        'ENTRENAMIENTO',
        'PRODUCTIVIDAD',
        'SIMULACION',
        'MULTIMEDIA'
    )
)
BEGIN
    UPDATE aplicacion
    SET nombre = p_nombre,
        version = p_version,
        desarrollador = p_desarrollador,
        descripcion = p_descripcion,
        tamanio = p_tamanio,
        categoria = p_categoria
    WHERE aplicacionid = p_aplicacionid;
END;
//

-- Eliminar aplicación (lógica)
CREATE PROCEDURE eliminar_aplicacion(
    IN p_aplicacionid INT
)
BEGIN
    UPDATE aplicacion
    SET activo = 'n'
    WHERE aplicacionid = p_aplicacionid;
END;
//

-- Listar aplicaciones activas
CREATE PROCEDURE listar_aplicacion()
BEGIN
    SELECT *
    FROM aplicacion
    WHERE activo = 's';
END;
//

-- Obtener una aplicación por su ID
CREATE PROCEDURE obtener_aplicacion(
    IN p_aplicacionid INT
)
BEGIN
    SELECT *
    FROM aplicacion
    WHERE aplicacionid = p_aplicacionid AND activo = 's';
END;
//

DELIMITER ;

-- Procedimientos para la tabla "configuracion"
DELIMITER //

-- Insertar nueva configuración
CREATE PROCEDURE insertar_configuracion(
    OUT p_configuracionid INT,
    IN p_nombre VARCHAR(254),
    IN p_descripcion VARCHAR(254),
    IN p_valor VARCHAR(254),
    IN p_tipo ENUM(
        'AUDIO',
        'VIDEO',
        'CONTROL',
        'RED',
        'SEGURIDAD',
        'SISTEMA'
    )
)
BEGIN
    INSERT INTO configuracion(
        nombre, descripcion, valor, tipo
    )
    VALUES (
        p_nombre, p_descripcion, p_valor, p_tipo
    );

    SET p_configuracionid = LAST_INSERT_ID();
END;
//

-- Actualizar configuración
CREATE PROCEDURE actualizar_configuracion(
    IN p_configuracionid INT,
    IN p_nombre VARCHAR(254),
    IN p_descripcion VARCHAR(254),
    IN p_valor VARCHAR(254),
    IN p_tipo ENUM(
        'AUDIO',
        'VIDEO',
        'CONTROL',
        'RED',
        'SEGURIDAD',
        'SISTEMA'
    )
)
BEGIN
    UPDATE configuracion
    SET nombre = p_nombre,
        descripcion = p_descripcion,
        valor = p_valor,
        tipo = p_tipo
    WHERE configuracionid = p_configuracionid;
END;
//

-- Eliminar configuración (lógica)
CREATE PROCEDURE eliminar_configuracion(
    IN p_configuracionid INT
)
BEGIN
    UPDATE configuracion
    SET activo = 'n'
    WHERE configuracionid = p_configuracionid;
END;
//

-- Listar configuraciones activas
CREATE PROCEDURE listar_configuracion()
BEGIN
    SELECT *
    FROM configuracion
    WHERE activo = 's';
END;
//

-- Obtener una configuración por su ID
CREATE PROCEDURE obtener_configuracion(
    IN p_configuracionid INT
)
BEGIN
    SELECT *
    FROM configuracion
    WHERE configuracionid = p_configuracionid AND activo = 's';
END;
//

DELIMITER ;

-- Procedimientos para la tabla "firmware"

DELIMITER //

-- Insertar nuevo firmware
CREATE PROCEDURE insertar_firmware(
    OUT p_firmwareid INT,
    IN p_nombre VARCHAR(254),
    IN p_version VARCHAR(254),
    IN p_descripcion VARCHAR(254)
)
BEGIN
    INSERT INTO firmware(nombre, version, descripcion)
    VALUES (p_nombre, p_version, p_descripcion);

    SET p_firmwareid = LAST_INSERT_ID();
END;
//

-- Actualizar firmware
CREATE PROCEDURE actualizar_firmware(
    IN p_firmwareid INT,
    IN p_nombre VARCHAR(254),
    IN p_version VARCHAR(254),
    IN p_descripcion VARCHAR(254)
)
BEGIN
    UPDATE firmware
    SET nombre = p_nombre,
        version = p_version,
        descripcion = p_descripcion
    WHERE firmwareid = p_firmwareid;
END;
//

-- Eliminar firmware (lógica)
CREATE PROCEDURE eliminar_firmware(
    IN p_firmwareid INT
)
BEGIN
    UPDATE firmware
    SET activo = 'n'
    WHERE firmwareid = p_firmwareid;
END;
//

-- Listar firmwares activos
CREATE PROCEDURE listar_firmware()
BEGIN
    SELECT *
    FROM firmware
    WHERE activo = 's';
END;
//

-- Obtener firmware por ID
CREATE PROCEDURE obtener_firmware(
    IN p_firmwareid INT
)
BEGIN
    SELECT *
    FROM firmware
    WHERE firmwareid = p_firmwareid AND activo = 's';
END;
//

DELIMITER ;

-- Procedimientos para la tabla "metricauso"

DELIMITER //

-- Insertar nueva métrica de uso
CREATE PROCEDURE insertar_metricauso(
    OUT p_metricaid INT,
    IN p_tiempousominutos INT,
    IN p_nivelbateriainicial INT,
    IN p_nivelbateriafinal INT,
    IN p_usuario_usuarioid INT,
    IN p_dispositivo_dispositivoid INT
)
BEGIN
    INSERT INTO metricauso(
        tiempousominutos, nivelbateriainicial,
        nivelbateriafinal, usuario_usuarioid, dispositivo_dispositivoid
    )
    VALUES (
        p_tiempousominutos, p_nivelbateriainicial,
        p_nivelbateriafinal, p_usuario_usuarioid, p_dispositivo_dispositivoid
    );

    SET p_metricaid = LAST_INSERT_ID();
END;
//

-- Actualizar métrica de uso
CREATE PROCEDURE actualizar_metricauso(
    IN p_metricaid INT,
    IN p_tiempousominutos INT,
    IN p_nivelbateriainicial INT,
    IN p_nivelbateriafinal INT,
    IN p_usuario_usuarioid INT,
    IN p_dispositivo_dispositivoid INT
)
BEGIN
    UPDATE metricauso
    SET tiempousominutos = p_tiempousominutos,
        nivelbateriainicial = p_nivelbateriainicial,
        nivelbateriafinal = p_nivelbateriafinal,
        usuario_usuarioid = p_usuario_usuarioid,
        dispositivo_dispositivoid = p_dispositivo_dispositivoid
    WHERE metricaid = p_metricaid;
END;
//

-- Eliminar métrica de uso (lógica)
CREATE PROCEDURE eliminar_metricauso(
    IN p_metricaid INT
)
BEGIN
    UPDATE metricauso
    SET activo = 'n'
    WHERE metricaid = p_metricaid;
END;
//

-- Listar métricas de uso activas
CREATE PROCEDURE listar_metricauso()
BEGIN
    SELECT *
    FROM metricauso
    WHERE activo = 's';
END;
//

-- Obtener una métrica de uso por su ID
CREATE PROCEDURE obtener_metricauso(
    IN p_metricaid INT
)
BEGIN
    SELECT *
    FROM metricauso
    WHERE metricaid = p_metricaid AND activo = 's';
END;
//

DELIMITER ;

--procedimientos para la tabla "rol"

DELIMITER //

-- Insertar nuevo rol
CREATE PROCEDURE insertar_rol(
    OUT p_rolid INT,
    IN p_nombre VARCHAR(50),
    IN p_descripcion VARCHAR(254)
)
BEGIN
    INSERT INTO rol(nombre, descripcion)
    VALUES (p_nombre, p_descripcion);

    SET p_rolid = LAST_INSERT_ID();
END;
//

-- Actualizar rol
CREATE PROCEDURE actualizar_rol(
    IN p_rolid INT,
    IN p_nombre VARCHAR(50),
    IN p_descripcion VARCHAR(254)
)
BEGIN
    UPDATE rol
    SET nombre = p_nombre,
        descripcion = p_descripcion
    WHERE rolid = p_rolid;
END;
//

-- Eliminar rol (lógica)
CREATE PROCEDURE eliminar_rol(
    IN p_rolid INT
)
BEGIN
    UPDATE rol
    SET activo = 'n'
    WHERE rolid = p_rolid;
END;
//

-- Listar roles activos
CREATE PROCEDURE listar_rol()
BEGIN
    SELECT *
    FROM rol
    WHERE activo = 's';
END;
//

-- Obtener rol por ID
CREATE PROCEDURE obtener_rol(
    IN p_rolid INT
)
BEGIN
    SELECT *
    FROM rol
    WHERE rolid = p_rolid AND activo = 's';
END;
//

DELIMITER ;
