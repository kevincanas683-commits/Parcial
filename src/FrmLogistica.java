import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import modelos.Envio;
import modelos.EnvioAereo;
import modelos.EnvioMaritimo;
import modelos.EnvioTerrestre;

public class FrmLogistica extends JFrame {

    public String[] encabezados = new String[] { "Código", "Cliente", "Peso", "Distancia", "Tipo", "Costo" };

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtNumero, txtCliente, txtPeso, txtDistancia;
    private JComboBox cmbTipoEnvio;

    private List<Envio> envios = new ArrayList<>();

    public FrmLogistica() {
        setSize(600, 400);
        setTitle("Operador Logísitico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tbLogistica = new JToolBar();

        JButton btnAgregarEnvio = new JButton();
        btnAgregarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/AgregarEnvio.png")));
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregarEnvio();
            }
        });
        tbLogistica.add(btnAgregarEnvio);

        JButton btnQuitarEnvio = new JButton();
        btnQuitarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/QuitarEnvio.png")));
        btnQuitarEnvio.setToolTipText("Quitar Envío");
        btnQuitarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quitarEnvio();
            }
        });
        tbLogistica.add(btnQuitarEnvio);

        // Contenedor principal de ENVIOS con BoxLayout (vertical)
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));

        // Panel 1 (oculto por defecto)
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 250)); // Altura fija de 100px
        pnlEditarEnvio.setLayout(null);

        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtNumero);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox();
        cmbTipoEnvio.setBounds(320, 10, 100, 25);
        String[] opciones = new String[] { "Terrestre", "Aéreo", "Marítimo" };
        DefaultComboBoxModel mdlTipoEnvio = new DefaultComboBoxModel(opciones);
        cmbTipoEnvio.setModel(mdlTipoEnvio);
        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(220, 40, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 40, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        JButton btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        btnGuardarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                guardarEnvio();
            }
        });
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(320, 70, 100, 25);
        btnCancelarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarEnvio();
            }
        });
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false); // Se oculta al inicio

        // Panel 2 (siempre visible)
        tblEnvios = new JTable();
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        DefaultTableModel dtm = new DefaultTableModel(null, encabezados);
        tblEnvios.setModel(dtm);

        // Agregar componentes
        pnlEnvios.add(pnlEditarEnvio);
        pnlEnvios.add(spListaEnvios);

        // JScrollPane para permitir desplazamiento si es necesario
        JScrollPane spEnvios = new JScrollPane(pnlEnvios);
        spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        getContentPane().add(tbLogistica, BorderLayout.NORTH);
        getContentPane().add(pnlEnvios, BorderLayout.CENTER);
    }

    public void agregarEnvio() {
        pnlEditarEnvio.setVisible(true);

    }

    public void quitarEnvio() {
        if (tblEnvios.getSelectedRow() >= 0) {
            envios.remove(tblEnvios.getSelectedRow());
            mostrarEnvios();
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN ENVIO");
        }

    }

    public void guardarEnvio() {
        pnlEditarEnvio.setVisible(false);
        switch (cmbTipoEnvio.getSelectedIndex()) {
            case 0:
                envios.add(new EnvioTerrestre(txtNumero.getText(), txtCliente.getText(),
                        Double.parseDouble(txtPeso.getText()), Double.parseDouble(txtDistancia.getText())));
                break;
            case 1:
                envios.add(new EnvioAereo(txtNumero.getText(), txtCliente.getText(),
                        Double.parseDouble(txtPeso.getText()), Double.parseDouble(txtDistancia.getText())));
                break;
            case 2:
                envios.add(new EnvioMaritimo(txtNumero.getText(), txtCliente.getText(),
                        Double.parseDouble(txtPeso.getText()), Double.parseDouble(txtDistancia.getText())));
                break;
        }

        mostrarEnvios();
    }

    private void mostrarEnvios() {
        String[][] datos = new String[envios.size()][encabezados.length];

        int fila = 0;
        for (Envio e : envios) {
            if (e != null) {
                datos[fila][0] = e instanceof EnvioTerrestre ? "Terrestre"
                        : e instanceof EnvioAereo ? "Aéreo" : "Marítimo";
                datos[fila][1] = e.getCodigo();
                datos[fila][2] = e.getCliente();
                datos[fila][3] = String.valueOf(e.getPeso());
                datos[fila][4] = String.valueOf(e.getDistancia());
                datos[fila][5] = String.valueOf(e.calcularTarifa());
            }
            fila++;
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblEnvios.setModel(dtm);
    }

    public void cancelarEnvio() {
        pnlEditarEnvio.setVisible(false);

    }

}
