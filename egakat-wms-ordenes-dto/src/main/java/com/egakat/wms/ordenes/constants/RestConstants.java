package com.egakat.wms.ordenes.constants;

public class RestConstants {

	final public static String BASE = "/api";

	final public static String SUSCRIPCIONES = BASE + "/suscripciones";

	final public static String SUSCRIPCIONES_ORDENES_ALISTAMIENTO = SUSCRIPCIONES + "/ordenes-alistamiento";

	final public static String SUSCRIPCIONES_ORDENES_ALISTAMIENTO_CREADAS = "/creadas";

	final public static String SUSCRIPCIONES_ORDENES_ALISTAMIENTO_CREADAS_ACK = "/creadas/{id}/ack";

	final public static String SUSCRIPCIONES_ORDENES_ALISTAMIENTO_EN_STAGE = "/stage";

	final public static String SUSCRIPCIONES_ORDENES_ALISTAMIENTO_EN_STAGE_ACK = "/stage/{id}/ack";

	final public static String SUSCRIPCIONES_ORDENES_ALISTAMIENTO_BY_PK = "/client_id/{client_id}/wh_id/{wh_id}/ordnum/{ordnum}";
}
