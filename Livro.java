public class Livro extends Publicacao {
    public Livro(String titulo, String autor, double preco) {
        super(titulo, autor, preco);
    }

    @Override
    public String getTipo() {
        return "Livro";
    }
}