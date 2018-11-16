package com.egakat.wms.ordenes.constants;

public class RestConstants {

	final public static String suscripciones = "/api/suscripciones";

	final public static String suscripciones_ordenes_alistamiento = suscripciones + "/ordenes-alistamiento";

	final public static String suscripciones_ordenes_alistamiento_creadas = "/creadas";

	final public static String suscripciones_ordenes_alistamiento_creadas_ack = "/creadas/{id}/ack";

	final public static String suscripciones_ordenes_alistamiento_en_stage = "/stage";

	final public static String suscripciones_ordenes_alistamiento_en_stage_ack = "/stage/{id}/ack";

	final public static String suscripciones_ordenes_alistamiento_by_pk = "/client_id/{client_id}/wh_id/{wh_id}/ordnum/{ordnum}";
}
