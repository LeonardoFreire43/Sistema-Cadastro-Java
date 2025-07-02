// Caminho do arquivo: com/example/userdatabase/repository/UserRepository.java
package com.example.userdatabase.repository;

import com.example.userdatabase.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Método para buscar um usuário pelo seu email.
     * O Spring Data MongoDB cria a implementação automaticamente.
     */
    Optional<User> findByEmail(String email);

    /**
     * Método para buscar um usuário combinando email, nome e CPF.
     * Usado para a validação de recuperação de senha.
     */
    Optional<User> findByEmailAndNomeAndCpf(String email, String nome, String cpf);
}
