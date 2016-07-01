package armazenamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Usuario;

public class Armazenamento {

	private String path = "armazenamento" + File.separator;

	private File getFile(String nomeUsuario) throws IOException {
		File file = new File(path + nomeUsuario);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	public void salvar(Usuario usuario) throws IOException {
		File file = getFile(usuario.getNome());
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		Map<String, Integer> pontuacao = usuario.getPontos();
		for (String ponto : pontuacao.keySet()) {
			writer.write(ponto + ":" + pontuacao.get(ponto));
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}

	public List<Usuario> carregarUsuarios() throws IOException {
		List<Usuario> usuarios = new ArrayList<>();
		File[] files = new File(path).listFiles();
		for (int i = 0; i < files.length; i++) {
			usuarios.add(new Usuario(files[i].getName()));
		}
		return usuarios;
	}

	public Map<String, Integer> carregarPontosUsuario(Usuario usuario) throws IOException {
		File file = getFile(usuario.getNome());
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String data = null;
		while ((data = reader.readLine()) != null) {
			String[] dados = data.split(":");
			usuario.setPonto(dados[0], new Integer(dados[1]));
		}
		reader.close();
		return usuario.getPontos();
	}

	public Integer totalPontos(String nomeUsuario, String tipoPontuacao) throws NumberFormatException, IOException {
		return carregarPontosUsuario(new Usuario(nomeUsuario)).get(tipoPontuacao);
	}

}
