package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dao.MochilasDao;
import mochilas.Mochilas;


public class telamochila extends JFrame implements ActionListener, ItemListener {

	
	private static final long serialVersionUID = 1L;
	static String IMAGES;
	private JFrame frame;
	private JComboBox tipo_box;
	private JComboBox box_marca;
	private JLabel precomarca;
	private JLabel precotamanho;
	private JLabel lblTotal;
	private JSpinner qtde_mochila;
	private JPanel passo2;
	private Hashtable<Object, Object> marcas = new Hashtable<Object, Object>();
	private JTextField txtPackaggio;
	public float m,t,v,s;
	private JTextField Nome;
	private JLabel imagem2;
	private JTextField nome_consulta;
	private JTable table;
	private Object[][] data;
	private JLabel imagem;
	private JLabel valorunitario2;
	private JLabel Total2;
	private JLabel qtde_mochila2;
	
	public static void main(String[] args) {
		try {
			System.out.println("/  -> " + new File("/TrabalhoMochila/imagens.java").getCanonicalPath());
			System.out.println(System.getProperty("user.dir.imagens.java"));
			IMAGES = System.getProperty("user.dir");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(IMAGES);
		telamochila fr= new telamochila();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telamochila window = new telamochila();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}


	public telamochila() {
		initialize();
		frame.getContentPane().setLayout(null);
		

		txtPackaggio = new JTextField();
		txtPackaggio.setForeground(new Color(255, 255, 255));
		txtPackaggio.setBackground(new Color(0, 128, 0));
		txtPackaggio.setText("Packaggio");
		txtPackaggio.setEditable(false);
		txtPackaggio.setFont(new Font("Modern No. 20", Font.BOLD, 45));
		txtPackaggio.setBounds(204, 30, 214, 58);
		frame.getContentPane().add(txtPackaggio);
		txtPackaggio.setColumns(10);
		
		precomarca = new JLabel("");
		precomarca.setBounds(31, 22, -62, 0);
		frame.getContentPane().add(precomarca);
		precomarca.setForeground(SystemColor.inactiveCaptionBorder);
		precomarca.setBackground(SystemColor.inactiveCaptionBorder);
		
		precotamanho = new JLabel("");
		precotamanho.setBounds(64, -2, 0, 0);
		frame.getContentPane().add(precotamanho);
		precotamanho.setEnabled(false);
		precotamanho.setForeground(Color.LIGHT_GRAY);
		precotamanho.setBackground(SystemColor.inactiveCaptionBorder);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 107, 598, 399);
		frame.getContentPane().add(tabbedPane);
		
		JPanel passo1 = new JPanel();
		passo1.setForeground(new Color(0, 128, 0));
		passo1.setBackground(new Color(204, 255, 204));
		tabbedPane.addTab("Mochilas", null, passo1, null);
		passo1.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 69, 41, 14);
		passo1.add(lblTipo);
		lblTipo.setForeground(new Color(0, 128, 0));
		lblTipo.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 31, 55, 14);
		passo1.add(lblNome);
		lblNome.setForeground(new Color(0, 128, 0));
		lblNome.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		
		Nome = new JTextField();
		Nome.setBounds(75, 26, 262, 26);
		passo1.add(Nome);
		Nome.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		Nome.setColumns(10);
		
		JLabel lblValorUnitrio = new JLabel("Valor Unit\u00E1rio R$:");
		lblValorUnitrio.setBounds(10, 293, 135, 14);
		passo1.add(lblValorUnitrio);
		lblValorUnitrio.setForeground(new Color(0, 128, 0));
		lblValorUnitrio.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		
		JLabel totalunitario = new JLabel("");
		totalunitario.setFont(new Font("Tahoma", Font.BOLD, 13));
		totalunitario.setBounds(155, 285, 111, 32);
		passo1.add(totalunitario);
		totalunitario.setEnabled(false);
		
				
				JLabel lblTotal_1 = new JLabel("Total R$:");
				lblTotal_1.setBounds(10, 327, 66, 17);
				passo1.add(lblTotal_1);
				lblTotal_1.setForeground(new Color(0, 128, 0));
				lblTotal_1.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		
		
		lblTotal = new JLabel("");
		lblTotal.setBounds(80, 318, 101, 32);
		passo1.add(lblTotal);
		lblTotal.setEnabled(false);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBackground(Color.WHITE);
		
		String[] tipos= {"Selecione", "Executivo", "Esportivo", "Couro","Escolar","Otaku","Dc","Marvel"};
		tipo_box = new JComboBox(tipos);
		tipo_box.setBounds(75, 66, 143, 23);
		tipo_box.addActionListener(this);
		tipo_box.addItemListener(this);
		passo1.add(tipo_box);
		tipo_box.setFont(new Font("Modern No. 20", Font.PLAIN, 14));
		tipo_box.setBackground(new Color(240, 255, 240));

		
		JLabel Modelo = new JLabel("Modelo:");
		Modelo.setBounds(10, 109, 56, 14);
		Modelo.setForeground(new Color(0, 128, 0));
		Modelo.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		passo1.add(Modelo);
		
		box_marca = new JComboBox();
		box_marca.setFont(new Font("Modern No. 20", Font.PLAIN, 14));
		box_marca.setBounds(75, 106, 143, 22);
		passo1.add(box_marca);
		box_marca.setBackground(new Color(240, 255, 240));
		box_marca.setForeground(new Color(0, 0, 0));
        String[] marcas1 = {"Selecione","Hustle","OSFA","Oakley-Exec."};
        marcas.put(tipos[1], marcas1);
        String[] marcas2 = {"Selecione","Baijia","Bagaggio","Oakley-Espo."};
        marcas.put(tipos[2], marcas2);
		String[] marcas3 = {"Selecione","Travel Max","Swissland","Rafi"};
        marcas.put(tipos[3], marcas3);
        String[] marcas4 = {"Selecione","Hannah Montana","Ben 10","Hello Kitty","Snoopy","Johnny Bravo","Elza","Dora a Aventureira"};
        marcas.put(tipos[4], marcas4);
        String[] marcas5 = {"Selecione","Naruto","DragonBall","Digimon","Pokemon","Sonic","Yoda"};
        marcas.put(tipos[5], marcas5);
        String[] marcas6 = {"Selecione","Batman","Superman","Flash","Mulher-Maravilha","Caçador Marciano","Mulher Gavião","Lanterna Verde"};
        marcas.put(tipos[6], marcas6);
        String[] marcas7 = {"Selecione","Capitão America","Homem de Ferro","Thor","Hulk","Vespa","Homem-Formiga","Gavião Arqueiro",
        		"Viuva Negra","Pantera Negra"};
        marcas.put(tipos[7], marcas7);
        box_marca.addItemListener(this);
		box_marca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item3 = (String) box_marca.getSelectedItem();
				if(item3.equals("Travel Max")) {
				    	precomarca.setText(String.valueOf(199.99));
				    	imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/travelmax.jpg"));
				    	imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/travelmax.jpg"));
				}
				else if(item3.equals("Swissland")) {
						precomarca.setText(String.valueOf(169.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/swissland.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/swissland.jpg"));
						
				}
				else if (item3.equals("Rafi")) {
						precomarca.setText(String.valueOf(179.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/Rafi.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/Rafi.jpg"));
				}
				else if (item3.equals("Hustle")) {
						precomarca.setText(String.valueOf(159.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/hustle.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/hustle.jpg"));
				}
				else if (item3.equals("OSFA")) {
						precomarca.setText(String.valueOf(99.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/OSFA.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/OSFA.jpg"));
				}
				else if (item3.equals("Oakley-Exec.")) {
						precomarca.setText(String.valueOf(189.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/oakleyexec.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/oakleyexec.jpg"));
				}
				else if (item3.equals("Baijia")) {
						precomarca.setText(String.valueOf(179.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/baijia.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/baijia.jpg"));
				}
				else if (item3.equals("Bagaggio")) {
						precomarca.setText(String.valueOf(299.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/bagaggio.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/bagaggio.jpg"));
				}

				else if(item3.equals("Oakley-Espo.")) {
						precomarca.setText(String.valueOf(179.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/oakleyesp.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/oakleyesp.jpg"));
				}
				
				else if(item3.equals("Hannah Montana")) {
						precomarca.setText(String.valueOf(79.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/hannahmontana.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/hannahmontana.jpg"));
				}
				
				else if(item3.equals("Ben 10")) {
						precomarca.setText(String.valueOf(89.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/ben10.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/ben10.jpg"));
				}
				
				else if(item3.equals("Hello Kitty")) {
						precomarca.setText(String.valueOf(69.99));
						imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/hellokitty.jpg"));
						imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/hellokitty.jpg"));
				}
				
				else if(item3.equals("Snoopy")) {
					precomarca.setText(String.valueOf(399.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/snoopy.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/snoopy.jpg"));
				}
				
				else if(item3.equals("Johnny Bravo")) {
					precomarca.setText(String.valueOf(89.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/johnnybravo.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/johnnybravo.jpg"));
				}
				
				else if(item3.equals("Elza")) {
					precomarca.setText(String.valueOf(399.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/elza.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/elza.jpg"));
				}
				
				else if(item3.equals("Naruto")) {
					precomarca.setText(String.valueOf(599.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/naruto.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/naruto.jpg"));
				}
				
				else if(item3.equals("DragonBall")) {
					precomarca.setText(String.valueOf(799.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/goku.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/goku.jpg"));
				}
				
				else if(item3.equals("Digimon")) {
					precomarca.setText(String.valueOf(899.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/digimon.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/digimon.jpg"));
				}
				
				else if(item3.equals("Pokemon")) {
					precomarca.setText(String.valueOf(1000.00));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/pokemon.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/pokemon.jpg"));
				}
				
				else if(item3.equals("Sonic")) {
					precomarca.setText(String.valueOf(49.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/sonic.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/sonic.jpg"));
				}
				
				else if(item3.equals("Batman")) {
					precomarca.setText(String.valueOf(5999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/batman.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/batman.jpg"));
				}
				
				else if(item3.equals("Superman")) {
					precomarca.setText(String.valueOf(199.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/superman.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/superman.jpg"));
				}
				
				else if(item3.equals("Flash")) {
					precomarca.setText(String.valueOf(399.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/flash.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/flash.jpg"));
				}
				
				else if(item3.equals("Mulher-Maravilha")) {
					precomarca.setText(String.valueOf(9999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/mulherona.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/mulherona.jpg"));
				}
				
				else if(item3.equals("Caçador Marciano")) {
					precomarca.setText(String.valueOf(799.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/marshunter.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/marshunter.jpg"));
				}
				
				else if(item3.equals("Mulher Gavião")) {
					precomarca.setText(String.valueOf(899.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/hawkwoman.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/hawkwoman.jpg"));
				}
				
				else if(item3.equals("Lanterna Verde")) {
					precomarca.setText(String.valueOf(999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/greenlantern.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/greenlantern.jpg"));
				}
				
				else if(item3.equals("Capitão America")) {
					precomarca.setText(String.valueOf(799.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/capamerica.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/capamerica.jpg"));
				}
				
				else if(item3.equals("Homem de Ferro")) {
					precomarca.setText(String.valueOf(9.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/ironman.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/ironman.jpg"));
				}
				
				else if(item3.equals("Thor")) {
					precomarca.setText(String.valueOf(3999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/thor.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/thor.jpg"));
				}
				
				else if(item3.equals("Hulk")) {
					precomarca.setText(String.valueOf(899.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/hulk.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/hulk.jpg"));
				}
				
				else if(item3.equals("Vespa")) {
					precomarca.setText(String.valueOf(7999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/wasp.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/wasp.jpg"));
				}
				
				else if(item3.equals("Homem-Formiga")) {
					precomarca.setText(String.valueOf(4999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/antman.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/antman.jpg"));
				}
				else if(item3.equals("Gavião Arqueiro")) {
					precomarca.setText(String.valueOf(689.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/hawkeye.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/hawkeye.jpg"));
				}
				
				else if(item3.equals("Viuva Negra")) {
					precomarca.setText(String.valueOf(8999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/blackwidow.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/blackwidow.jpg"));
				}
				
				else if(item3.equals("Pantera Negra")) {
					precomarca.setText(String.valueOf(31999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/blackpanther.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/blackpanther.jpg"));
				}
				else if(item3.equals("Yoda")) {
					precomarca.setText(String.valueOf(9999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/yoda.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/yoda.jpg"));
				}
				else if(item3.equals("Dora a Aventureira")) {
					precomarca.setText(String.valueOf(9999.99));
					imagem2.setIcon(new ImageIcon(IMAGES+"/imagens.java/dora.jpg"));
					imagem.setIcon(new ImageIcon(IMAGES+"/imagens.java/dora.jpg"));
				}
				
				v =(float) Float.parseFloat(precomarca.getText());
				valorunitario2.setText(Float.toString(v));
				totalunitario.setText(Float.toString(v));
				qtde_mochila.setValue(0);
				int j =(int) qtde_mochila.getValue();
				if(j > 1) {
					qtde_mochila.setValue(0);
				}
				
			}
			
			});
		
		JComboBox box_size = new JComboBox();
		box_size.setBounds(239, 66, 98, 22);
		passo1.add(box_size);
		box_size.setBackground(new Color(240, 255, 240));
		box_size.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(box_size.getSelectedItem().equals("Pequeno")) {
					precotamanho.setText(String.valueOf(20.00));
					qtde_mochila.setValue(0);
				}
				if(box_size.getSelectedItem().equals("Medio")) {
					precotamanho.setText(String.valueOf(35.00));
					qtde_mochila.setValue(0);
				}
				if(box_size.getSelectedItem().equals("Grande")) {
					precotamanho.setText(String.valueOf(50.00));
					qtde_mochila.setValue(0);
				}
			}
		});
		box_size.setFont(new Font("Modern No. 20", Font.PLAIN, 14));
		box_size.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Pequeno", "Medio", "Grande"}));
		
		///*tabela de precos
		JTextPane txtpnobs1 = new JTextPane();
		txtpnobs1.setBounds(8, 138, 148, 76);
		passo1.add(txtpnobs1);
		txtpnobs1.setEditable(false);
		txtpnobs1.setFont(new Font("Modern No. 20", Font.PLAIN, 13));
		txtpnobs1.setText("Aten\u00E7\u00E3o! De acordo com o tamanho que escolher, haver\u00E1 um acr\u00E9ssimo no valor da mochila:");
		
		JTextPane txtpnTamanho = new JTextPane();
		txtpnTamanho.setBounds(151, 139, 148, 23);
		passo1.add(txtpnTamanho);
		txtpnTamanho.setEditable(false);
		txtpnTamanho.setToolTipText("");
		txtpnTamanho.setFont(new Font("Modern No. 20", Font.PLAIN, 13));
		txtpnTamanho.setText("| Tamanho ||  Pre\u00E7o      |");
		
		JTextPane txtpnPequeno = new JTextPane();
		txtpnPequeno.setBounds(151, 157, 148, 23);
		passo1.add(txtpnPequeno);
		txtpnPequeno.setToolTipText("");
		txtpnPequeno.setText("| Pequeno   ||  R$20,00 |");
		txtpnPequeno.setFont(new Font("Modern No. 20", Font.PLAIN, 13));
		txtpnPequeno.setEditable(false);
		
		JTextPane txtpnMdio = new JTextPane();
		txtpnMdio.setBounds(151, 173, 148, 23);
		passo1.add(txtpnMdio);
		txtpnMdio.setToolTipText("");
		txtpnMdio.setText("| M\u00E9dio       ||  R$35,00 |");
		txtpnMdio.setFont(new Font("Modern No. 20", Font.PLAIN, 13));
		txtpnMdio.setEditable(false);
		
		JTextPane txtpnGrande = new JTextPane();
		txtpnGrande.setBounds(151, 191, 148, 23);
		passo1.add(txtpnGrande);
		txtpnGrande.setToolTipText("");
		txtpnGrande.setText("| Grande     ||  R$50,00 |");
		txtpnGrande.setFont(new Font("Modern No. 20", Font.PLAIN, 13));
		txtpnGrande.setEditable(false);
				
					JLabel lblQuantidade = new JLabel("Quantidade:");
					lblQuantidade.setBounds(10, 242, 90, 14);
					passo1.add(lblQuantidade);
					lblQuantidade.setForeground(new Color(0, 128, 0));
					lblQuantidade.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
					
        
					qtde_mochila = new JSpinner();
					qtde_mochila.setBounds(110, 234, 56, 31);
					passo1.add(qtde_mochila);
					qtde_mochila.setFont(new Font("Tahoma", Font.PLAIN, 14));
					qtde_mochila.setBackground(new Color(240, 255, 240));
					qtde_mochila.setValue(0);
					qtde_mochila.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent arg0) {
							int j =(int) qtde_mochila.getValue();
							if (j<0) {
								qtde_mochila.setValue(0);
								j=0;
							}
							else if(j>99) {
								qtde_mochila.setValue(99);
								j=99;
							}
							
							s =Float.parseFloat(precotamanho.getText());
							m =Float.parseFloat(totalunitario.getText());
							t =(s + m) * j;
							DecimalFormat tm= new DecimalFormat();
							Total2.setText((tm.format(t)));
							lblTotal.setText((tm.format(t)));
						}
					});
					
					JLabel lblModeloVisual = new JLabel("Modelo visual:");
					lblModeloVisual.setForeground(new Color(0, 128, 0));
					lblModeloVisual.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
					lblModeloVisual.setBounds(311, 105, 95, 23);
					passo1.add(lblModeloVisual);
					
					imagem = new JLabel("");
					imagem.setBounds(309, 126, 268, 218);
					passo1.add(imagem);
					
					
			
					

		passo2 = new JPanel();
		passo2.setBackground(new Color(204, 255, 204));
		tabbedPane.addTab("Pagamento", null, passo2, null);
		passo2.setLayout(null);
		
		JComboBox pagamento = new JComboBox();
		pagamento.setBounds(177, 220, 106, 22);
		passo2.add(pagamento);
		pagamento.setFont(new Font("Modern No. 20", Font.PLAIN, 14));
		pagamento.setModel(new DefaultComboBoxModel(new String[] {"Bitcoin", "Boleto", "Vale Refei\u00E7\u00E3o", "Vale-Presente"}));
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(40, 337, 105, 23);
		passo2.add(btnConcluir);
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Nome.getText()==null || Nome.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "Por favor digite seu nome.");
				}
				else if(tipo_box.getSelectedItem()=="Selecione") {
					JOptionPane.showMessageDialog(null, "Não existe tipo mochila 'Selecione'.");
					
				}
				else if(box_marca.getSelectedItem()=="Selecione") {
					JOptionPane.showMessageDialog(null, "Não existe modelo mochila 'Selecione'.");
					
				}
				else if ((int) qtde_mochila.getValue()== 0) {
					JOptionPane.showMessageDialog(null, "Compre pelo menos uma mochila.");
				}
				
				else {	
					JOptionPane.showMessageDialog(null, "Compra Concluida!");
				System.out.println(Nome.getText());
					Mochilas p = new Mochilas();
					p.setNome(Nome.getText());
					p.setModelo(box_marca.getSelectedItem().toString());
					p.setQuantidade(Integer.parseInt(qtde_mochila.getValue().toString()));
					p.setValor(t);
					p.setTipo(tipo_box.getSelectedItem().toString());
					p.setPagamento(pagamento.getSelectedItem().toString());
		
					MochilasDao dao = new MochilasDao();
					try {
						dao.insereProducao(p);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
			    }
				}
			}
		});
		btnConcluir.setForeground(new Color(0, 128, 0));
		btnConcluir.setBackground(new Color(240, 255, 240));
		btnConcluir.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(195, 337, 105, 23);
		passo2.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setForeground(new Color(0, 128, 0));
		btnCancelar.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		btnCancelar.setBackground(new Color(240, 255, 240));
		
		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento:");
		lblFormaDePagamento.setBounds(10, 219, 150, 23);
		passo2.add(lblFormaDePagamento);
		lblFormaDePagamento.setForeground(new Color(0, 128, 0));
		lblFormaDePagamento.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		
		JLabel lblModeloVisual2 = new JLabel("Modelo visual:");
		lblModeloVisual2.setForeground(new Color(0, 128, 0));
		lblModeloVisual2.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblModeloVisual2.setBounds(315, 69, 95, 23);
		passo2.add(lblModeloVisual2);
		
		imagem2 = new JLabel("");
		imagem2.setBounds(313, 103, 268, 218);
		passo2.add(imagem2);
		
		JLabel valoruni2 = new JLabel("Valor Unit\u00E1rio R$:");
		valoruni2.setForeground(new Color(0, 128, 0));
		valoruni2.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		valoruni2.setBounds(10, 61, 135, 14);
		passo2.add(valoruni2);
		
		valorunitario2 = new JLabel("");
		valorunitario2.setFont(new Font("Tahoma", Font.BOLD, 13));
		valorunitario2.setEnabled(false);
		valorunitario2.setBounds(148, 52, 128, 32);
		passo2.add(valorunitario2);
		
		JLabel lbltotal2 = new JLabel("Total R$:");
		lbltotal2.setForeground(new Color(0, 128, 0));
		lbltotal2.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		lbltotal2.setBounds(10, 111, 66, 17);
		passo2.add(lbltotal2);
		
		Total2 = new JLabel("");
		Total2.setFont(new Font("Tahoma", Font.BOLD, 13));
		Total2.setEnabled(false);
		Total2.setBackground(Color.WHITE);
		Total2.setBounds(83, 103, 150, 32);
		passo2.add(Total2);
		
		JButton btnConsultar = new JButton("Consultar dados");
		btnConsultar.setBounds(421, 337, 160, 23);
		passo2.add(btnConsultar);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						new Consulta();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
			}
		});
		btnConsultar.setForeground(new Color(0, 128, 0));
		btnConsultar.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		
		
		
	}

	
	
	public void actionPerformed(ActionEvent tipos_marcas) {

        String item = (String) tipo_box.getSelectedItem();
        Object o = marcas.get(item);
        if (o == null) {
            box_marca.setModel(new DefaultComboBoxModel());
        } else {
            box_marca.setModel(new DefaultComboBoxModel((String[]) o));
        }

	}
    
	   private class FirstDialog extends JDialog {

	        private static final long serialVersionUID = 1L;

	        FirstDialog(final Frame parent, String winTitle, String msgString) {
	            super(parent, winTitle);
	            setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	            JLabel myLabel = new JLabel(msgString);
	            myLabel.setForeground(new Color(60, 179, 113));
	            myLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
	            JButton bNext = new JButton("Parar Processo");
	            bNext.setForeground(new Color(60, 179, 113));
	    		bNext.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
	    		bNext.setBackground(new Color(240, 255, 240));
	            
	            
	            add(myLabel, BorderLayout.CENTER);
	            add(bNext, BorderLayout.SOUTH);
	            bNext.addActionListener(new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent evt) {
	                    setVisible(false);
	                }
	            });
	            javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    setVisible(false);
	                }
	            });
	            t.setRepeats(false);
	            t.start();
	            setLocationRelativeTo(parent);
	            setSize(new Dimension(400, 100));
	            setVisible(true);
	        }
	    }
	   
	public void itemStateChanged(ItemEvent tipos_marcas) {
		if (tipos_marcas.getStateChange() == ItemEvent.SELECTED) {
            if (tipos_marcas.getSource() == tipo_box) {
                if (tipo_box.getSelectedIndex() != 0) {
                    FirstDialog firstDialog = new FirstDialog(telamochila.this,
                            tipo_box.getSelectedItem().toString(), "Por favor espere,  Procurando por ..... ");
                }
            } 
        }
		
		// TODO Auto-generated method stub
		
	}
		
	public Hashtable<Object, Object> getBox_marca() {
		return marcas;
	}
	

	
    private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		frame.setBackground(new Color(128, 128, 128));
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 634, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
	}


