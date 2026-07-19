package cadastro.investidor.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cadastro.investidor.api.interfaces.InvestidorRepository;
import cadastro.investidor.api.models.Investidor;
import cadastro.investidor.api.rowMappers.InvestidorRowMapper;

@Repository
public class InvestidorRepositoryImpl implements InvestidorRepository {

	private static final String SELECT_BY_CODIGO = """
			SELECT codigo, nome, cpf, apta_negociacao, data_criacao, data_atualizacao
			FROM cadastros.investidor
			WHERE codigo = ?
			""";

	private static final String SELECT_BY_CPF = """
			SELECT codigo, nome, cpf, apta_negociacao, data_criacao, data_atualizacao
			FROM cadastros.investidor
			WHERE cpf = ?
			""";

	private static final String SELECT_ALL = """
			SELECT codigo, nome, cpf, apta_negociacao, data_criacao, data_atualizacao
			FROM cadastros.investidor
			ORDER BY codigo
			""";

	private static final String INSERT = """
			INSERT INTO cadastros.investidor (nome, cpf, apta_negociacao)
			VALUES (?, ?, ?)
			RETURNING codigo, nome, cpf, apta_negociacao, data_criacao, data_atualizacao
			""";

	private static final String UPDATE = """
			UPDATE cadastros.investidor
			SET nome = ?, cpf = ?, apta_negociacao = ?
			WHERE codigo = ?
			RETURNING codigo, nome, cpf, apta_negociacao, data_criacao, data_atualizacao
			""";

	private static final String DESATIVAR = """
			UPDATE cadastros.investidor
			SET apta_negociacao = false
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
	public Optional<Investidor> findByCpf(String cpf) {
		return jdbcTemplate.query(SELECT_BY_CPF, rowMapper, cpf).stream().findFirst();
	}

	@Override
	public List<Investidor> findAll() {
		return jdbcTemplate.query(SELECT_ALL, rowMapper);
	}

	@Override
	public Investidor save(Investidor investidor) {
		return jdbcTemplate.query(
				INSERT,
				rowMapper,
				investidor.getNome(),
				investidor.getCpf(),
				investidor.isAptaNegociacao())
				.stream()
				.findFirst()
				.orElseThrow();
	}

	@Override
	public Investidor update(Investidor investidor) {
		return jdbcTemplate.query(
				UPDATE,
				rowMapper,
				investidor.getNome(),
				investidor.getCpf(),
				investidor.isAptaNegociacao(),
				investidor.getCodigo())
				.stream()
				.findFirst()
				.orElseThrow();
	}

	@Override
	public void desativarPorCodigo(Integer codigo) {
		jdbcTemplate.update(DESATIVAR, codigo);
	}
}
