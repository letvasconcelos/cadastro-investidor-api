package cadastro.investidor.api.exceptions;

public class InvestidorAlreadyExistsException extends RuntimeException {

	public InvestidorAlreadyExistsException(String cpf) {
		super("Investidor já cadastrado para o CPF: " + cpf);
	}
}
