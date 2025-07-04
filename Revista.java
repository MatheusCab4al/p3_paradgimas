public class Revista extends Publicacao {
    public Revista(String titulo, String autor, double preco) {
        super(titulo, autor, preco);
    }

    @Override
    public String getTipo() {
        return "Revista";
    }
}