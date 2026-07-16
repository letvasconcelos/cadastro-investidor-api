package cadastro.investidor.api.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvestidorNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(InvestidorNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Map.of("mensagem", ex.getMessage()));
	}

	@ExceptionHandler(InvestidorAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleAlreadyExists(InvestidorAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(Map.of("mensagem", ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> erros = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> erros.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(erros);
	}
}
