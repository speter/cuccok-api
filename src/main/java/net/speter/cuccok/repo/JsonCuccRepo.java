package net.speter.cuccok.repo;

import java.io.File;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Preconditions;

import net.speter.cuccok.domain.Cucc;

public class JsonCuccRepo implements CuccRepo {

	private static final String REPO_PATH = "cucc-repo.json";

	private final ObjectMapper objectMapper;

	public JsonCuccRepo(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Override
	public synchronized List<Cucc> getAll() throws Exception {
		return objectMapper.readValue(new File(REPO_PATH), new TypeReference<List<Cucc>>() {
		});
	}

	@Override
	public synchronized Cucc get(UUID id) throws Exception {
		Preconditions.checkArgument(id != null, "UUID mustn't be null");
		List<Cucc> cuccok = objectMapper.readValue(new File(REPO_PATH), new TypeReference<List<Cucc>>() {});
		for (Cucc c : cuccok) {
			if (id.equals(c.id)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public synchronized UUID store(Cucc c) throws Exception {
		Preconditions.checkArgument(c != null, "Cucc mustn't be null");
		Preconditions.checkArgument(c.id == null, "Cucc mustn't have an id");
		UUID uuid = UUID.randomUUID();
		c.id = uuid;
		List<Cucc> cuccok = objectMapper.readValue(new File(REPO_PATH), new TypeReference<List<Cucc>>() {
		});
		cuccok.add(c);
		objectMapper.writeValue(new File(REPO_PATH), cuccok);
		return uuid;
	}

	@Override
	public synchronized boolean update(Cucc c) throws Exception {
		Preconditions.checkArgument(c != null, "Cucc mustn't be null");
		Preconditions.checkArgument(c.id != null, "Cucc must have an id");
		List<Cucc> cuccok = objectMapper.readValue(new File(REPO_PATH), new TypeReference<List<Cucc>>() {
		});
		boolean updated = false;
		for (int i = 0; i < cuccok.size(); i++) {
			if (c.id.equals(cuccok.get(i).id)) {
				cuccok.set(i, c);
				updated = true;
				break;
			}
		}
		if (updated) {
			objectMapper.writeValue(new File(REPO_PATH), cuccok);
		}
		return updated;

	}

	@Override
	public synchronized boolean delete(UUID id) throws Exception {
		Preconditions.checkArgument(id != null, "UUID mustn't be null");
		List<Cucc> cuccok = objectMapper.readValue(new File(REPO_PATH), new TypeReference<List<Cucc>>() {
		});
		boolean deleted = false;
		for (int i = 0; i < cuccok.size(); i++) {
			if (id.equals(cuccok.get(i).id)) {
				cuccok.remove(i);
				deleted = true;
				break;
			}
		}
		if (deleted) {
			objectMapper.writeValue(new File(REPO_PATH), cuccok);
		}
		return deleted;
	}

}
