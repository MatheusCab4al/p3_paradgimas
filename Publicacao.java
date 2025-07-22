import java.io.Serializable;

public abstract class Publicacao implements Serializable {
    protected String titulo;
    protected String autor;
    protected double preco;

    public Publicacao(String titulo, String autor, double preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    public abstract String getTipo();

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
public String toString() {
    return "Título: " + titulo +
           "\nAutor: " + autor +
           "\nPreço: R$ " + String.format("%.2f", preco);
}
}