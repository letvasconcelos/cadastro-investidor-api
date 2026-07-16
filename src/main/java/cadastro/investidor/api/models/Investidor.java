package cadastro.investidor.api.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Investidor {

	private Integer codigo;
	private String nome;
	private String cpf;
	private boolean aptaNegociacao;
	private LocalDateTime dataCriacao;
}
