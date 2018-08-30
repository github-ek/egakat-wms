package com.egakat.wms.maestros.domain.ordenes;

import java.time.LocalDateTime;

public interface OrdShipmentLineLote {

	String getOrdlin();

	String getPrtnum();

	String getLotnum();
	
	String getInvsts();
	
	String getOrgcod();
	
	LocalDateTime getExpireDte();

	int getUntqty();
}