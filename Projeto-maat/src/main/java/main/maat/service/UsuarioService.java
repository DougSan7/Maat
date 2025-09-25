package main.maat.service;

import main.maat.DAO.UsuarioDAO;
import main.maat.model.Usuario;
import java.util.List;

public class UsuarioService {

    // Instância do DAO para se comunicar com o banco de dados
    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Método de autenticação (valida o login e senha)
    public Usuario autenticar(String login, String senha) {
        // Busca o usuário pelo login (não pelo email, pois o login é o campo que você usa para autenticar)
        Usuario usuario = usuarioDAO.buscarPorLogin(login);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário ou senha incorretos.");
        }

        // Verifica se a senha fornecida corresponde à senha no banco de dados
        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Usuário ou senha incorretos.");
        }

        return usuario;
    }

    // Método de serviço para salvar um novo usuário
    public void salvar(Usuario usuario) {
        // Aqui você pode adicionar validações de negócio, por exemplo:
        // if (usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
        //     throw new IllegalArgumentException("E-mail já cadastrado.");
        // }
        usuarioDAO.salvar(usuario);
    }

    // Método de serviço para atualizar um usuário
    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    // Método de serviço para remover um usuário
    public void remover(int id) {
        usuarioDAO.remover(id);
    }

    // Método de serviço para buscar um usuário por ID
    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    // Método de serviço para listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }
}