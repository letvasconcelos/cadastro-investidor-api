package cadastro.investidor.api.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cadastro.investidor.api.dtos.InvestidorRequest;
import cadastro.investidor.api.dtos.InvestidorResponse;
import cadastro.investidor.api.exceptions.InvestidorAlreadyExistsException;
import cadastro.investidor.api.exceptions.InvestidorNotFoundException;
import cadastro.investidor.api.interfaces.InvestidorRepository;
import cadastro.investidor.api.interfaces.InvestidorService;
import cadastro.investidor.api.models.Investidor;

@Service
@Transactional
public class InvestidorServiceImpl implements InvestidorService {

	private final InvestidorRepository investidorRepository;

	public InvestidorServiceImpl(InvestidorRepository investidorRepository) {
		this.investidorRepository = investidorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public InvestidorResponse buscarPorCodigo(Integer codigo) {
		Investidor investidor = investidorRepository.findByCodigo(codigo)
				.orElseThrow(() -> new InvestidorNotFoundException(codigo));
		return InvestidorResponse.from(investidor);
	}

	@Override
	public InvestidorResponse criar(InvestidorRequest request) {
		if (investidorRepository.findByCodigo(request.getCodigo()).isPresent()) {
			throw new InvestidorAlreadyExistsException(request.getCodigo());
		}

		Investidor investidor = Investidor.builder()
				.codigo(request.getCodigo())
				.nome(request.getNome())
				.cpf(request.getCpf())
				.aptaNegociacao(request.getAptaNegociacao())
				.build();

		return InvestidorResponse.from(investidorRepository.save(investidor));
	}

	@Override
	public InvestidorResponse atualizar(InvestidorRequest request) {
		if (investidorRepository.findByCodigo(request.getCodigo()).isEmpty()) {
			throw new InvestidorNotFoundException(request.getCodigo());
		}

		Investidor investidor = Investidor.builder()
				.codigo(request.getCodigo())
				.nome(request.getNome())
				.cpf(request.getCpf())
				.aptaNegociacao(request.getAptaNegociacao())
				.build();

		return InvestidorResponse.from(investidorRepository.update(investidor));
	}

	@Override
	public void remover(Integer codigo) {
		if (investidorRepository.findByCodigo(codigo).isEmpty()) {
			throw new InvestidorNotFoundException(codigo);
		}
		investidorRepository.deleteByCodigo(codigo);
	}
}
