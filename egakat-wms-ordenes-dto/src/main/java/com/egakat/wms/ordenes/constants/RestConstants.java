package com.egakat.wms.ordenes.constants;

public class RestConstants {

	final public static String base = "/api/ordenes";

	final public static String ordenes_alistamiento = base + "/ordenes-alistamiento";

	final public static String ordenes_alistamiento_suscripciones = "/suscripciones";

	final public static String ordenes_alistamiento_suscripciones_ack = ordenes_alistamiento_suscripciones
			+ "/{id}/ack";

	final public static String suscripciones = base + "/suscripciones";
	
	final public static String suscripciones_ordenes_alistamiento = "/ordenes-alistamiento";
	
	final public static String suscripciones_ordenes_alistamiento_creacion = suscripciones_ordenes_alistamiento + "/creacion";
	
	final public static String suscripciones_ordenes_alistamiento_stage = suscripciones_ordenes_alistamiento + "/stage";
}
