package com.egakat.wms.maestros.domain.ordenes;

public interface OrdShipmentLine {

	String getOrdlin();

	String getPrtnum();

	String getInvsts();

	int getOrdqty();
	
	int getShpqty();
	
	int getStgqty();
}