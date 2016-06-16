package visão;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import abordagem.aEstrela.Aestrela;
import abordagem.buscaHeuristica.Gulosa;
import abordagem.forcaBruta.estruturaDeDados.Largura;
import abordagem.forcaBruta.estruturaDeDados.profundidade.BuscaProfundidade;
import modelo.Tabuleiro;
import modelo.Unidade;

@SuppressWarnings("serial")
public class TelaRelatorio extends JFrame {

	private JPanel contentPane;
	ArrayList<Tabuleiro> todos;
	Unidade matriz[][];
	JPanel panel_1;
	int altura;
	JPanel panel;
	
	ArrayList<Tabuleiro> listaProfundidade;
	ArrayList<Tabuleiro> listaLargura;
	ArrayList<Tabuleiro> listaGuloso;
	ArrayList<Tabuleiro> listaAEstrela;
	
	JLabel lblLarguraSituacao;
	JLabel lblProfundidadeSituacao;
	JLabel lblGulosoSituacao;
	JLabel lblAEstrelaSituacao;
	
	
	/**
	 * Create the frame.
	 */
	public TelaRelatorio(Unidade m[][], int a) {
		
		matriz = m;
		altura = a;
		
		setResizable(false);
		textAreaGuloso = new TextArea();
		textAreaGuloso.setBounds(10, 10, 464, 217);
		textAreaLargura = new TextArea();
		textAreaLargura.setBounds(10, 10, 464, 217);
		textAreaProfundidade = new TextArea();
		textAreaProfundidade.setBounds(10, 10, 464, 217);
		textAreaAEstrela = new TextArea();
		textAreaAEstrela.setBounds(10, 10, 464, 217);
		
		setTitle("Puzzle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 0, 514, 315);
		contentPane.add(tabbedPane);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Execução", null, panel, null);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Tabuleiro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 278, 267);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblInformaes = new JLabel("Informações :");
		lblInformaes.setBounds(298, 11, 101, 14);
		panel.add(lblInformaes);
		
		lblAlturaDefinidaPara = new JLabel("Altura definida para busca :");
		lblAlturaDefinidaPara.setBounds(298, 36, 189, 14);
		panel.add(lblAlturaDefinidaPara);
		
		label_2 = new JLabel(altura+"");
		label_2.setBounds(458, 36, 29, 14);
		panel.add(label_2);
		
		JLabel lblLargura = new JLabel("Largura : ");
		lblLargura.setBounds(298, 61, 101, 14);
		panel.add(lblLargura);
		
		JLabel lblProfundidade = new JLabel("Profundidade :");
		lblProfundidade.setBounds(298, 86, 101, 14);
		panel.add(lblProfundidade);
		
		JLabel lblGuloso = new JLabel("Guloso :");
		lblGuloso.setBounds(298, 111, 101, 14);
		panel.add(lblGuloso);
		
		JLabel lblA = new JLabel("A* :");
		lblA.setBounds(298, 136, 101, 14);
		panel.add(lblA);
		
		lblLarguraSituacao = new JLabel("Executando...");
		lblLarguraSituacao.setBounds(392, 61, 95, 14);
		panel.add(lblLarguraSituacao);
		
		lblProfundidadeSituacao = new JLabel("Executando...");
		lblProfundidadeSituacao.setBounds(392, 86, 95, 14);
		panel.add(lblProfundidadeSituacao);
		
		lblGulosoSituacao = new JLabel("Executando...");
		lblGulosoSituacao.setBounds(392, 111, 95, 14);
		panel.add(lblGulosoSituacao);
		
		lblAEstrelaSituacao = new JLabel("Executando...");
		lblAEstrelaSituacao.setBounds(392, 136, 95, 14);
		panel.add(lblAEstrelaSituacao);
		
		
		//Preencher a area de texto de cada algoritmo
		new Thread(threadGuloso).start();
		new Thread(threadLargura).start();
		new Thread(threadProfundidade).start();
		new Thread(threadAEstrela).start();

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Relatório", null, panel_2, null);
		panel_2.setLayout(null);

		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
		table.setModel(new DefaultTableModel(
				new Object[][] { { " Algoritmo", " Tempo de Execu\u00E7\u00E3o", "N\u00F3s expandidos" },
						{ " Profundidade", "x", "y" }, { " Largura", "x", "y" }, { " Guloso", "x", "y" },
						{ " A*", "x", "y" }, },
				new String[] { "Algoritmo", " Tempo de Execu\u00E7\u00E3o", "N\u00F3s expandidos" }));
		
	
		table.getColumnModel().getColumn(0).setPreferredWidth(135);
		table.getColumnModel().getColumn(1).setPreferredWidth(169);
		table.getColumnModel().getColumn(2).setPreferredWidth(127);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setToolTipText("Tabela\r\n");
		table.setBounds(20, 55, 468, 80);

		panel_2.add(table);
		
		lblRelatrioTcnico = new JLabel("Relatório Informativo");
		lblRelatrioTcnico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRelatrioTcnico.setBounds(168, 11, 191, 33);
		panel_2.add(lblRelatrioTcnico);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Algoritmo", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 11, 489, 265);
		panel_3.add(tabbedPane_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Profundidade", null, panel_4, null);
		panel_4.setLayout(null);
		
		
		panel_4.add(textAreaProfundidade);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Largura", null, panel_5, null);
		panel_5.setLayout(null);
		
		
		panel_5.add(textAreaLargura);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Guloso", null, panel_6, null);
		panel_6.setLayout(null);
		
		panel_6.add(textAreaGuloso);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		tabbedPane_1.addTab("A*", null, panel_7, null);
		panel_7.setLayout(null);
		
		
		panel_7.add(textAreaAEstrela);
		
		btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaInicial inicial = new TelaInicial();
				dispose();
				inicial.show();
			}
		});
		btnNewButton_1.setBounds(334, 337, 126, 23);
		contentPane.add(btnNewButton_1);

		// for(Tabuleiro aux :todos){
		// for (int l = 0; l < 3; l++) {
		// for (int c = 0; c < 3; c++) {
		// System.out.print(aux.getMatriz()[l][c].getInf());
		// }
		// System.out.println();
		// }
		// }
	}

	private Runnable thread3x3 = new Runnable() {
		public void run() {
			
			
			label_1 = new JLabel("");
			
				
			label_1.setBounds(473, 30, 26, 14);
			panel.add(label_1);
			panel.repaint();
			for (int qnt = 0; qnt < todos.size(); qnt++) {
				panel_1.removeAll();
				for (int l = 0; l < 3; l++) {
					for (int c = 0; c < 3; c++) {

						if (l == 0 && c == 0) {
							JButton btnNewButton = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
							btnNewButton.setBounds(10, 32, 45, 45);
							panel_1.add(btnNewButton);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								btnNewButton.setText("");
								btnNewButton.setEnabled(false);
								btnNewButton.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 1) {
							JButton button = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button.setFont(new Font("Tahoma", Font.PLAIN, 10));
							button.setBounds(55, 32, 45, 45);
							panel_1.add(button);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button.setText("");
								button.setEnabled(false);
								button.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 2) {
							JButton button_1 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_1.setBounds(100, 32, 45, 45);
							button_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_1);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_1.setText("");
								button_1.setEnabled(false);
								button_1.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 0) {
							JButton button_2 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_2.setBounds(10, 77, 45, 45);
							button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_2);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_2.setText("");
								button_2.setEnabled(false);
								button_2.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 1) {
							JButton button_3 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_3.setBounds(55, 77, 45, 45);
							button_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_3);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_3.setText("");
								button_3.setEnabled(false);
								button_3.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 2) {
							JButton button_4 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_4.setBounds(100, 77, 45, 45);
							button_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_4);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_4.setText("");
								button_4.setEnabled(false);
								button_4.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 0) {
							JButton button_5 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_5.setBounds(10, 122, 45, 45);
							button_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_5);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_5.setText("");
								button_5.setEnabled(false);
								button_5.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 1) {
							JButton button_6 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_6.setBounds(55, 122, 45, 45);
							button_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_6);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_6.setText("");
								button_6.setEnabled(false);
								button_6.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 2) {
							JButton button_7 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_7.setBounds(100, 122, 45, 45);
							button_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_7);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_7.setText("");
								button_7.setEnabled(false);
								button_7.setBackground(Color.white);
							}
						}

					}

				}
				try {
					panel_1.repaint();
					Thread.sleep(900);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	};
	
	private Runnable thread4x4 = new Runnable() {
		public void run() {
			
			
			label_1 = new JLabel("");
			label_1.setBounds(473, 30, 26, 14);
			panel.add(label_1);
			panel.repaint();
			
			for (int qnt = 0; qnt < todos.size(); qnt++) {
				panel_1.removeAll();
				for (int l = 0; l < 4; l++) {
					for (int c = 0; c < 4; c++) {

						if (l == 0 && c == 0) {
							JButton btnNewButton = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
							btnNewButton.setBounds(10, 32, 45, 45);
							panel_1.add(btnNewButton);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								btnNewButton.setText("");
								btnNewButton.setEnabled(false);
								btnNewButton.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 1) {
							JButton button = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button.setFont(new Font("Tahoma", Font.PLAIN, 10));
							button.setBounds(55, 32, 45, 45);
							panel_1.add(button);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button.setText("");
								button.setEnabled(false);
								button.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 2) {
							JButton button_1 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_1.setBounds(100, 32, 45, 45);
							button_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_1);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_1.setText("");
								button_1.setEnabled(false);
								button_1.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 3) {
							JButton button_2 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_2.setBounds(145, 32, 45, 45);
							button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_2);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_2.setText("");
								button_2.setEnabled(false);
								button_2.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 0) {
							JButton button_3 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_3.setBounds(10, 77, 45, 45);
							button_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_3);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_3.setText("");
								button_3.setEnabled(false);
								button_3.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 1) {
							JButton button_4 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_4.setBounds(55, 77, 45, 45);
							button_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_4);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_4.setText("");
								button_4.setEnabled(false);
								button_4.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 2) {
							JButton button_5 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_5.setBounds(100, 77, 45, 45);
							button_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_5);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_5.setText("");
								button_5.setEnabled(false);
								button_5.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 3) {
							JButton button_6 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_6.setBounds(145, 77, 45, 45);
							button_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_6);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_6.setText("");
								button_6.setEnabled(false);
								button_6.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 0) {
							JButton button_7 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_7.setBounds(10, 122, 45, 45);
							button_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_7);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_7.setText("");
								button_7.setEnabled(false);
								button_7.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 1) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(55, 122, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 2) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(100, 122, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 3) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(145, 122, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 0) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(10, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 1) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(55, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 2) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(100, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 3) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(145, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}

					}

				}
				try {
					panel_1.repaint();
					Thread.sleep(2000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	};
	
	private Runnable thread5x5 = new Runnable() {
		public void run() {
		
		
			
			label_1 = new JLabel("");
			label_1.setBounds(473, 30, 26, 14);
			panel.add(label_1);
			panel.repaint();
			for (int qnt = 0; qnt < todos.size(); qnt++) {
				panel_1.removeAll();
				for (int l = 0; l < 5; l++) {
					for (int c = 0; c < 5; c++) {

						if (l == 0 && c == 0) {
							JButton btnNewButton = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
							btnNewButton.setBounds(10, 32, 45, 45);
							panel_1.add(btnNewButton);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								btnNewButton.setText("");
								btnNewButton.setEnabled(false);
								btnNewButton.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 1) {
							JButton button = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button.setFont(new Font("Tahoma", Font.PLAIN, 10));
							button.setBounds(55, 32, 45, 45);
							panel_1.add(button);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button.setText("");
								button.setEnabled(false);
								button.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 2) {
							JButton button_1 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_1.setBounds(100, 32, 45, 45);
							button_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_1);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_1.setText("");
								button_1.setEnabled(false);
								button_1.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 3) {
							JButton button_2 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_2.setBounds(145, 32, 45, 45);
							button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_2);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_2.setText("");
								button_2.setEnabled(false);
								button_2.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 0) {
							JButton button_3 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_3.setBounds(10, 77, 45, 45);
							button_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_3);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_3.setText("");
								button_3.setEnabled(false);
								button_3.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 1) {
							JButton button_4 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_4.setBounds(55, 77, 45, 45);
							button_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_4);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_4.setText("");
								button_4.setEnabled(false);
								button_4.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 2) {
							JButton button_5 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_5.setBounds(100, 77, 45, 45);
							button_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_5);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_5.setText("");
								button_5.setEnabled(false);
								button_5.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 3) {
							JButton button_6 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_6.setBounds(145, 77, 45, 45);
							button_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_6);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_6.setText("");
								button_6.setEnabled(false);
								button_6.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 0) {
							JButton button_7 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_7.setBounds(10, 122, 45, 45);
							button_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_7);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_7.setText("");
								button_7.setEnabled(false);
								button_7.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 1) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(55, 122, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 2) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(100, 122, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 3) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(145, 122, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 0) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(10, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 1) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(55, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 2) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(100, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 3) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(145, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 0 && c == 4) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(190, 32, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 1 && c == 4) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(190, 77, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 2 && c == 4) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(190, 122, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 3 && c == 4) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(190, 167, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 4 && c == 0) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(10, 212, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 4 && c == 1) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(55, 212, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 4 && c == 2) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(100, 212, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 4 && c == 3) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(145, 212, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}
						if (l == 4 && c == 4) {
							JButton button_8 = new JButton(todos.get(qnt).getMatriz()[l][c].getInf() + "");
							button_8.setBounds(190, 212, 45, 45);
							button_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
							panel_1.add(button_8);
							if (todos.get(qnt).getMatriz()[l][c].getInf() == -1) {
								button_8.setText("");
								button_8.setEnabled(false);
								button_8.setBackground(Color.white);
							}
						}

					}

				}
				try {
					panel_1.repaint();
					Thread.sleep(2000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	};
	
	private Runnable threadGuloso = new Runnable() {
		public void run() {
			
			listaGuloso = Gulosa.buscaGulosa(new Tabuleiro(matriz.length, matriz, null), altura, textAreaGuloso);
			
			
			
			if(todos==null){
				if(listaGuloso.get(listaGuloso.size() - 1).analisaOtimo()){
					lblGulosoSituacao.setText("Concluído");
					//todos = listaGuloso;
					//System.out.println("Escolheu Guloso");
					//Execução do sistema----------------------------------------------
//					if(matriz.length==3)
//						new Thread(thread3x3).start();
//					if(matriz.length==4)
//						new Thread(thread4x4).start();
//					if(matriz.length==5)
//						new Thread(thread5x5).start();
					
				}else{
					lblGulosoSituacao.setText("Solucao nao encontrada");
				}
				//
			//
			}else{
				if(listaGuloso.get(listaGuloso.size() - 1).analisaOtimo()){
					lblGulosoSituacao.setText("Concluído");
				}else{
					lblGulosoSituacao.setText("Solucao nao encontrada");
				}
			}
		}
	};
	
	private Runnable threadLargura = new Runnable() {
		public void run() {
			
			
				listaLargura = Largura.buscaEmLargura(new Tabuleiro(matriz.length, matriz, null), altura, textAreaLargura);
				
				
				
				if(todos== null && listaLargura!=null){
					if(listaLargura.get(listaLargura.size()-1).analisaOtimo()){
						lblLarguraSituacao.setText("Concluído");
						todos = listaLargura;
						System.out.println("Escolheu Largura");
						//Execução do sistema----------------------------------------------
						if(matriz.length==3)
							new Thread(thread3x3).start();
						if(matriz.length==4)
							new Thread(thread4x4).start();
						if(matriz.length==5)
							new Thread(thread5x5).start();
					}else{
						lblLarguraSituacao.setText("Solucao nao encontrada");
					}
					
				}else{
					if(listaLargura!=null && listaLargura.get(listaLargura.size()-1).analisaOtimo())
						lblLarguraSituacao.setText("Concluído");
					else
						lblLarguraSituacao.setText("Solucao nao encontrada");
						
				}
		}
	};
	
	private Runnable threadProfundidade = new Runnable() {
		public void run() {
			
			listaProfundidade = BuscaProfundidade.buscaProfunidade(new Tabuleiro(matriz.length, matriz, null), altura, textAreaProfundidade);
			
			
			
			if(todos==null && listaProfundidade.size()>0){
				if(listaProfundidade.get(listaProfundidade.size()-1).analisaOtimo()){
					lblProfundidadeSituacao.setText("Concluído");
					todos = listaProfundidade;
					System.out.println("Escolheu Profundidade");
					//Execução do sistema----------------------------------------------
					if(matriz.length==3)
						new Thread(thread3x3).start();
					if(matriz.length==4)
						new Thread(thread4x4).start();
					if(matriz.length==5)
						new Thread(thread5x5).start();
				}else{
					lblProfundidadeSituacao.setText("Solucao nao encontrada");
				}
				
			}else{
				if(listaProfundidade.size() > 0 && listaProfundidade.get(listaProfundidade.size()-1).analisaOtimo()){
					lblProfundidadeSituacao.setText("Concluído");
				}else{
					lblProfundidadeSituacao.setText("Solucao nao encontrada");
				}
			}
		}
	};
	
	private Runnable threadAEstrela = new Runnable() {
		public void run() {
			
			listaAEstrela =  Aestrela.buscaAEstrela(new Tabuleiro(matriz.length, matriz, null), altura, textAreaAEstrela);
			
			lblAEstrelaSituacao.setText("Concluído");
			
			if(todos==null){
				if(listaAEstrela.get(listaAEstrela.size()-1).analisaOtimo()){
					lblAEstrelaSituacao.setText("Concluído");
					todos = listaAEstrela;
					System.out.println("Escolheu A*");
					//Execução do sistema----------------------------------------------
					if(matriz.length==3)
						new Thread(thread3x3).start();
					if(matriz.length==4)
						new Thread(thread4x4).start();
					if(matriz.length==5)
						new Thread(thread5x5).start();
				}else{
					lblAEstrelaSituacao.setText("Solucao nao encontrada");
				}
				
			}else{
				if(listaAEstrela.get(listaAEstrela.size()-1).analisaOtimo()){
					lblAEstrelaSituacao.setText("Concluído");
				}else{
					lblAEstrelaSituacao.setText("Solucao nao encontrada");
				}
			}
		}
	};
	
	private TextArea textAreaAEstrela;
	private TextArea textAreaGuloso;
	private JTable table;
	private JLabel label;
	private JLabel lblInformaes;
	private JLabel label_1;
	private JLabel lblAlturaDefinidaPara;
	private JLabel label_2;
	private JLabel lblRelatrioTcnico;
	private JButton btnNewButton_1;
	private JPanel panel_3;
	private TextArea textAreaLargura;
	private TextArea textAreaProfundidade;
}
