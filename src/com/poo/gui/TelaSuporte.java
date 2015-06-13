package com.poo.gui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.JSeparator;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.GerenciadorFachada;
import com.poo.negocios.IGerenciador;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TelaSuporte extends JFrame{
	
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JPasswordField passwordField;
	private JButton botaoLogin;
	private JButton botaoLimpar;
	private IGerenciador fachada = GerenciadorFachada.getInstance();

	public TelaSuporte() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(1, 91, 710, 343);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblSuporteLogin = new JLabel("Login Suporte");
		lblSuporteLogin.setBounds(248, 28, 185, 26);
		panel1.add(lblSuporteLogin);
		lblSuporteLogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblSuporteLogin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSuporteLogin.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(248, 92, 64, 26);
		panel1.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(248, 136, 64, 26);
		panel1.add(lblSenha);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(343, 95, 105, 20);
		panel1.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(343, 139, 105, 20);
		panel1.add(passwordField);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new AcaoBotaoLogin());
		btnLogin.setBounds(248, 191, 89, 23);
		panel1.add(btnLogin);
				
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new AcaoBotaoLimpar());
		btnLimpar.setBounds(357, 191, 89, 23);
		panel1.add(btnLimpar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(704, 83, -700, 10);
		contentPane.add(separator);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(1, 0, 709, 84);
		contentPane.add(panel2);
	}
	
	
/*
	private TelaPrincipal voltarTelaPrincipal() {
		
	}
*/
	
	public class AcaoBotaoLogin implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			
			try{
				if(fachada.validarSenhaS(textFieldNome.getText(), passwordField.getPassword()))
				JOptionPane.showMessageDialog(null, "Logado com sucesso");
			}catch(ProcuraPessoaInexistenteExeception ppi){
				
				JOptionPane.showMessageDialog(null, ppi.getMessage());
			
			} catch (SenhaIncorretaExeception sie) {
			
				JOptionPane.showMessageDialog(null, sie.getMessage());
			}
			
		}
	}
	
	
	
	public class AcaoBotaoLimpar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			textFieldNome.setText("");
			passwordField.setText("");
		}
	}
	
	public void limparTodosCampos(Container container) {  
		Component components[] = container.getComponents();  
		for (Component component : components) {  
		    if (component instanceof JFormattedTextField) {  
		        JFormattedTextField field = (JFormattedTextField) component;  
		        field.setValue(null);  
		    } else if (component instanceof JTextField) {  
		        JTextField field = (JTextField) component;  
		        field.setText("");  
		    } else if (component instanceof Container) {  
		        limparTodosCampos((Container) component);  
		    }  
		}
	}
	
	
	private void setDefaultCloseOperation() {
		TelaPrincipal tela = new TelaPrincipal();
		tela.setVisible(true);
		
	}

}

