package cadastro.investidor.api.exceptions;

public class InvestidorNotFoundException extends RuntimeException {

	public InvestidorNotFoundException(Integer codigo) {
		super("Investidor não encontrado: " + codigo);
	}
}
