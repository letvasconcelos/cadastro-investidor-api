package cadastro.investidor.api.interfaces;

import java.util.Optional;

import cadastro.investidor.api.models.Investidor;

public interface InvestidorRepository {

	Optional<Investidor> findByCodigo(Integer codigo);

	Investidor save(Investidor investidor);

	Investidor update(Investidor investidor);

	void deleteByCodigo(Integer codigo);
}
