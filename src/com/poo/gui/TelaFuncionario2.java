package com.poo.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JInternalFrame;

public class TelaFuncionario2 extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSuporte2 frame = new TelaSuporte2();
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
	public TelaFuncionario2() {
		setTitle("Funcionario - iSinistro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 463);
		JPanel Funcionario = new JPanel();
		Funcionario.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Funcionario);
		GridBagLayout gbl_Funcionario = new GridBagLayout();
		gbl_Funcionario.columnWidths = new int[]{672, 0};
		gbl_Funcionario.rowHeights = new int[]{424, 0};
		gbl_Funcionario.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_Funcionario.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		Funcionario.setLayout(gbl_Funcionario);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		Funcionario.add(panel, gbc_panel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 672, 21);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("MENU");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSair = new JMenuItem("SAIR");
		mnNewMenu.add(mntmSair);
		
		JMenu mnNewMenu_1 = new JMenu("SOBRE");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmDesenvolvedores = new JMenuItem("DESENVOLVEDORES");
		mnNewMenu_1.add(mntmDesenvolvedores);
		
		JMenuItem mntmPrograma = new JMenuItem("PROGRAMA");
		mnNewMenu_1.add(mntmPrograma);
		
		JMenu mnNewMenu_2 = new JMenu("AJUDA");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmPrincipaisFuncionalidades = new JMenuItem("PRINCIPAIS FUNCIONALIDADES");
		mnNewMenu_2.add(mntmPrincipaisFuncionalidades);
		
		JMenuItem mntmPassoAPasso = new JMenuItem("PASSO A PASSO COMPLETO");
		mnNewMenu_2.add(mntmPassoAPasso);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 26, 672, 2);
		panel.add(separator);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 37, 672, 387);
		panel.add(tabbedPane);
		
		JScrollPane cadastra = new JScrollPane();
	
		cadastra.setToolTipText("");
		tabbedPane.addTab("INSERIR CLIENTE", null, cadastra, null);
		cadastra.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		cadastra.setViewportView(btnNewButton);
		
		JScrollPane excluir = new JScrollPane();
		tabbedPane.addTab("EXCLUIR CLIENTE", null, excluir, null);
		
		JScrollPane listarTodos = new JScrollPane();
		tabbedPane.addTab("LISTAR CLIENTES", null, listarTodos, null);
		
		JScrollPane pesquisar = new JScrollPane();
		tabbedPane.addTab("CONSULTAR CLIENTE", null, pesquisar, null);
	}
}
