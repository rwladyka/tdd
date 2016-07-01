package exception;

public class PontuacaoNaoRegistradaException extends RuntimeException {
	private static final long serialVersionUID = -1136775904159265317L;

	public PontuacaoNaoRegistradaException(String msg) {
		super(msg);
	}

}
