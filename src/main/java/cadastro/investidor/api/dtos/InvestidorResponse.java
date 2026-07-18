package cadastro.investidor.api.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import cadastro.investidor.api.models.Investidor;
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
public class InvestidorResponse {

	private Integer codigo;
	private String nome;
	private String cpf;

	@JsonProperty("apta_negociacao")
	private boolean aptaNegociacao;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonProperty("data_criacao")
	private LocalDateTime dataCriacao;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonProperty("data_atualizacao")
	private LocalDateTime dataAtualizacao;

	public static InvestidorResponse from(Investidor investidor) {
		return InvestidorResponse.builder()
				.codigo(investidor.getCodigo())
				.nome(investidor.getNome())
				.cpf(investidor.getCpf())
				.aptaNegociacao(investidor.isAptaNegociacao())
				.dataCriacao(investidor.getDataCriacao())
				.dataAtualizacao(investidor.getDataAtualizacao())
				.build();
	}
}
