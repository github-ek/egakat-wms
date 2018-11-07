package com.egakat.wms.ordenes.constants;

public class RestConstants {

	final public static String base = "/api/ordenes";

	final public static String ordenes_alistamiento = base + "/ordenes-alistamiento";

	final public static String ordenes_alistamiento_suscripciones = "/suscripciones";

	final public static String ordenes_alistamiento_suscripciones_ack = ordenes_alistamiento_suscripciones
			+ "/{id}/ack";
}
