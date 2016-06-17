package visão;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modelo.Unidade;

@SuppressWarnings("serial")
public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setResizable(false);
		setTitle("Puzzle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// variaveis de matriz
		JButton[][] matriz = new JButton[3][3];
		JButton[][] matriz44 = new JButton[4][4];
		JButton[][] matriz55 = new JButton[5][5];

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tamanho do Jogo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 59, 126, 111);
		contentPane.add(panel);
		panel.setLayout(null);

		JRadioButton rdbtnx_1 = new JRadioButton("4x4");
		rdbtnx_1.setBackground(Color.WHITE);
		rdbtnx_1.setBounds(18, 49, 60, 23);
		panel.add(rdbtnx_1);

		JRadioButton rdbtnx_2 = new JRadioButton("5x5");
		rdbtnx_2.setBackground(Color.WHITE);
		rdbtnx_2.setBounds(18, 75, 60, 23);
		panel.add(rdbtnx_2);

		JRadioButton rdbtnx = new JRadioButton("3x3");
		rdbtnx.setSelected(true);
		rdbtnx.setBackground(Color.WHITE);
		rdbtnx.setBounds(18, 21, 60, 23);
		panel.add(rdbtnx);

		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(rdbtnx);
		grupo1.add(rdbtnx_1);
		grupo1.add(rdbtnx_2);

		JLabel lblQuabracabea = new JLabel("Quebra-cabeça");
		lblQuabracabea.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 18));
		lblQuabracabea.setBounds(174, 11, 185, 51);
		contentPane.add(lblQuabracabea);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Tabuleiro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(176, 59, 289, 273);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Tabuleiro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(176, 59, 289, 273);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "Tabuleiro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(176, 59, 289, 273);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		SpinnerModel model = new SpinnerNumberModel(1, 1, 50, 1);
		JSpinner spinner = new JSpinner(model);
		spinner.setBounds(42, 41, 35, 25);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				// botao da matriz
				// 3x3--------------------------------------------------------------------------------------------
				if (rdbtnx.isSelected()) {
					
					Unidade matrizDeUnidade[][] = new Unidade[3][3];
					for (int l = 0; l < 3; l++) {
						for (int c = 0; c < 3; c++) {
							if (matriz[l][c].getText().equals(""))
								matrizDeUnidade[l][c] = new Unidade(-1);
							else
								matrizDeUnidade[l][c] = new Unidade(Integer.parseInt(matriz[l][c].getText()));
						}
					}
					dispose();
					TelaRelatorio tela = new TelaRelatorio(matrizDeUnidade,
							Integer.parseInt(spinner.getValue().toString()));
					tela.show();
				}
				// botao da matriz
				// 4x4---------------------------------------------------------------------------------------
				if (rdbtnx_1.isSelected()) {
					
					Unidade matrizDeUnidade[][] = new Unidade[4][4];
					for (int l = 0; l < 4; l++) {
						for (int c = 0; c < 4; c++) {
							if (matriz44[l][c].getText().equals(""))
								matrizDeUnidade[l][c] = new Unidade(-1);
							else
								matrizDeUnidade[l][c] = new Unidade(Integer.parseInt(matriz44[l][c].getText()));
						}
					}
					dispose();
					TelaRelatorio tela = new TelaRelatorio(matrizDeUnidade,
							Integer.parseInt(spinner.getValue().toString()));
					tela.show();
				}
				// botao da matriz
				// 5x5---------------------------------------------------------------------------------------
				if (rdbtnx_2.isSelected()) {
					
					Unidade matrizDeUnidade[][] = new Unidade[5][5];
					for (int l = 0; l < 5; l++) {
						for (int c = 0; c < 5; c++) {
							if (matriz55[l][c].getText().equals(""))
								matrizDeUnidade[l][c] = new Unidade(-1);
							else
								matrizDeUnidade[l][c] = new Unidade(Integer.parseInt(matriz55[l][c].getText()));
						}
					}
					dispose();
					TelaRelatorio tela = new TelaRelatorio(matrizDeUnidade,
							Integer.parseInt(spinner.getValue().toString()));
					tela.show();
				}
			}
		});
		btnIniciar.setBounds(334, 337, 126, 23);
		contentPane.add(btnIniciar);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Altura Inicial",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 195, 126, 111);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		panel_4.add(spinner);

		panel_1.setVisible(true);
		panel_2.setVisible(false);
		panel_3.setVisible(false);

		rdbtnx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
				panel_2.setVisible(false);
				panel_3.setVisible(false);

			}
		});

		rdbtnx_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				panel_3.setVisible(false);

			}
		});

		rdbtnx_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_3.setVisible(true);

			}
		});
		// -----------------------------MATRIZ 3 X 3
		// ----------------------------------------------------------------------

		JButton btnBranco = new JButton("");
		btnBranco.setBackground(Color.white);
		btnBranco.setEnabled(false);
		btnBranco.setBounds(115, 127, 45, 45);

		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button, matriz, 3);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button.setBounds(23, 35, 45, 45);
		matriz[0][0] = button;

		JButton button_1 = new JButton("2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1, matriz, 3);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1.setBounds(69, 35, 45, 45);
		matriz[0][1] = button_1;

		JButton button_2 = new JButton("3");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_2, matriz, 3);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2.setBounds(115, 35, 45, 45);
		matriz[0][2] = button_2;

		JButton button_3 = new JButton("4");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_3, matriz, 3);
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_3.setBounds(23, 81, 45, 45);
		matriz[1][0] = button_3;

		JButton button_4 = new JButton("5");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_4, matriz, 3);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_4.setBounds(69, 81, 45, 45);
		matriz[1][1] = button_4;

		JButton button_5 = new JButton("6");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_5, matriz, 3);
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_5.setBounds(115, 81, 45, 45);
		matriz[1][2] = button_5;

		JButton button_6 = new JButton("7");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_6, matriz, 3);
			}
		});
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_6.setBounds(23, 127, 45, 45);
		matriz[2][0] = button_6;

		JButton button_7 = new JButton("8");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_7, matriz, 3);
			}
		});
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_7.setBounds(69, 127, 45, 45);
		matriz[2][1] = button_7;

		matriz[2][2] = btnBranco;

		for (int l = 0; l < 3; l++) {
			for (int c = 0; c < 3; c++) {
				panel_1.add(matriz[l][c]);
			}
		}

		// -----------------------------MATRIZ 4 X 4
		// ----------------------------------------------------------------------

		JButton btnBranco44 = new JButton("");
		btnBranco44.setBackground(Color.white);
		btnBranco44.setEnabled(false);
		btnBranco44.setBounds(161, 173, 45, 45);

		matriz44[3][3] = btnBranco44;

		JButton button44 = new JButton("1");
		button44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button44, matriz44, 4);
			}
		});
		button44.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button44.setBounds(23, 35, 45, 45);
		matriz44[0][0] = button44;

		JButton button_144 = new JButton("2");
		button_144.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_144, matriz44, 4);
			}
		});
		button_144.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_144.setBounds(69, 35, 45, 45);
		matriz44[0][1] = button_144;

		JButton button_244 = new JButton("3");
		button_244.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_244, matriz44, 4);
			}
		});
		button_244.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_244.setBounds(115, 35, 45, 45);
		matriz44[0][2] = button_244;

		JButton button_344 = new JButton("4");
		button_344.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_344, matriz44, 4);
			}
		});
		button_344.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_344.setBounds(161, 35, 45, 45);
		matriz44[0][3] = button_344;

		JButton button_444 = new JButton("5");
		button_444.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_444, matriz44, 4);
			}
		});
		button_444.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_444.setBounds(23, 81, 45, 45);
		matriz44[1][0] = button_444;

		JButton button_544 = new JButton("6");
		button_544.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_544, matriz44, 4);
			}
		});
		button_544.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_544.setBounds(69, 81, 45, 45);
		matriz44[1][1] = button_544;

		JButton button_644 = new JButton("7");
		button_644.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_644, matriz44, 4);
			}
		});
		button_644.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_644.setBounds(115, 81, 45, 45);
		matriz44[1][2] = button_644;

		JButton button_744 = new JButton("8");
		button_744.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_744, matriz44, 4);
			}
		});
		button_744.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_744.setBounds(161, 81, 45, 45);
		matriz44[1][3] = button_744;

		JButton button_844 = new JButton("9");
		button_844.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_844, matriz44, 4);
			}
		});
		button_844.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_844.setBounds(23, 127, 45, 45);
		matriz44[2][0] = button_844;

		JButton button_944 = new JButton("10");
		button_944.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_944, matriz44, 4);
			}
		});
		button_944.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_944.setBounds(69, 127, 45, 45);
		matriz44[2][1] = button_944;

		JButton button_1044 = new JButton("11");
		button_1044.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1044, matriz44, 4);
			}
		});
		button_1044.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1044.setBounds(115, 127, 45, 45);
		matriz44[2][2] = button_1044;

		JButton button_1144 = new JButton("12");
		button_1144.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1144, matriz44, 4);
			}
		});
		button_1144.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1144.setBounds(161, 127, 45, 45);
		matriz44[2][3] = button_1144;

		JButton button_1244 = new JButton("13");
		button_1244.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1244, matriz44, 4);
			}
		});
		button_1244.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1244.setBounds(23, 173, 45, 45);
		matriz44[3][0] = button_1244;

		JButton button_1344 = new JButton("14");
		button_1344.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1344, matriz44, 4);
			}
		});
		button_1344.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1344.setBounds(69, 173, 45, 45);
		matriz44[3][1] = button_1344;

		JButton button_1444 = new JButton("15");
		button_1444.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1444, matriz44, 4);
			}
		});
		button_1444.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1444.setBounds(115, 173, 45, 45);
		matriz44[3][2] = button_1444;

		for (int l = 0; l < 4; l++) {
			for (int c = 0; c < 4; c++) {
				panel_2.add(matriz44[l][c]);
			}
		}

		// -----------------------------MATRIZ 5 X 5
		// ----------------------------------------------------------------------
		JButton btnBranco55 = new JButton("");
		btnBranco55.setBackground(Color.white);
		btnBranco55.setEnabled(false);
		btnBranco55.setBounds(207, 219, 45, 45);

		matriz55[4][4] = btnBranco55;

		JButton button55 = new JButton("1");
		button55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button55, matriz55, 5);
			}
		});
		button55.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button55.setBounds(23, 35, 45, 45);
		matriz55[0][0] = button55;

		JButton button_155 = new JButton("2");
		button_155.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_155, matriz55, 5);
			}
		});
		button_155.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_155.setBounds(69, 35, 45, 45);
		matriz55[0][1] = button_155;

		JButton button_255 = new JButton("3");
		button_255.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_255, matriz55, 5);
			}
		});
		button_255.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_255.setBounds(115, 35, 45, 45);
		matriz55[0][2] = button_255;

		JButton button_355 = new JButton("4");
		button_355.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_355, matriz55, 5);
			}
		});
		button_355.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_355.setBounds(161, 35, 45, 45);
		matriz55[0][3] = button_355;

		JButton button_455 = new JButton("5");
		button_455.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_455, matriz55, 5);
			}
		});
		button_455.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_455.setBounds(207, 35, 45, 45);
		matriz55[0][4] = button_455;

		JButton button_555 = new JButton("6");
		button_555.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_555, matriz55, 5);
			}
		});
		button_555.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_555.setBounds(23, 81, 45, 45);
		matriz55[1][0] = button_555;

		JButton button_655 = new JButton("7");
		button_655.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_655, matriz55, 5);
			}
		});
		button_655.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_655.setBounds(69, 81, 45, 45);
		matriz55[1][1] = button_655;

		JButton button_755 = new JButton("8");
		button_755.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_755, matriz55, 5);
			}
		});
		button_755.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_755.setBounds(115, 81, 45, 45);
		matriz55[1][2] = button_755;

		JButton button_855 = new JButton("9");
		button_855.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_855, matriz55, 5);
			}
		});
		button_855.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_855.setBounds(161, 81, 45, 45);
		matriz55[1][3] = button_855;

		JButton button_955 = new JButton("10");
		button_955.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_955, matriz55, 5);
			}
		});
		button_955.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_955.setBounds(207, 81, 45, 45);
		matriz55[1][4] = button_955;

		JButton button_1055 = new JButton("11");
		button_1055.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1055, matriz55, 5);
			}
		});
		button_1055.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1055.setBounds(23, 127, 45, 45);
		matriz55[2][0] = button_1055;

		JButton button_1155 = new JButton("12");
		button_1155.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1155, matriz55, 5);
			}
		});
		button_1155.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1155.setBounds(69, 127, 45, 45);
		matriz55[2][1] = button_1155;

		JButton button_1255 = new JButton("13");
		button_1255.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1255, matriz55, 5);
			}
		});
		button_1255.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1255.setBounds(115, 127, 45, 45);
		matriz55[2][2] = button_1255;

		JButton button_1355 = new JButton("14");
		button_1355.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1355, matriz55, 5);
			}
		});
		button_1355.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1355.setBounds(161, 127, 45, 45);
		matriz55[2][3] = button_1355;

		JButton button_1455 = new JButton("15");
		button_1455.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1455, matriz55, 5);
			}
		});
		button_1455.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1455.setBounds(207, 127, 45, 45);
		matriz55[2][4] = button_1455;

		JButton button_1555 = new JButton("16");
		button_1555.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1555, matriz55, 5);
			}
		});
		button_1555.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1555.setBounds(23, 173, 45, 45);
		matriz55[3][0] = button_1555;

		JButton button_1655 = new JButton("17");
		button_1655.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1655, matriz55, 5);
			}
		});
		button_1655.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1655.setBounds(69, 173, 45, 45);
		matriz55[3][1] = button_1655;

		JButton button_1755 = new JButton("18");
		button_1755.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1755, matriz55, 5);
			}
		});
		button_1755.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1755.setBounds(115, 173, 45, 45);
		matriz55[3][2] = button_1755;

		JButton button_1855 = new JButton("19");
		button_1855.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1855, matriz55, 5);
			}
		});
		button_1855.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1855.setBounds(161, 173, 45, 45);
		matriz55[3][3] = button_1855;

		JButton button_1955 = new JButton("20");
		button_1955.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_1955, matriz55, 5);
			}
		});
		button_1955.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1955.setBounds(207, 173, 45, 45);
		matriz55[3][4] = button_1955;

		JButton button_2055 = new JButton("21");
		button_2055.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_2055, matriz55, 5);
			}
		});
		button_2055.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2055.setBounds(23, 219, 45, 45);
		matriz55[4][0] = button_2055;

		JButton button_2155 = new JButton("22");
		button_2155.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_2155, matriz55, 5);
			}
		});
		button_2155.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2155.setBounds(69, 219, 45, 45);
		matriz55[4][1] = button_2155;

		JButton button_2255 = new JButton("23");
		button_2255.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_2255, matriz55, 5);
			}
		});
		button_2255.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2255.setBounds(115, 219, 45, 45);
		matriz55[4][2] = button_2255;

		JButton button_2355 = new JButton("24");
		button_2355.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verificaVizinho(button_2355, matriz55, 5);
			}
		});
		button_2355.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2355.setBounds(161, 219, 45, 45);
		matriz55[4][3] = button_2355;

		for (int l = 0; l < 5; l++) {
			for (int c = 0; c < 5; c++) {
				panel_3.add(matriz55[l][c]);
			}
		}
	}

	public void verificaVizinho(JButton button, JButton[][] matriz, int ordem) {
		int linha = 0;
		int coluna = 0;
		for (int l = 0; l < ordem; l++) {
			for (int c = 0; c < ordem; c++) {
				if (matriz[l][c].getText().equals(button.getText())) {
					linha = l;
					coluna = c;
					break;
				}
			}
		}
		int linhaBranco = 0;
		int colunaBranco = 0;
		for (int l = 0; l < ordem; l++) {
			for (int c = 0; c < ordem; c++) {
				if (matriz[l][c].getText().equals("")) {
					linhaBranco = l;
					colunaBranco = c;
					break;
				}
			}
		}

		if (linhaBranco - 1 == linha && colunaBranco == coluna) {
			matriz[linhaBranco][colunaBranco].setEnabled(true);
			int x = matriz[linhaBranco][colunaBranco].getX();
			int y = matriz[linhaBranco][colunaBranco].getY();
			matriz[linhaBranco][colunaBranco].setLocation(matriz[linha][coluna].getX(), matriz[linha][coluna].getY());
			matriz[linha][coluna].setLocation(x, y);

			// troca posicao na matriz
			JButton aux = matriz[linhaBranco][colunaBranco];
			matriz[linhaBranco][colunaBranco] = matriz[linha][coluna];
			matriz[linha][coluna] = aux;

			return;
		}
		if (linhaBranco == linha && colunaBranco - 1 == coluna) {
			matriz[linhaBranco][colunaBranco].setEnabled(true);
			int x = matriz[linhaBranco][colunaBranco].getX();
			int y = matriz[linhaBranco][colunaBranco].getY();
			matriz[linhaBranco][colunaBranco].setLocation(matriz[linha][coluna].getX(), matriz[linha][coluna].getY());
			matriz[linha][coluna].setLocation(x, y);

			// troca posicao na matriz
			JButton aux = matriz[linhaBranco][colunaBranco];
			matriz[linhaBranco][colunaBranco] = matriz[linha][coluna];
			matriz[linha][coluna] = aux;

			return;
		}

		if (linhaBranco + 1 == linha && colunaBranco == coluna) {
			matriz[linhaBranco][colunaBranco].setEnabled(true);
			int x = matriz[linhaBranco][colunaBranco].getX();
			int y = matriz[linhaBranco][colunaBranco].getY();
			matriz[linhaBranco][colunaBranco].setLocation(matriz[linha][coluna].getX(), matriz[linha][coluna].getY());
			matriz[linha][coluna].setLocation(x, y);

			// troca posicao na matriz
			JButton aux = matriz[linhaBranco][colunaBranco];
			matriz[linhaBranco][colunaBranco] = matriz[linha][coluna];
			matriz[linha][coluna] = aux;

			return;
		}
		if (linhaBranco == linha && colunaBranco + 1 == coluna) {
			matriz[linhaBranco][colunaBranco].setEnabled(true);
			int x = matriz[linhaBranco][colunaBranco].getX();
			int y = matriz[linhaBranco][colunaBranco].getY();
			matriz[linhaBranco][colunaBranco].setLocation(matriz[linha][coluna].getX(), matriz[linha][coluna].getY());
			matriz[linha][coluna].setLocation(x, y);

			// troca posicao na matriz
			JButton aux = matriz[linhaBranco][colunaBranco];
			matriz[linhaBranco][colunaBranco] = matriz[linha][coluna];
			matriz[linha][coluna] = aux;

			return;
		}

		// matriz[linhaBranco][colunaBranco].setEnabled(true);
		return;
	}

	public boolean verificaNum(ArrayList<Integer> lista, int num) {
		for (int aux : lista) {
			if (aux == num)
				return true;
		}
		return false;
	}
}
