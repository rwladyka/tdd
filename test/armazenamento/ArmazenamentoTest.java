package armazenamento;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import model.Usuario;

public class ArmazenamentoTest {

	@Test
	public void gravarPontos() throws IOException {
		Armazenamento arm = new Armazenamento();
		Usuario usuario = new Usuario("UserTest");
		usuario.registrarPonto("estrela");
		usuario.registrarPonto("estrela");
		usuario.registrarPonto("estrela");
		usuario.registrarPonto("moeda");
		usuario.registrarPonto("moeda");
		arm.salvar(usuario);
	}

	@Test
	public void todosUsuariosComPontosRegistrados() throws IOException {
		Armazenamento arm = new Armazenamento();
		List<Usuario> usuarios = arm.carregarUsuarios();
		assertEquals(2, usuarios.size());
	}

	@Test
	public void pontosDeUmtipoPorUsuario() throws IOException {
		Armazenamento arm = new Armazenamento();
		assertEquals(new Integer(2), arm.totalPontos("UserTest", "moeda"));
	}

	@Test
	public void carregarPontos() throws IOException {
		Armazenamento arm = new Armazenamento();
		Usuario usuario = new Usuario("UserTest");
		Map<String, Integer> pontos = arm.carregarPontosUsuario(usuario);
		assertEquals(new Integer(2), pontos.get("moeda"));
		assertEquals(new Integer(3), pontos.get("estrela"));
	}

}
