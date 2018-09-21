package com.egakat.wms.maestros.configuration.constants;

public class RestConstants {

	final public static String base = "/api/maestros";

	final public static String bodegas = base + "/bodegas";

	final public static String clientes = base + "/clientes";

	final public static String direcciones = base + "/direcciones";

	final public static String estadosInventario = base + "/estados-inventario";

	final public static String subestadosInventario = base + "/subestados-inventario";

	final public static String unidadesMedida = base + "/unidades-medida";

	final public static String productosByCliente = "/{client_id}/productos";

	final public static String productosByClienteAndId = "/{client_id}/productos/{prtnum}";

	final public static String codigosByClienteAndProducto = "/{client_id}/productos/{prtnum}/codigos";

	final public static String atributosByClienteAndProductoAndBodega = "/{client_id}/productos/{prtnum}/atributos?wh_id={wh_id}";

	final public static String medidasByClienteAndProductoAndBodega = "/{client_id}/productos/{prtnum}/medidas?wh_id={wh_id}";

	final public static String productoTieneListaDeMateriales = "/{wh_id}/cliente/{client_id}/producto/{prtnum}";

	final public static String bom = "/{wh_id}/cliente/{client_id}/producto/{prtnum}/bom";

	final public static String ordenes_alistamiento = "/api/ordenes/ordenes-alistamiento";

	final public static String ordenes_alistamiento_suscripciones = "/suscripciones";

	final public static String ordenes_alistamiento_suscripciones_ack = ordenes_alistamiento_suscripciones
			+ "/{id}/ack";
}
