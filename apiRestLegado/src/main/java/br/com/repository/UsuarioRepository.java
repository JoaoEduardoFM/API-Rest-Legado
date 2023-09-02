package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	/* Criado métodos para estudo. Feito de forma nativa na classe UsuarioCustomRepository.
	@Query("FROM usuario u WHERE u.nome like :nome")//JPQL
	List<Usuario> buscaPorNome(String nome);
	
	@Query("FROM usuario u WHERE u.sobrenome like :sobrenome")//JPQL
	List<Usuario> buscaPorSobrenome(String sobrenome);
	*/
	
	@Query("FROM usuario u WHERE u.idade like :idade")//JPQL
	List<Usuario> buscaPorIdade(Integer idade);
}
