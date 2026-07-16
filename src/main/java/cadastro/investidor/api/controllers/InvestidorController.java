package cadastro.investidor.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cadastro.investidor.api.dtos.InvestidorRequest;
import cadastro.investidor.api.dtos.InvestidorResponse;
import cadastro.investidor.api.interfaces.InvestidorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/cadastros/investidor")
public class InvestidorController {

	private final InvestidorService investidorService;

	public InvestidorController(InvestidorService investidorService) {
		this.investidorService = investidorService;
	}

	@GetMapping
	public InvestidorResponse buscar(@RequestParam Integer codigo) {
		return investidorService.buscarPorCodigo(codigo);
	}

	@PostMapping
	public ResponseEntity<InvestidorResponse> criar(@Valid @RequestBody InvestidorRequest request) {
		InvestidorResponse response = investidorService.criar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	public InvestidorResponse atualizar(@Valid @RequestBody InvestidorRequest request) {
		return investidorService.atualizar(request);
	}

	@DeleteMapping
	public ResponseEntity<Void> remover(@RequestParam Integer codigo) {
		investidorService.remover(codigo);
		return ResponseEntity.noContent().build();
	}
}
