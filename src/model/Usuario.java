package model;

import java.util.HashMap;
import java.util.Map;

public class Usuario {

	private String _nome;
	private Map<String, Integer> _pontos = new HashMap<>();

	public Usuario(String nome) {
		_nome = nome;
	}

	private void criarPontuacaoSeNaoExistir(String tipoPonto) {
		if (!_pontos.containsKey(tipoPonto))
			_pontos.put(tipoPonto, 0);
	}

	private void adicionarPontuacao(String tipoPonto) {
		_pontos.put(tipoPonto, _pontos.get(tipoPonto) + 1);
	}

	public void registrarPonto(String tipoPonto) {
		criarPontuacaoSeNaoExistir(tipoPonto);
		adicionarPontuacao(tipoPonto);
	}

	public void setPonto(String tipoPonto, Integer totalPontos) {
		_pontos.put(tipoPonto, totalPontos);
	}

	public String getNome() {
		return _nome;
	}

	public boolean temPontuacaoDe(String tipoPonto) {
		return _pontos.containsKey(tipoPonto);
	}

	public Integer getPontuacao(String tipoPonto) {
		return _pontos.get(tipoPonto);
	}

	public Map<String, Integer> getPontos() {
		return _pontos;
	}

}
