package cadastro.investidor.api.exceptions;

public class InvestidorAlreadyExistsException extends RuntimeException {

	public InvestidorAlreadyExistsException(Integer codigo) {
		super("Investidor já cadastrado: " + codigo);
	}
}
