package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.model.entity.Usuario;
import br.com.model.response.Response;
import br.com.repository.UsuarioCustomRepository;
import br.com.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired 
	UsuarioCustomRepository repositoryCustom;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public ResponseEntity<?> cadastraUsuario(Usuario usuario) {
		Response retorno = new Response();
		boolean validaId = repositoryCustom.validaId(usuario.getCpf());

		// caso o cpf não for preenchido retorna o maior valor do cpf + 1
		if(usuario.getCpf() == null) {
			usuario.setCpf(repositoryCustom.buscarMaiorCPF(usuario.getCpf()));
		}
		
		if (validaId == true) {
			retorno.setMessage("Informe um cpf não existente.");
			retorno.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
		}
		try {
			Usuario cadastro = repositoryCustom.cadastraUsurioNative(usuario);
			return ResponseEntity.status(HttpStatus.OK).body(cadastro);
		} catch (Exception e) {
			retorno.setMessage("Erro ao cadastrar registro");
			retorno.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);

	}
	
	public ResponseEntity<?> atualizaUsuario(Usuario usuario) {
		Response retorno = new Response();
		boolean validaId = repositoryCustom.validaId(usuario.getCpf());
		
		if(usuario.getCpf() == null) {
			retorno.setMessage("O cpf deve ser informado.");
			retorno.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
		}

		if (validaId == false) {
			retorno.setMessage("O cpf informado não existe.");
			retorno.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
		}
		try {
			Usuario atualiza = repositoryCustom.atualizaUsuarioNative(usuario);
			return ResponseEntity.status(HttpStatus.OK).body(atualiza);
		} catch (Exception e) {
			retorno.setMessage("Erro ao atualizar registro");
			retorno.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);

	}
	
	public ResponseEntity<?> deletaUsuario(Long cpf) {
		Response retorno = new Response();
		Long remove = repositoryCustom.deletaUsuarioNative(cpf);
		boolean validaId = repositoryCustom.validaId(cpf);
		
		if(cpf == null) {
			retorno.setMessage("O cpf deve ser informado.");
			retorno.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
		}
		
		if (remove > 0) {
			retorno.setMessage("Registro deletado com sucesso.");
			retorno.setStatus(HttpStatus.OK);
		} else {
			if (validaId == false) {
				retorno.setMessage("O id informado não existe.");
				retorno.setStatus(HttpStatus.BAD_REQUEST);
			} else {
				retorno.setMessage("Erro ao excluir registro.");
				retorno.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(retorno);

	}
	
	public ResponseEntity<?> buscaUsuarioCpf(Long cpf) {
		boolean validaId = repositoryCustom.validaId(cpf);
		Response retorno = new Response();
		
		if(cpf == null) {
			retorno.setMessage("O cpf deve ser informado.");
			retorno.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
		}
		
		if(validaId == false) {
			retorno.setMessage("Registro não encontrado.");
			retorno.setStatus(HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}
		try {
		Usuario get = repositoryCustom.buscaUsuarioPorCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(get);
		}catch(Exception e) {
			retorno.setMessage("Erro ao buscar registro.");
			retorno.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
	}
	
	public ResponseEntity<?> buscaUsuarios() {
	    Response retorno = new Response();
	    List<Usuario> usuarios = repositoryCustom.buscaUsuarios();
	    
	    if (usuarios.isEmpty()) {
	        retorno.setMessage("Nenhum registro encontrado.");
	        retorno.setStatus(HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
	    } else {
	        // Processar os usuários conforme necessário
	        for (Usuario usuario : usuarios) {
	            usuario.getCpf();
	            usuario.getIdade();
	            usuario.getNome();
	            usuario.getSobrenome();
	        }
	        
	        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	    }
	}
	
	public ResponseEntity<?> buscaPorNome(String nome){
		 Response response = new Response();
		 
		 if(nome == null) {
				response.setMessage("O nome deve ser informado.");
				response.setStatus(HttpStatus.NOT_FOUND);
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		 
		List<Usuario> retorno= repositoryCustom.buscaPorNome(nome);
		
		if(retorno.isEmpty()) {
			response.setMessage("Nenhum registro encontrado.");
			response.setStatus(HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
	}
	
	public ResponseEntity<?> buscaPorSobrenome(String sobrenome){
		 Response response = new Response();
		 
		 if(sobrenome == null) {
				response.setMessage("O sobrenome deve ser informado.");
				response.setStatus(HttpStatus.NOT_FOUND);
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		 
		List<Usuario> retorno= repositoryCustom.buscaPorSobrenome(sobrenome);
		
		if(retorno.isEmpty()) {
			response.setMessage("Nenhum registro encontrado.");
			response.setStatus(HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
	}
	
	public ResponseEntity<?> buscaPorIdade(Integer idade){
		 Response response = new Response();
		 
		 if(idade == null) {
				response.setMessage("A idade deve ser informada.");
				response.setStatus(HttpStatus.NOT_FOUND);
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		 
		List<Usuario> retorno= repositoryCustom.buscaPorIdade(idade);
		
		if(retorno.isEmpty()) {
			response.setMessage("Nenhum registro encontrado.");
			response.setStatus(HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
	}

}
