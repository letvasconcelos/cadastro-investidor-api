package cadastro.investidor.api.interfaces;

import java.util.List;
import java.util.Optional;

import cadastro.investidor.api.models.Investidor;

public interface InvestidorRepository {

	Optional<Investidor> findByCodigo(Integer codigo);

	Optional<Investidor> findByCpf(String cpf);

	List<Investidor> findAll();

	Investidor save(Investidor investidor);

	Investidor update(Investidor investidor);

	void desativarPorCodigo(Integer codigo);
}
