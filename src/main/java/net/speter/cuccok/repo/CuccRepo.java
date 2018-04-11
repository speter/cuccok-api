package net.speter.cuccok.repo;

import java.util.List;
import java.util.UUID;

import net.speter.cuccok.domain.Cucc;

public interface CuccRepo {

	List<Cucc> getAll() throws Exception;

	Cucc get(UUID id) throws Exception;

	UUID store(Cucc c) throws Exception;

	boolean update(Cucc c) throws Exception;

	boolean delete(UUID id) throws Exception;

}
