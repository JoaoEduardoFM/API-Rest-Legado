package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Usuario;
import br.com.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
@Api(value = "Gerenciar usuários", tags = "Usuário", description = " Crud usuários.")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	@ApiOperation (
			value = "Cadastra um usuário.",
			notes = "Cadastra um usuário.")
	private ResponseEntity<?> cadastraUsuario(@RequestBody Usuario usuario) {	
		return usuarioService.cadastraUsuario(usuario);
	}
	
	@PutMapping
	@ApiOperation (
			value = "Atualiza um usuário.",
			notes = "Atualiza um usuário.")
	private ResponseEntity<?> atualizaUsuario(@RequestBody Usuario usuario) {
		return usuarioService.atualizaUsuario(usuario);
	}
	
	@DeleteMapping
	@ApiOperation (
			value = "Deleta um usuário.",
			notes = "Deleta um usuário.")
	private ResponseEntity<?> deletaUsuario(Long cpf) {
		return usuarioService.deletaUsuario(cpf);
	}
	
	@GetMapping
	@DeleteMapping
	@ApiOperation (
			value = "Busca usuário pelo cpf.",
			notes = "Busca usuário pelo cpf.")
	private ResponseEntity<?> buscaUsuarioCpf(Long cpf) {
		return usuarioService.buscaUsuarioCpf(cpf);
	}
	
	@GetMapping("/listaRegistro")
	@DeleteMapping
	@ApiOperation (
			value = "Busca usuários cadastrados.",
			notes = "Busca usuários cadastrados.")
	private ResponseEntity<?> buscaUsuarios() {
		return usuarioService.buscaUsuarios();
	}
	
	@GetMapping("/listaPorNome")
	@DeleteMapping
	@ApiOperation (
			value = "Busca por nome.",
			notes = "Busca por nome.")
	private ResponseEntity<?> buscaPorNome(String nome) {
		return usuarioService.buscaPorNome(nome);
	}
	
	@GetMapping("/listaPorSobrenome")
	@DeleteMapping
	@ApiOperation (
			value = "Busca por sobrenome.",
			notes = "Busca por sobrenome.")
	private ResponseEntity<?> buscaPorSobrenome(String sobrenome) {
		return usuarioService.buscaPorSobrenome(sobrenome);
	}
	
	@GetMapping("/listaPorIdade")
	@DeleteMapping
	@ApiOperation (
			value = "Busca por Idade.",
			notes = "Busca por Idade.")
	private ResponseEntity<?> buscaPorIdade(Integer Idade) {
		return usuarioService.buscaPorIdade(Idade);
	}

}
