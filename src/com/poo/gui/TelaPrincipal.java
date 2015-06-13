package com.poo.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	public TelaPrincipal() {
		//contentPane.setTitle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{710, 0};
		gbl_contentPane.rowHeights = new int[]{68, 2, 342, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);
		
		this.setResizable(isResizable());
	
		
		JLabel lblBemVindos = new JLabel("BEM VINDOS");
		lblBemVindos.setBounds(292, 44, 153, 28);
		panel_1.add(lblBemVindos);
		lblBemVindos.setFont(new Font("Tahoma", Font.BOLD, 23));
		
		JButton btnFuncionario = new JButton("FUNCIONARIO");
		btnFuncionario.setBounds(314, 101, 111, 23);
		panel_1.add(btnFuncionario);
		btnFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		JButton btnSuporte = new JButton("SUPORTE");
		btnSuporte.setBounds(314, 146, 111, 23);
		panel_1.add(btnSuporte);
		btnSuporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSuporte tela = new TelaSuporte();
				tela.setVisible(true);
				TelaPrincipal.this.setVisible(false);
				}
		});
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSuporte tela = new TelaSuporte();
				tela.setVisible(true);
				TelaPrincipal.this.setVisible(false);
			}
		});
	}
}
