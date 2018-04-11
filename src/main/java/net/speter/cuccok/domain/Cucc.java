package net.speter.cuccok.domain;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Cucc {

	public UUID id;
	@NotNull
	public String megnevezes;
	public int mennyiseg;
	@NotNull
	public String rekesz;
	public String elteve;

}
