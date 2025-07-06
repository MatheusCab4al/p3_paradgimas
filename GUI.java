import java.awt.*;
import javax.swing.*;

public class GUI {
    private GerenciadorPublicacoes gerenciador;
    private DefaultListModel<Publicacao> listaModel;
    private JList<Publicacao> lista;

    public GUI() {
        // Aplicar tema moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel não disponível.");
        }

        gerenciador = new GerenciadorPublicacoes();

        JFrame frame = new JFrame("📚 Livraria Digital");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para fechar aplicação
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centraliza na tela

        // Título
        JLabel titulo = new JLabel("Sistema de Gerenciamento de Publicações", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(titulo, BorderLayout.NORTH);

        // Lista de publicações
        listaModel = new DefaultListModel<>();
        atualizarLista();
        lista = new JList<>(listaModel);
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(lista);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botões
        JButton adicionar = new JButton("➕ Adicionar");
        JButton editar = new JButton("✏️ Editar");
        JButton remover = new JButton("🗑️ Remover");

        for (JButton btn : new JButton[]{adicionar, editar, remover}) {
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(120, 35));
        }

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoes.add(adicionar);
        botoes.add(editar);
        botoes.add(remover);
        frame.add(botoes, BorderLayout.SOUTH);

        // Ações
        adicionar.addActionListener(e -> abrirFormulario(-1));
        editar.addActionListener(e -> {
            int i = lista.getSelectedIndex();
            if (i != -1) abrirFormulario(i);
        });
        remover.addActionListener(e -> {
            int i = lista.getSelectedIndex();
            if (i != -1) {
                gerenciador.remover(i);
                atualizarLista();
            }
        });

        frame.setVisible(true);
    }

    private void abrirFormulario(int index) {
        JTextField campoTitulo = new JTextField();
        JTextField campoAutor = new JTextField();
        JTextField campoPreco = new JTextField();
        String[] tipos = {"Livro", "Revista"};
        JComboBox<String> tipoBox = new JComboBox<>(tipos);

        if (index != -1) {
            Publicacao p = gerenciador.getPublicacoes().get(index);
            campoTitulo.setText(p.getTitulo());
            campoAutor.setText(p.getAutor());
            campoPreco.setText(String.valueOf(p.getPreco()));
            tipoBox.setSelectedItem(p.getTipo());
        }

        Object[] msg = {
            "Tipo:", tipoBox,
            "Título:", campoTitulo,
            "Autor:", campoAutor,
            "Preço:", campoPreco,
        };

        int opcao = JOptionPane.showConfirmDialog(null, msg, "Formulário", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            String titulo = campoTitulo.getText();
            String autor = campoAutor.getText();
            double preco = Double.parseDouble(campoPreco.getText());
            String tipo = (String) tipoBox.getSelectedItem();

            Publicacao nova;
            if (tipo.equals("Livro")) {
                nova = new Livro(titulo, autor, preco);
            } else {
                nova = new Revista(titulo, autor, preco);
            }

            if (index == -1)
                gerenciador.adicionar(nova);
            else
                gerenciador.alterar(index, nova);

            atualizarLista();
        }
    }

    private void atualizarLista() {
        listaModel.clear();
        for (Publicacao p : gerenciador.getPublicacoes()) {
            listaModel.addElement(p);
        }
    }
}
