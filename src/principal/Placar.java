package principal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import armazenamento.Armazenamento;
import model.Usuario;

public class Placar {

	private Map<String, Usuario> usuarios = new HashMap<>();
	private Armazenamento _armazenamento;

	public Placar(Armazenamento armazenamento) {
		_armazenamento = armazenamento;
	}

	private void criarUsuarioSeNaoExistir(String nomeUsuario) {
		if (usuarios.get(nomeUsuario) == null)
			usuarios.put(nomeUsuario, new Usuario(nomeUsuario));
	}

	public void registrarPonto(String nomeUsuario, String tipoPonto) throws IOException {
		criarUsuarioSeNaoExistir(nomeUsuario);
		Usuario usuario = usuarios.get(nomeUsuario);
		usuario.registrarPonto(tipoPonto);
		_armazenamento.salvar(usuario);
		usuarios.put(nomeUsuario, usuario);
	}

	public Map<String, Integer> pegarTotalPontos(String nomeUsuario) {
		Usuario usuario = usuarios.get(nomeUsuario);
		if (usuario == null)
			return null;
		return usuario.getPontos();
	}

	public TreeMap<Integer, String> ranking(String tipoPonto) {
		TreeMap<Integer, String> ranking = new TreeMap<>((int1, int2) -> int2.compareTo(int1));
		usuarios.entrySet().stream().forEach(e -> {
			Usuario usuario = e.getValue();
			if (usuario.temPontuacaoDe(tipoPonto))
				ranking.put(usuario.getPontuacao(tipoPonto), usuario.getNome());
		});
		return ranking;
	}

}
