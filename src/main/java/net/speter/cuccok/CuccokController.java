package net.speter.cuccok;

import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import net.speter.cuccok.domain.Cucc;
import net.speter.cuccok.repo.CuccRepo;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class CuccokController {

	CuccRepo cuccRepo;

	public CuccokController(CuccRepo cuccRepo) {
		this.cuccRepo = cuccRepo;
	}

	@RequestMapping("/")
	public String index() {
		return "";
	}

	@GetMapping("/cucc")
	public List<Cucc> cuccok() throws Exception {
		return cuccRepo.getAll();
	}

	@PostMapping("/cucc")
	public UUID storeCucc(@RequestBody Cucc cucc) throws Exception {
		Preconditions.checkArgument(cucc.id == null, "Cucc id mustn't be set");
		UUID uuid = cuccRepo.store(cucc);
		return uuid;
	}

	@GetMapping("/cucc/{id}")
	public Cucc getCucc(@PathVariable("id") UUID id) throws Exception {
		return cuccRepo.get(id);
	}

	@PutMapping("/cucc/{id}")
	public boolean updateCucc(@PathVariable("id") UUID id, @RequestBody Cucc cucc) throws Exception {
		if (!id.equals(cucc.id)) {
			throw new IllegalArgumentException(); // TODO
		}
		return cuccRepo.update(cucc);
	}

	@DeleteMapping("/cucc/{id}")
	public boolean deleteCucc(@PathVariable("id") UUID id) throws Exception {
		return cuccRepo.delete(id);
	}

}
