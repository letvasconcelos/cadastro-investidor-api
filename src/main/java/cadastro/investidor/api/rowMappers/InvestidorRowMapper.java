package cadastro.investidor.api.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cadastro.investidor.api.models.Investidor;

@Component
public class InvestidorRowMapper implements RowMapper<Investidor> {

	@Override
	public Investidor mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Investidor.builder()
				.codigo(rs.getInt("codigo"))
				.nome(rs.getString("nome"))
				.cpf(rs.getString("cpf"))
				.aptaNegociacao(rs.getBoolean("apta_negociacao"))
				.dataCriacao(rs.getObject("data_criacao", LocalDateTime.class))
				.dataAtualizacao(rs.getObject("data_atualizacao", LocalDateTime.class))
				.build();
	}
}
