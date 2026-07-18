package cadastro.investidor.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class InvestidorRequest {

	@NotBlank
	private String nome;

	@NotBlank
	@Pattern(regexp = "\\d{11}", message = "deve conter exatamente 11 dígitos")
	private String cpf;

	@NotNull
	@JsonProperty("apta_negociacao")
	private Boolean aptaNegociacao;
}
