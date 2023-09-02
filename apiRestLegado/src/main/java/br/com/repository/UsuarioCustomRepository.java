package br.com.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.model.entity.Usuario;

@Repository
@Transactional
public class UsuarioCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @apiNote Insere um novo usuário de forma nativa.
	 * @param usuario
	 */
	public Usuario cadastraUsurioNative(Usuario usuario) {
		String sql = "INSERT INTO usuario (cpf, nome, sobrenome, idade) VALUES (?, ?, ?, ?)";

		entityManager.createNativeQuery(sql)
		.setParameter(1, usuario.getCpf())
		.setParameter(2, usuario.getNome())
		.setParameter(3, usuario.getSobrenome())
		.setParameter(4, usuario.getIdade())
		.executeUpdate();
		return usuario;
	}
	
	/**
	 * @apiNote Atualiza um usuário de forma nativa.
	 * @param usuario
	 */
	public Usuario atualizaUsuarioNative(Usuario usuario) {
		String sql2 = "UPDATE usuario set nome = ? , sobrenome = ? , idade = ? WHERE cpf = ?";
		
		entityManager.createNativeQuery(sql2)
		.setParameter(1, usuario.getNome())
		.setParameter(2, usuario.getSobrenome())
		.setParameter(3, usuario.getIdade())
		.setParameter(4, usuario.getCpf())
		.executeUpdate();
		return usuario;
	}
	
	/**
	 * @apiNote deleta um usuário de forma nativa.
	 * @param cpf
	 */
	public Long  deletaUsuarioNative(Long cpf) {
		String sql3 = "DELETE FROM usuario WHERE cpf = ?";
		 int registrosAfetados = entityManager.createNativeQuery(sql3)
		.setParameter(1, cpf)
		.executeUpdate();
		return Long.valueOf(registrosAfetados); //  retornará o número de registros que foram excluídos pela consulta

	}
	
	/**
	 * @apiNote busca um usuário pelo cpf de forma nativa.
	 * @param cpf
	 */
	public Usuario buscaUsuarioPorCpf(Long cpf) {
		String sql4 = "SELECT * FROM usuario WHERE cpf = ?";
		
		return (Usuario)entityManager.createNativeQuery(sql4, Usuario.class)
				.setParameter(1, cpf)
				.getSingleResult();
		
	}
	
	/**
	 * @apiNote busca usuários de forma nativa.
	 * @param usuario
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> buscaUsuarios() {
	    String sql = "SELECT * FROM usuario";
	    return entityManager.createNativeQuery(sql, Usuario.class)
	            .getResultList();
	}
	
	/**
	 * @apiNote Busca por nome
	 * @param nome
	 */
	public List<Usuario> buscaPorNome(String nome) {
        String jpql = "SELECT u FROM usuario u WHERE u.nome like :nome";

        return entityManager.createQuery(jpql, Usuario.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
	
	/**
	 * @apiNote Busca por sobrenome
	 * @param sobrenome
	 */
	public List<Usuario> buscaPorSobrenome(String sobrenome) {
        String jpql = "SELECT u FROM usuario u WHERE u.sobrenome like :sobrenome";

        return entityManager.createQuery(jpql, Usuario.class)
                .setParameter("sobrenome", "%" + sobrenome + "%")
                .getResultList();
    }
	
	/**
	 * @apiNote valida id
	 * @param cpf
	 * @return 1 exite id. 0 não existe.
	 */
	public int validaId(Long cpf) {
		// Consulta de contagem para verificar se o CPF existe
	    String countSql = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
	    BigInteger count = (BigInteger) entityManager.createNativeQuery(countSql)
	            .setParameter(1, cpf)
	            .getSingleResult();

	    // Verifica se o CPF existe
	    if (count.intValue() > 0) {	   
	        return 1;
	    }else {
	    	return 0;
	    }
	}
}
