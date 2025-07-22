import java.awt.*;
import javax.swing.*;

public class GUI {
    private GerenciadorPublicacoes gerenciador;
    private DefaultListModel<Publicacao> listaModel;
    private JList<Publicacao> lista;

    public GUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel não disponível.");
        }

        gerenciador = new GerenciadorPublicacoes();

        JFrame frame = new JFrame("📚 Livraria Digital");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Sistema de Gerenciamento de Publicações", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(titulo, BorderLayout.NORTH);

        listaModel = new DefaultListModel<>();
        lista = new JList<>(listaModel);
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // ListCellRenderer para múltiplas linhas
        lista.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JTextArea area = new JTextArea(value.toString());
                area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                area.setWrapStyleWord(true);
                area.setLineWrap(true);
                area.setOpaque(true);
                area.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                area.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                area.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                return area;
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        frame.add(scrollPane, BorderLayout.CENTER);

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

        atualizarLista();
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
