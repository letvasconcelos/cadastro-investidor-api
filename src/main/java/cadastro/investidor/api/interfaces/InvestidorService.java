package cadastro.investidor.api.interfaces;

import java.util.List;

import cadastro.investidor.api.dtos.InvestidorRequest;
import cadastro.investidor.api.dtos.InvestidorResponse;

public interface InvestidorService {

	InvestidorResponse buscarPorCodigo(Integer codigo);

	List<InvestidorResponse> listarTodos();

	InvestidorResponse criar(InvestidorRequest request);

	InvestidorResponse atualizar(Integer codigo, InvestidorRequest request);

	void remover(Integer codigo);
}
