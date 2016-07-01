package principal;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import armazenamento.MockArmazenamento;

public class PlacarTest {

	private Placar placar;
	private String usuario;
	private String moeda;
	private String estrela;

	@Before
	public void SetUp() {
		placar = new Placar(new MockArmazenamento());
		usuario = "Rodrigo";
		moeda = "moeda";
		estrela = "estrela";
	}

	@Test
	public void registrarPontoUsuario() throws IOException {
		placar.registrarPonto(usuario, moeda);
		assertEquals(new Integer(1), placar.pegarTotalPontos(usuario).get(moeda));
		placar.registrarPonto(usuario, moeda);
		assertEquals(new Integer(2), placar.pegarTotalPontos(usuario).get(moeda));
	}

	@Test
	public void registrarPontoMaisDeUmUsuario() throws IOException {
		String usuario2 = "Matheus";
		placar.registrarPonto(usuario, moeda);
		placar.registrarPonto(usuario, moeda);
		assertEquals(new Integer(2), placar.pegarTotalPontos(usuario).get(moeda));

		placar.registrarPonto(usuario2, moeda);
		placar.registrarPonto(usuario2, moeda);
		placar.registrarPonto(usuario2, moeda);
		assertEquals(new Integer(3), placar.pegarTotalPontos(usuario2).get(moeda));
	}

	@Test
	public void registrarPontosDiferentes() throws IOException {

		placar.registrarPonto(usuario, moeda);
		placar.registrarPonto(usuario, moeda);

		placar.registrarPonto(usuario, estrela);
		placar.registrarPonto(usuario, estrela);
		placar.registrarPonto(usuario, estrela);
		placar.registrarPonto(usuario, estrela);
		assertEquals(new Integer(2), placar.pegarTotalPontos(usuario).get(moeda));
		assertEquals(new Integer(4), placar.pegarTotalPontos(usuario).get(estrela));
	}

	@Test
	public void pegarUsuarioInexistente() throws IOException {
		placar.registrarPonto(usuario, moeda);
		assertEquals(null, placar.pegarTotalPontos("Matheus"));
	}

	@Test
	public void pegarPontuacaoInexistente() throws IOException {
		placar.registrarPonto(usuario, moeda);
		assertEquals(null, placar.pegarTotalPontos(usuario).get("estrela"));
	}

	@Test
	public void ranking() throws IOException {
		placar.registrarPonto(usuario, moeda);
		placar.registrarPonto(usuario, moeda);
		placar.registrarPonto(usuario, moeda);
		placar.registrarPonto(usuario, moeda);

		placar.registrarPonto("Gabriel", moeda);
		placar.registrarPonto("Gabriel", moeda);
		placar.registrarPonto("Gabriel", moeda);

		placar.registrarPonto("Paula", moeda);
		placar.registrarPonto("Paula", moeda);

		placar.registrarPonto("Beatriz", moeda);
		placar.registrarPonto("Beatriz", moeda);
		placar.registrarPonto("Beatriz", moeda);
		placar.registrarPonto("Beatriz", moeda);
		placar.registrarPonto("Beatriz", moeda);
		placar.registrarPonto("Beatriz", moeda);

		placar.registrarPonto("Matheus", estrela);
		placar.registrarPonto("Matheus", estrela);
		placar.registrarPonto("Matheus", estrela);
		placar.registrarPonto("Matheus", estrela);

		placar.registrarPonto("Luciane", estrela);
		placar.registrarPonto("Luciane", estrela);
		placar.registrarPonto("Luciane", estrela);

		placar.registrarPonto("Roque", estrela);
		placar.registrarPonto("Roque", estrela);
		placar.registrarPonto("Roque", estrela);
		placar.registrarPonto("Roque", estrela);

		placar.registrarPonto("Beatriz", estrela);
		placar.registrarPonto("Beatriz", estrela);

		TreeMap<Integer, String> ranking = placar.ranking(moeda);
		assertEquals(new Integer(6), ranking.firstEntry().getKey());
		assertEquals("Beatriz", ranking.firstEntry().getValue());

		assertEquals(new Integer(2), ranking.lastEntry().getKey());
		assertEquals("Paula", ranking.lastEntry().getValue());
	}

}
