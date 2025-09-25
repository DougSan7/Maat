package main.maat;

import main.maat.model.Usuario;
import main.maat.service.UsuarioService;

import java.util.List;

public class TesteUsuarioService {

    public static void main(String[] args) {
        // Crie uma instância do Serviço
        UsuarioService usuarioService = new UsuarioService();

        // 1. Testando o método 'salvar' do serviço
        System.out.println("--- Testando a criação de um usuário ---");
        Usuario novoUsuario = new Usuario("Bruno Viana", "987.654.321-00", "bruno.viana@teste.com", "Desenvolvedor", "bruno.viana", "senha456", "Colaborador");
        usuarioService.salvar(novoUsuario);
        System.out.println("Usuário criado com sucesso. ID: " + novoUsuario.getId());

        // 2. Testando o método 'autenticar' do serviço
        System.out.println("\n--- Testando a autenticação ---");
        try {
            Usuario usuarioAutenticado = usuarioService.autenticar("bruno.viana", "senha456");
            System.out.println("Autenticação bem-sucedida para o usuário: " + usuarioAutenticado.getNomeCompleto());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
        }

        // 3. Testando a autenticação com dados incorretos
        System.out.println("\n--- Testando autenticação com dados incorretos ---");
        try {
            usuarioService.autenticar("bruno.viana", "senha_errada");
        } catch (IllegalArgumentException e) {
            System.err.println("Teste de erro de autenticação: " + e.getMessage());
        }
    }
}