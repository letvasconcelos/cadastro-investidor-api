package cadastro.investidor.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cadastro.investidor.api.dtos.InvestidorRequest;
import cadastro.investidor.api.dtos.InvestidorResponse;
import cadastro.investidor.api.exceptions.InvestidorAlreadyExistsException;
import cadastro.investidor.api.exceptions.InvestidorNotFoundException;
import cadastro.investidor.api.interfaces.InvestidorRepository;
import cadastro.investidor.api.models.Investidor;

@ExtendWith(MockitoExtension.class)
class InvestidorServiceImplTest {

	@Mock
	private InvestidorRepository repository;

	private InvestidorServiceImpl service;

	@BeforeEach
	void setUp() {
		service = new InvestidorServiceImpl(repository);
	}

	@Test
	void deveBuscarInvestidorPorCodigo() {
		Investidor investidor = investidor();
		when(repository.findByCodigo(1)).thenReturn(Optional.of(investidor));

		InvestidorResponse response = service.buscarPorCodigo(1);

		assertEquals(investidor.getCodigo(), response.getCodigo());
		assertEquals(investidor.getNome(), response.getNome());
		assertEquals(investidor.getCpf(), response.getCpf());
		assertEquals(investidor.isAptaNegociacao(), response.isAptaNegociacao());
		assertEquals(investidor.getDataCriacao(), response.getDataCriacao());
	}

	@Test
	void deveListarTodosOsInvestidores() {
		when(repository.findAll()).thenReturn(List.of(investidor()));

		List<InvestidorResponse> response = service.listarTodos();

		assertEquals(1, response.size());
		assertEquals(1, response.getFirst().getCodigo());
	}

	@Test
	void deveFalharAoBuscarInvestidorInexistente() {
		when(repository.findByCodigo(1)).thenReturn(Optional.empty());

		assertThrows(InvestidorNotFoundException.class, () -> service.buscarPorCodigo(1));
	}

	@Test
	void deveCriarInvestidor() {
		InvestidorRequest request = request();
		Investidor investidor = investidor();
		when(repository.findByCpf(request.getCpf())).thenReturn(Optional.empty());
		when(repository.save(any(Investidor.class))).thenReturn(investidor);

		InvestidorResponse response = service.criar(request);

		assertEquals(1, response.getCodigo());
		verify(repository).save(any(Investidor.class));
	}

	@Test
	void deveFalharAoCriarCodigoExistente() {
		InvestidorRequest request = request();
		when(repository.findByCpf(request.getCpf())).thenReturn(Optional.of(investidor()));

		assertThrows(InvestidorAlreadyExistsException.class, () -> service.criar(request));
	}

	@Test
	void deveAtualizarInvestidorExistente() {
		InvestidorRequest request = request();
		Investidor investidor = investidor();
		when(repository.findByCodigo(1)).thenReturn(Optional.of(investidor));
		when(repository.findByCpf(request.getCpf())).thenReturn(Optional.of(investidor));
		when(repository.update(any(Investidor.class))).thenReturn(investidor);

		InvestidorResponse response = service.atualizar(1, request);

		assertEquals(1, response.getCodigo());
		verify(repository).update(any(Investidor.class));
	}

	@Test
	void deveRemoverInvestidorExistente() {
		when(repository.findByCodigo(1)).thenReturn(Optional.of(investidor()));

		service.remover(1);

		verify(repository).deleteByCodigo(1);
	}

	private InvestidorRequest request() {
		return InvestidorRequest.builder()
				.nome("Vitoria")
				.cpf("12345678901")
				.aptaNegociacao(true)
				.build();
	}

	private Investidor investidor() {
		return Investidor.builder()
				.codigo(1)
				.nome("Vitoria")
				.cpf("12345678901")
				.aptaNegociacao(true)
				.dataCriacao(LocalDateTime.of(2026, 5, 5, 0, 0))
				.dataAtualizacao(LocalDateTime.of(2026, 5, 5, 0, 0))
				.build();
	}
}
