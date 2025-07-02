package com.example.userdatabase.controller;

import com.example.userdatabase.model.User;
import com.example.userdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este email já está em uso.");
        }
        if (user.getCpf() != null) user.setCpf(user.getCpf().replaceAll("\\D", ""));
        if (user.getNumeroTelefone() != null) user.setNumeroTelefone(user.getNumeroTelefone().replaceAll("\\D", ""));
        
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        // Todo novo usuário é criado com o papel "USER"
        user.setRole("USER"); 
        
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        Optional<User> userOptional = userRepository.findByEmail(credentials.get("email"));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(credentials.get("senha"), user.getSenha())) {
                // Retorna o objeto de usuário completo, incluindo o papel
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
    }

    // Para promover um usuário a administrador
    @PostMapping("/{id}/promote")
    public ResponseEntity<?> promoteToAdmin(@PathVariable String id, @RequestBody Map<String, String> payload) {
        String adminPassword = payload.get("password");
        // A senha de segurança para se tornar admin
        if (!"1234".equals(adminPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha de administrador incorreta.");
        }

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole("ADMIN");
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/validate-reset")
    public ResponseEntity<?> validateForPasswordReset(@RequestBody Map<String, String> data) {
        Optional<User> userOptional = userRepository.findByEmailAndNomeAndCpf(data.get("email"), data.get("nome"), data.get("cpf"));
        if (userOptional.isPresent()) { return ResponseEntity.ok().body("Validação bem-sucedida."); }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados não encontrados. Verifique as informações.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> data) {
        Optional<User> userOptional = userRepository.findByEmail(data.get("email"));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSenha(passwordEncoder.encode(data.get("novaSenha")));
            userRepository.save(user);
            return ResponseEntity.ok().body("Senha alterada com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }

    @GetMapping
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            if (userDetails.getNome() != null) existingUser.setNome(userDetails.getNome());
            if (userDetails.getEmail() != null) existingUser.setEmail(userDetails.getEmail());
            if (userDetails.getSenha() != null && !userDetails.getSenha().isEmpty()) {
                existingUser.setSenha(passwordEncoder.encode(userDetails.getSenha()));
            }
            if (userDetails.getIconeBase64() != null) existingUser.setIconeBase64(userDetails.getIconeBase64());
            User updatedUser = userRepository.save(existingUser);
            return ResponseEntity.ok(updatedUser);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
