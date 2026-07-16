package cadastro.investidor.api.repositories;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cadastro.investidor.api.interfaces.InvestidorRepository;
import cadastro.investidor.api.models.Investidor;
import cadastro.investidor.api.rowMappers.InvestidorRowMapper;

@Repository
public class InvestidorRepositoryImpl implements InvestidorRepository {

	private static final String SELECT_BY_CODIGO = """
			SELECT codigo, nome, cpf, apta_negociacao, data_criacao
			FROM investidor
			WHERE codigo = ?
			""";

	private static final String INSERT = """
			INSERT INTO investidor (codigo, nome, cpf, apta_negociacao)
			VALUES (?, ?, ?, ?)
			""";

	private static final String UPDATE = """
			UPDATE investidor
			SET nome = ?, cpf = ?, apta_negociacao = ?
			WHERE codigo = ?
			""";

	private static final String DELETE = """
			DELETE FROM investidor
			WHERE codigo = ?
			""";

	private final JdbcTemplate jdbcTemplate;
	private final InvestidorRowMapper rowMapper;

	public InvestidorRepositoryImpl(JdbcTemplate jdbcTemplate, InvestidorRowMapper rowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapper = rowMapper;
	}

	@Override
	public Optional<Investidor> findByCodigo(Integer codigo) {
		return jdbcTemplate.query(SELECT_BY_CODIGO, rowMapper, codigo).stream().findFirst();
	}

	@Override
	public Investidor save(Investidor investidor) {
		jdbcTemplate.update(
				INSERT,
				investidor.getCodigo(),
				investidor.getNome(),
				investidor.getCpf(),
				investidor.isAptaNegociacao());
		return findByCodigo(investidor.getCodigo()).orElseThrow();
	}

	@Override
	public Investidor update(Investidor investidor) {
		jdbcTemplate.update(
				UPDATE,
				investidor.getNome(),
				investidor.getCpf(),
				investidor.isAptaNegociacao(),
				investidor.getCodigo());
		return findByCodigo(investidor.getCodigo()).orElseThrow();
	}

	@Override
	public void deleteByCodigo(Integer codigo) {
		jdbcTemplate.update(DELETE, codigo);
	}
}
