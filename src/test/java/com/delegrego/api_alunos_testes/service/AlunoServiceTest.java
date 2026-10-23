package com.delegrego.api_alunos_testes.service;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.delegrego.api_alunos_testes.dto.AlunoRequest;
import com.delegrego.api_alunos_testes.dto.AlunoResponse;
import com.delegrego.api_alunos_testes.model.Aluno;
import com.delegrego.api_alunos_testes.repository.AlunoRepository;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

	@InjectMocks
	AlunoService service;

	@Mock
	AlunoRepository repository;

	@Test
	void deveSalvarERetornarAluno() {

		// Arrange
		AlunoRequest alunoRequest = new AlunoRequest("José", "jose@email.com", "senha", LocalDate.of(1996, 11, 15),
				9.2);

		// Stub -> parte
		Mockito.when(repository.existsByEmailIgnoreCase(alunoRequest.getEmail())).thenReturn(false);
		
		Aluno aluno = new Aluno(1L, alunoRequest.getNome(), alunoRequest.getEmail(), alunoRequest.getSenha(), alunoRequest.getDataNascimento(), alunoRequest.getMedia());

		Mockito.when(repository.save(Mockito.any(Aluno.class))).thenReturn(aluno);
		
		// Act
		AlunoResponse alunoSalvo = service.cadastrarAluno(alunoRequest);

		// Assert
		Assertions.assertThat(alunoSalvo).isNotNull();
		Assertions.assertThat(alunoSalvo.getId()).isGreaterThan(0);

	}

}
