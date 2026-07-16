package cadastro.investidor.api.interfaces;

import cadastro.investidor.api.dtos.InvestidorRequest;
import cadastro.investidor.api.dtos.InvestidorResponse;

public interface InvestidorService {

	InvestidorResponse buscarPorCodigo(Integer codigo);

	InvestidorResponse criar(InvestidorRequest request);

	InvestidorResponse atualizar(InvestidorRequest request);

	void remover(Integer codigo);
}
