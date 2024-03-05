package presentacion;

import logica.Fabrica;
import logica.ISistema;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;

import datatype.DtModUser;
import datatype.DtPaquete;
import datatype.DtUsuario;
import datatype.DtSalidaTuristica;
import datatype.DtActTur;
import datatype.DtActTurEst;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;


public class Pantalla extends JFrame {

	private static final long serialVersionUID = 1L;

	JPanel actual;
	private TIPOS_USUARIOS tipoUsuarioActual = TIPOS_USUARIOS.NONE; 
	private TIPOS_VISTA_USUARIOS vistaUsuarioActual = TIPOS_VISTA_USUARIOS.NONE;
	
	

	private JLabel lblTituloEmpleado;
	private JLabel lblTituloConsultaU;
	private JTextField tFNickEmpleado;
	private JTextField tFEmailEmpleado;
	private JTextField tFNameEmpleado;
	private JTextField tFApeEmpleado;
	private JTextField tFFechaNacEmpleado;
	private JLabel lblNacionEmpleado;
	private JTextField tFNacionEmpleado;
	private JLabel lblLinkEmpleado;
	private JTextField tFLinkEmpleado;
	private JLabel lblDescEmpleado;
	private JTextArea tADescEmpleado;
	private JTextField tFFechaInsc;
	private JTextField tFCantTur;
	
	private JLabel lblEmailEmpleado;
	private JLabel lblNickEmpleado;
	private JTextField tFNickUsuario;
	private JTextField tFEmailUsuario;
	private JTextField tFNameUsuario;
	private JTextField tFApeUsuario;
	private JTextField tFFechaNacUsuario;
	private JLabel lblNacionUsuario;
	private JTextField tFNacionUsuario;
	private JLabel lblLinkUsuario;
	private JTextField tFLinkUsuario;
	private JLabel lblDescUsuario;
	private JTextArea tADescUsuario;
	private JComboBox<String> listarUsuarios;
	private JComboBox<String> listarDepartamentosS;
	private JComboBox<String> listarDepartamentosA;
	private JComboBox<String> listarActividades;
	private JComboBox<String> listarProveedores;
	private JComboBox<String> listarDepartamentosCS;
	private JComboBox<String> listarActividadesCS;
	private JComboBox<String> listarSalidasCS;
	private JComboBox<String> listarPaquetes;
	private JComboBox<String> listarActividadesCP;
	private JComboBox<String> listarPaquetesAAP;
	private JComboBox<String> listarDepartamentosAAP;
	private JComboBox<String> listarActividadesAAP;
	

	private JComboBox<String> listarCategoriasA;
	private JComboBox<String> listarCategoriasSelectA;
	private JComboBox<String> listarCategoriasCA;
	private JComboBox<String> listarCategoriasCP;
	private JComboBox<String> listarDepartamentosCA;
	private JComboBox<String> listarActividadesCA;
	private JComboBox<String> listarUsuariosCU;
	private JComboBox<String> listarActividadesCU;
	private JComboBox<String> listarSalidasCU;
	private JComboBox<String> listarDepartamentosIST;
	private JComboBox<String> listarActividadesIST;
	private JComboBox<String> listarSalidasIST;
	private JComboBox<String> listarTuristasIST;
	private JComboBox<String> listarInsc;
	JComboBox<String> listarSalidasCA;
	JComboBox<String> listarPaquetesCA;
	
	private JComboBox<String> listarActividadesAg;
	
	private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	private JTextField tFCantMaxSalida;
	private JTextField tFLugarSalida;
	private JTextField tFHoraSalida;
	private JTextField tFFechaSalida;
	private JTextField tFNameSalida;
	private JTextField tFDuracionActividad;
	private JTextField tFCostoActividad;
	private JTextField tFCiudadActividad;
	private JTextField tFNomActividad;
	private JTextArea tADescActividad;
	private JTextField tFCantMaxSalidaCS;
	private JTextField tFLugarSalidaCS;
	private JTextField tFHoraSalidaCS;
	private JTextField tFFechaSalidaCS;
	private JTextField tFNameSalidaCS;
	private JTextArea tADescripcionP;
	private JTextField tFFechaAltaP;
	private JTextField tFDescuentoP;
	private JTextField tFNomPaquete;
	private JTextField tFPerValP;
	private JTextField tFFechaActividad;
	private JTextField tFNomCActividad;	
	private JTextField tFDuracionCActividad;
	private JTextField tFCostoCActividad;
	private JTextField tFCiudadCActividad;
	private JTextArea tADescCActividad;
	private JTextField tFEstadoAct;
	

	private JTextField tFNombreCategoria;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JPasswordField tFPassword;
	private JPasswordField tFConfirmPassword;


	private boolean testearLargo(String s) {
		if (s.length() > 255) {
			JOptionPane.showMessageDialog(null, "Ningun campo acepta mas de 255 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
	
	private boolean testearLargoNum(Integer i) {
		if (i > 2000000000) {
			JOptionPane.showMessageDialog(null, "Numero demasiado largo.", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		if (i <= 0) {
			JOptionPane.showMessageDialog(null, "Todo numero debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
	
	/**
	 * Cambia la ventana actual por la nueva n para mostrarse
	 * 
	 * @param n JPanel que se quiere mostrar
	 */
	private void cambiarVentanaPrincipal(JPanel n) {
		if (actual != null)
			actual.setVisible(false);
		n.setVisible(true);
		actual = n;
	}

	private JPanel iniciarPanelSistema() {
		JPanel pSistema = new JPanel();
		pSistema.setBounds(0, 0, 784, 539);
		pSistema.setLayout(null);
		pSistema.setBackground(new Color(10, 10, 10));
		
		JLabel lblTituloSis = new JLabel("Sistema");
		lblTituloSis.setBounds(10, 10, 764, 64);
		lblTituloSis.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloSis.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloSis.setForeground(Color.white);
		pSistema.add(lblTituloSis);
		return pSistema;
	}

	private JPanel iniciarPanelAjustes() {
		JPanel pAjustes = new JPanel();
		pAjustes.setBounds(0, 0, 784, 539);
		pAjustes.setVisible(false);
		pAjustes.setLayout(null);
		pAjustes.setBackground(new Color(20, 20, 20));
		
		JLabel lblTituloAjus = new JLabel("Ajustes");
		lblTituloAjus.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTituloAjus.setBounds(10, 10, 764, 64);
		lblTituloAjus.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloAjus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAjus.setForeground(Color.WHITE);
		pAjustes.add(lblTituloAjus);
		return pAjustes;
	}
	
	private void limpiarCamposUsuarios() {
		tFNickEmpleado.setText("");
		tFEmailEmpleado.setText("");
		tFNameEmpleado.setText("");
		tFApeEmpleado.setText("");
		tFFechaNacEmpleado.setText("dd-MM-yyyy");
		tFFechaNacEmpleado.setForeground(Color.GRAY);
		tFPassword.setText("");
		tFConfirmPassword.setText("");
		tFLinkEmpleado.setText("");
		tADescEmpleado.setText("");
		tFNacionEmpleado.setText("");
	}
	
	private void limpiarCamposUsuariosCU() {
		tFNickUsuario.setText("");
		tFEmailUsuario.setText("");
		tFNameUsuario.setText("");
		tFApeUsuario.setText("");
		tFFechaNacUsuario.setText("dd-MM-yyyy");
		tFFechaNacUsuario.setForeground(Color.GRAY);
		tFLinkUsuario.setText("");
		tADescUsuario.setText("");
		tFNacionUsuario.setText("");
	}
	
	private void limpiarCamposSalidaCP() {
    	listarActividadesCP	.removeAllItems();
    	tADescripcionP.setText("");
		tFFechaAltaP.setText("");
		tFDescuentoP.setText("");
		tFNomPaquete.setText("");
		tFPerValP.setText("");
	}
	
	private void limpiarCamposAAP() {
		listarActividadesAAP.removeAllItems();
	}
	
	private void cambiarPlantillaProveedor(boolean c) {
		lblDescEmpleado.setVisible(c);
		tADescEmpleado.setVisible(c);
		lblLinkEmpleado.setVisible(c);
		tFLinkEmpleado.setVisible(c);
	}
	
	private void cambiarPlantillaUsuario(boolean c) {
		lblNacionEmpleado.setVisible(c);
		tFNacionEmpleado.setVisible(c);
	}
	
	private void cambiarPlantillaProveedorCU(boolean c) {
		lblDescUsuario.setVisible(c);
		tADescUsuario.setVisible(c);
		lblLinkUsuario.setVisible(c);
		tFLinkUsuario.setVisible(c);
	}
	
	private void cambiarPlantillaUsuarioCU(boolean c) {
		lblNacionUsuario.setVisible(c);
		tFNacionUsuario.setVisible(c);
	}
	
	private void actualizarPanelUsuario() {
		switch (vistaUsuarioActual) {
			case AGREGAR : {
				lblTituloEmpleado.setText("Agregar Usuario");
				tFNickEmpleado.setEditable(true);
				tFEmailEmpleado.setEditable(true);
				tFPassword.setVisible(true);
				tFConfirmPassword.setVisible(true);
				lblPassword.setVisible(true);
				lblConfirmPassword.setVisible(true);
				
				lblNickEmpleado.setBounds(lblNickEmpleado.getBounds().x, 120, lblNickEmpleado.getBounds().width, lblNickEmpleado.getBounds().height);
				tFNickEmpleado.setBounds(tFNickEmpleado.getBounds().x, 120, tFNickEmpleado.getBounds().width, tFNickEmpleado.getBounds().height);

				lblEmailEmpleado.setBounds(lblEmailEmpleado.getBounds().x, 155, lblEmailEmpleado.getBounds().width, lblEmailEmpleado.getBounds().height);
				tFEmailEmpleado.setBounds(tFEmailEmpleado.getBounds().x, 155, tFEmailEmpleado.getBounds().width, tFEmailEmpleado.getBounds().height);
				limpiarCamposUsuarios();
				cargarListaAgregarEmpleados();

			}
			break;
			case MODIFICAR: {
				lblTituloEmpleado.setText("Modificar Usuario");
				tFNickEmpleado.setEditable(false);
				tFEmailEmpleado.setEditable(false);
				tFPassword.setVisible(false);
				tFConfirmPassword.setVisible(false);
				lblPassword.setVisible(false);
				lblConfirmPassword.setVisible(false);
				
				lblNickEmpleado.setBounds(lblNickEmpleado.getBounds().x, 190, lblNickEmpleado.getBounds().width, lblNickEmpleado.getBounds().height);
				tFNickEmpleado.setBounds(tFNickEmpleado.getBounds().x, 190, tFNickEmpleado.getBounds().width, tFNickEmpleado.getBounds().height);
				
				lblEmailEmpleado.setBounds(lblEmailEmpleado.getBounds().x, 225, lblEmailEmpleado.getBounds().width, lblEmailEmpleado.getBounds().height);
				tFEmailEmpleado.setBounds(tFEmailEmpleado.getBounds().x, 225, tFEmailEmpleado.getBounds().width, tFEmailEmpleado.getBounds().height);
				
				limpiarCamposUsuarios();
				cargarListaModEmpleados();
				
			}
			break;
			default : {
				lblTituloEmpleado.setText("Usuario");
				limpiarCamposUsuarios();
				
			}
		}
	}
	
	private void accionBotonAceptarModificarEmpleado() {
//		boolean esProveedor = (listarUsuarios.getSelectedItem().toString() == "Proveedor");
		
		String nuevoNombre = tFNameEmpleado.getText();
		if (testearLargo(nuevoNombre))
			return;
		if (nuevoNombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String nuevoApellido = tFApeEmpleado.getText();
		if (testearLargo(nuevoApellido))
			return;
		if (nuevoApellido.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El apellido no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		LocalDate nuevaFecha = LocalDate.now();
        try {
        	nuevaFecha = LocalDate.parse(tFFechaNacEmpleado.getText(), formatoFecha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }		

		ISistema sis = Fabrica.getSistema();
		try {
			sis.selecUsuario(listarUsuarios.getSelectedItem().toString());
			if (tipoUsuarioActual.equals(TIPOS_USUARIOS.PROVEEDOR)) {
				String nuevaDesc = tADescEmpleado.getText();
				if (testearLargo(nuevaDesc))
					return;
				if (nuevaDesc.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String nuevoLink = tFLinkEmpleado.getText();
				if (testearLargo(nuevoLink))
					return;				
				sis.modificarDatos(new DtModUser(nuevoNombre, nuevoApellido, nuevaFecha, nuevaDesc, nuevoLink));
			} 
			if (tipoUsuarioActual.equals(TIPOS_USUARIOS.TURISTA)) {
				String nuevaNacionalidad = tFNacionEmpleado.getText();
				if (testearLargo(nuevaNacionalidad))
					return;
				if (nuevaNacionalidad.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La nacionalidad no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				sis.modificarDatos(new DtModUser(nuevoNombre, nuevoApellido, nuevaFecha, nuevaNacionalidad));
			}
			JOptionPane.showMessageDialog(null, "Guardado correctamente.");
		} catch (Exception err) {
			System.out.println(err.getMessage());
			JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar guardar.\nCompruebe los datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	private void accionBotonAceptarAgregarEmpleado(){
		String nuevoNick = tFNickEmpleado.getText();
		if (testearLargo(nuevoNick))
			return;
		if (nuevoNick.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nick no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String nuevoEmail = tFEmailEmpleado.getText();
		if (testearLargo(nuevoEmail))
			return;
		if (nuevoEmail.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El email no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String password = new String(tFPassword.getPassword());
//		Se podrian agregar mas validaciones con respecto a la contraseña usando regex, entre otras cosas.
		if (password.length() < 8 || password.length() > 20) {
			JOptionPane.showMessageDialog(null, "La contraseña debe tener al entre 8 y 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String confirmPassword = new String(tFConfirmPassword.getPassword());
		if(!password.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(null, "La contraseña y la confirmacion deben ser iguales.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String nuevoNombre = tFNameEmpleado.getText();
		if (testearLargo(nuevoNombre))
			return;
		if (nuevoNombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String nuevoApellido = tFApeEmpleado.getText();
		if (testearLargo(nuevoApellido))
			return;
		if (nuevoApellido.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El apellido no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		LocalDate nuevaFecha = LocalDate.now();
        try {
        	nuevaFecha = LocalDate.parse(tFFechaNacEmpleado.getText(), formatoFecha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		boolean esProveedor = (listarUsuarios.getSelectedItem().toString() == "Proveedor");
		String nuevaDesc = tADescEmpleado.getText();
		if (testearLargo(nuevaDesc))
			return;
		if (nuevaDesc.isEmpty() && esProveedor) {
			JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String nuevoLink = tFLinkEmpleado.getText();
		if (testearLargo(nuevoLink))
			return;
		String nuevaNacion = tFNacionEmpleado.getText();
		if (testearLargo(nuevaNacion))
			return;
		if (nuevaNacion.isEmpty() && !esProveedor) {
			JOptionPane.showMessageDialog(null, "La nacionalidad no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ISistema sis = Fabrica.getSistema();
		DtUsuario data = new DtUsuario(nuevoNick, nuevoNombre, nuevoApellido, nuevoEmail, nuevaFecha, nuevaDesc, nuevoLink, nuevaNacion);
		try {
			sis.altaUsuario(esProveedor, data, password);
			JOptionPane.showMessageDialog(null,  "Usuario ingresado correctamente", "Inscripcion completa", JOptionPane.INFORMATION_MESSAGE);
			limpiarCamposUsuarios();
			listarUsuarios.setSelectedIndex(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
		
	private JPanel iniciarPanelEmpleado(JPanel sistema) {
		JPanel pEmpleado = new JPanel();
		pEmpleado.setLayout(null);
		pEmpleado.setBackground(new Color(18, 18, 18));
		pEmpleado.setBounds(0, 0, 784, 539);

		lblTituloEmpleado = new JLabel("Usuario");		
		lblTituloEmpleado.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloEmpleado.setForeground(new Color(255, 255, 255));
		lblTituloEmpleado.setBounds(10, 10, 764, 64);
		pEmpleado.add(lblTituloEmpleado);

		lblNickEmpleado = new JLabel("Nickname:");
		lblNickEmpleado.setForeground(Color.WHITE);
		lblNickEmpleado.setBounds(245, 120, 150, 20);
		pEmpleado.add(lblNickEmpleado);
		tFNickEmpleado = new JTextField();
		tFNickEmpleado.setColumns(10);
		tFNickEmpleado.setBounds(405, 120, 150, 20);
		pEmpleado.add(tFNickEmpleado);
		
		
		lblEmailEmpleado = new JLabel("Email:");
		lblEmailEmpleado.setForeground(Color.WHITE);
		lblEmailEmpleado.setBounds(245, 155, 150, 20);
		pEmpleado.add(lblEmailEmpleado);
		tFEmailEmpleado = new JTextField();
		tFEmailEmpleado.setColumns(10);
		tFEmailEmpleado.setBounds(405, 155, 150, 20);
		pEmpleado.add(tFEmailEmpleado);
		
		JLabel lblNameEmpleado = new JLabel("Nombre:");
		lblNameEmpleado.setForeground(new Color(255, 255, 255));
		lblNameEmpleado.setBounds(245, 260, 150, 20);
		pEmpleado.add(lblNameEmpleado);
		tFNameEmpleado = new JTextField();
		tFNameEmpleado.setColumns(10);
		tFNameEmpleado.setBounds(405, 260, 150, 20);
		pEmpleado.add(tFNameEmpleado);

		JLabel lblApeEmpleado = new JLabel("Apellido:");
		lblApeEmpleado.setForeground(new Color(255, 255, 255));
		lblApeEmpleado.setBounds(245, 295, 150, 20);
		pEmpleado.add(lblApeEmpleado);
		tFApeEmpleado = new JTextField();
		tFApeEmpleado.setColumns(10);
		tFApeEmpleado.setBounds(405, 295, 150, 20);
		pEmpleado.add(tFApeEmpleado);

		JLabel lblFechaNacEmpleado = new JLabel("Fecha de Nacimiento:");
		lblFechaNacEmpleado.setForeground(new Color(255, 255, 255));
		lblFechaNacEmpleado.setBounds(245, 330, 150, 20);
		pEmpleado.add(lblFechaNacEmpleado);
		tFFechaNacEmpleado = new JTextField("dd-MM-yyyy");
        tFFechaNacEmpleado.setForeground(Color.GRAY);
		tFFechaNacEmpleado.setColumns(10);
		tFFechaNacEmpleado.setBounds(405, 330, 150, 20);
		pEmpleado.add(tFFechaNacEmpleado);

		
		lblNacionEmpleado = new JLabel("Nacionalidad:");
		lblNacionEmpleado.setForeground(new Color(255, 255, 255));
		lblNacionEmpleado.setBounds(245, 365, 150, 20);
		pEmpleado.add(lblNacionEmpleado);
		tFNacionEmpleado = new JTextField();
		tFNacionEmpleado.setColumns(10);
		tFNacionEmpleado.setBounds(405, 365, 150, 20);
		pEmpleado.add(tFNacionEmpleado);
		
		lblNacionEmpleado.setVisible(false);
		tFNacionEmpleado.setVisible(false);
		

		lblLinkEmpleado = new JLabel("Enlace:");
		lblLinkEmpleado.setForeground(new Color(255, 255, 255));
		lblLinkEmpleado.setBounds(245, 365, 150, 20);
		pEmpleado.add(lblLinkEmpleado);
		tFLinkEmpleado = new JTextField();
		tFLinkEmpleado.setColumns(10);
		tFLinkEmpleado.setBounds(405, 365, 150, 20);
		pEmpleado.add(tFLinkEmpleado);
		
		lblLinkEmpleado.setVisible(false);
		tFLinkEmpleado.setVisible(false);

		lblDescEmpleado = new JLabel("Descripción:");
		lblDescEmpleado.setForeground(new Color(255, 255, 255));
		lblDescEmpleado.setBounds(245, 400, 150, 20);
		pEmpleado.add(lblDescEmpleado);
		tADescEmpleado = new JTextArea();
		tADescEmpleado.setBounds(405, 400, 150, 60);
		pEmpleado.add(tADescEmpleado);
		
		lblDescEmpleado.setVisible(false);
		tADescEmpleado.setVisible(false);


		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(300, 480, 90, 25);
		pEmpleado.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 480, 90, 25);
		pEmpleado.add(btnCancelar);
		
		listarUsuarios = new JComboBox<String>();
		listarUsuarios.setBounds(245, 86, 310, 22);
		pEmpleado.add(listarUsuarios);

		lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(245, 190, 150, 20);
		pEmpleado.add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirmar Contraseña:");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setBounds(245, 225, 150, 20);
		pEmpleado.add(lblConfirmPassword);
		
		tFPassword = new JPasswordField();
		tFPassword.setColumns(10);
		tFPassword.setBounds(405, 190, 150, 20);
		pEmpleado.add(tFPassword);
		
		tFConfirmPassword = new JPasswordField();
		tFConfirmPassword.setColumns(10);
		tFConfirmPassword.setBounds(405, 225, 150, 20);
		pEmpleado.add(tFConfirmPassword);
		
		tFFechaNacEmpleado.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (tFFechaNacEmpleado.getText().equals("dd-MM-yyyy")) {
		            tFFechaNacEmpleado.setText("");
		            tFFechaNacEmpleado.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (tFFechaNacEmpleado.getText().isEmpty()) {
		            tFFechaNacEmpleado.setForeground(Color.GRAY);
		            tFFechaNacEmpleado.setText("dd-MM-yyyy");
		        }
		    }
		});
		
		listarUsuarios.addItemListener(new ItemListener() { 
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() != ItemEvent.SELECTED || vistaUsuarioActual == TIPOS_VISTA_USUARIOS.NONE)
					return;
				ISistema sis = Fabrica.getSistema();
				if (vistaUsuarioActual.equals(TIPOS_VISTA_USUARIOS.MODIFICAR)) {
					String nickUsuario = e.getItem().toString();
					DtUsuario info = sis.obtenerDataUsuario(nickUsuario);
					if (info == null) {
						cambiarPlantillaProveedor(false);
						cambiarPlantillaUsuario(false);
						limpiarCamposUsuarios();
						return;
					}
					tFNickEmpleado.setText(nickUsuario);
					tFEmailEmpleado.setText(info.getEmail());
					tFNameEmpleado.setText(info.getNom());
					tFApeEmpleado.setText(info.getApe());
					tFFechaNacEmpleado.setText(info.getFechaNac().format(formatoFecha));
					tFFechaNacEmpleado.setForeground(Color.BLACK);
					if (info.getDesc() != null) {
						tipoUsuarioActual = TIPOS_USUARIOS.PROVEEDOR;
						cambiarPlantillaUsuario(false);
						cambiarPlantillaProveedor(true);
						tFLinkEmpleado.setText(info.getLink());
						tADescEmpleado.setText(info.getDesc());
						tFNacionEmpleado.setText(null);
					}
					if (info.getNacion() != null) {
						tipoUsuarioActual = TIPOS_USUARIOS.TURISTA;
						cambiarPlantillaProveedor(false);
						cambiarPlantillaUsuario(true);
						tFNacionEmpleado.setText(info.getNacion());
					}
				}
				if (vistaUsuarioActual.equals(TIPOS_VISTA_USUARIOS.AGREGAR)) {
					String nuevoTipo = e.getItem().toString();
					if (nuevoTipo.equals("Tipos de Usuarios")) {
						tipoUsuarioActual = TIPOS_USUARIOS.NONE;
						cambiarPlantillaUsuario(false);
						cambiarPlantillaProveedor(false);
					}
					if (nuevoTipo.equals("Proveedor")) {
						tipoUsuarioActual = TIPOS_USUARIOS.PROVEEDOR;
						cambiarPlantillaUsuario(false);
						cambiarPlantillaProveedor(true);
					}
					if (nuevoTipo.equals("Turista")) {	
						tipoUsuarioActual = TIPOS_USUARIOS.TURISTA;
						cambiarPlantillaProveedor(false);
						cambiarPlantillaUsuario(true);
					}					
				}
				
			}
		});
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listarUsuarios.getSelectedItem() == null || listarUsuarios.getSelectedItem().toString() == "" || listarUsuarios.getSelectedIndex() == 0)
					return;
				if (vistaUsuarioActual == TIPOS_VISTA_USUARIOS.MODIFICAR)
					accionBotonAceptarModificarEmpleado();
				if (vistaUsuarioActual == TIPOS_VISTA_USUARIOS.AGREGAR)
					accionBotonAceptarAgregarEmpleado();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		return pEmpleado;
	}
//	Hay que cambiar la cantidad
	private void altaPaquete(String nombre, String desc, String periodoValidez, String descuento, String cantTur, String cantTickeyts) {
		if (testearLargo(nombre) || testearLargo(desc))
			return;
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (desc.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (periodoValidez.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El periodo no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (descuento.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El descuento no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Integer pV;
		try {
			pV = Integer.parseInt(periodoValidez);
			if (testearLargoNum(pV))
				return;
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "El peridodo de validez debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Integer cantTu;
		try {
			cantTu = Integer.parseInt(cantTur);
			if (testearLargoNum(cantTu))
				return;
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "El peridodo de validez debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Integer cantTi;
		try {
			cantTi = Integer.parseInt(cantTur);
			if (testearLargoNum(cantTi))
				return;
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "El peridodo de validez debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		float d;
	    try {
	        d = Float.parseFloat(descuento);
	    } catch(NumberFormatException nfe) {
	    	JOptionPane.showMessageDialog(null, "El descuento debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    if (testearLargoNum(Math.round(d)))
			return;
	    ISistema sis = Fabrica.getSistema();
	    try {
			sis.altaPaquete(new DtPaquete(nombre, desc, pV, d, LocalDate.now(),cantTu,cantTi));
			JOptionPane.showMessageDialog(null, "Paquete ingresado correctamente.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar el paquete", "Error", JOptionPane.ERROR_MESSAGE);			
		}
	}
	
	private JPanel iniciarPanelPaquete(JPanel sistema) {
		JPanel pPaquete = new JPanel();
		pPaquete.setBounds(0, 0, 784, 539);
		pPaquete.setLayout(null);
		pPaquete.setBackground(new Color(18, 18, 18));
		
		JLabel lblTituloPaquete = new JLabel("Agregar Paquete");
		lblTituloPaquete.setBounds(10, 10, 764, 64);
		lblTituloPaquete.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloPaquete.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPaquete.setForeground(Color.white);
		pPaquete.add(lblTituloPaquete);
		
		JLabel lblNombrePaquete= new JLabel("Nombre:");
		lblNombrePaquete.setForeground(Color.WHITE);
		lblNombrePaquete.setBounds(245, 140, 150, 20);
		pPaquete.add(lblNombrePaquete);
		JTextField tFNombrePaquete= new JTextField();
		tFNombrePaquete.setColumns(10);
		tFNombrePaquete.setBounds(405, 140, 150, 20);
		pPaquete.add(tFNombrePaquete);
		
		JLabel lblDescPaquete = new JLabel("Descripción:");
		lblDescPaquete.setForeground(new Color(255, 255, 255));
		lblDescPaquete.setBounds(245, 180, 150, 20);
		pPaquete.add(lblDescPaquete);
		JTextArea tADescPaquete= new JTextArea();
		tADescPaquete.setBounds(405, 180, 150, 80);
		pPaquete.add(tADescPaquete);
		
		JLabel lblPeriodoValidez = new JLabel("Periodo Validez:");
		lblPeriodoValidez.setForeground(Color.WHITE);
		lblPeriodoValidez.setBounds(245, 280, 150, 20);
		pPaquete.add(lblPeriodoValidez);
		JTextField tFPeriodoValidez = new JTextField();
		tFPeriodoValidez.setColumns(10);
		tFPeriodoValidez.setBounds(405, 280, 150, 20);
		pPaquete.add(tFPeriodoValidez);
		
		JLabel lblDescuentoPaquete = new JLabel("Descuento:");
		lblDescuentoPaquete.setForeground(Color.WHITE);
		lblDescuentoPaquete.setBounds(245, 320, 150, 20);
		pPaquete.add(lblDescuentoPaquete);
		JTextField tFDescuentoPaquete = new JTextField();
		tFDescuentoPaquete.setColumns(10);
		tFDescuentoPaquete.setBounds(405, 320, 150, 20);
		pPaquete.add(tFDescuentoPaquete);
		
		JLabel lblCantTur = new JLabel("Cant. Tur.:");
		lblCantTur.setForeground(Color.WHITE);
		lblCantTur.setBounds(245, 360, 150, 20);
		pPaquete.add(lblCantTur);
		JTextField tFCantTur = new JTextField();
		tFCantTur.setColumns(10);
		tFCantTur.setBounds(405, 360, 150, 20);
		pPaquete.add(tFCantTur);
		
		JLabel lblCantTickets = new JLabel("Cant. Tickets:");
		lblCantTickets.setForeground(Color.WHITE);
		lblCantTickets.setBounds(245, 400, 150, 20);
		pPaquete.add(lblCantTickets);
		JTextField tFCantTickets = new JTextField();
		tFCantTickets.setColumns(10);
		tFCantTickets.setBounds(405, 400, 150, 20);
		pPaquete.add(tFCantTickets);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(300, 480, 90, 25);
		pPaquete.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 480, 90, 25);
		pPaquete.add(btnCancelar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaPaquete(tFNombrePaquete.getText(), tADescPaquete.getText(), tFPeriodoValidez.getText(), tFDescuentoPaquete.getText(), tFCantTur.getText(), tFCantTickets.getText());
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		
		return pPaquete;
	}
	
	private void altaSalida() {
        String depar = (String) listarDepartamentosS.getSelectedItem();
        if(depar == "" || depar == null || listarDepartamentosS.getSelectedIndex() == 0) {
        	JOptionPane.showMessageDialog(null, "Debe seleccionar un Departamento.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        String acti = listarActividades.getSelectedItem().toString();
        if(acti == "" || acti == null || listarActividades.getSelectedIndex() == 0) {
        	JOptionPane.showMessageDialog(null, "Debe seleccionar una Actividad.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
		String nuevoNom = tFNameSalida.getText();
		if (nuevoNom.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (testearLargo(nuevoNom))
			return;
		LocalDate nuevaFecha = null;
        try {
        	nuevaFecha = LocalDate.parse(tFFechaSalida.getText(), formatoFecha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalTime nuevaHora = null;
        try {
        	nuevaHora = LocalTime.parse(tFHoraSalida.getText(), formatoHora);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de hora inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		String nuevoLugar = tFLugarSalida.getText();
		if (nuevoLugar.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El lugar no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (testearLargo(nuevoLugar))
			return;
        Integer nuevaCantidad;
        try {
        	nuevaCantidad = Integer.parseInt(tFCantMaxSalida.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La Cantidad Maxima debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (testearLargoNum(nuevaCantidad))
			return;
		DtSalidaTuristica sali = new DtSalidaTuristica(nuevoNom,nuevaFecha,nuevaHora,nuevoLugar,nuevaCantidad, LocalDate.now());
		ISistema sis = Fabrica.getSistema();
		try {
			sis.confirmarAltaSalTur(acti, sali);
			JOptionPane.showMessageDialog(null,  "Salida ingresada correctamente", "Inscripcion completa", JOptionPane.INFORMATION_MESSAGE);
			limpiarCamposSalida();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en inscripcion", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JPanel iniciarPanelSalida(JPanel sistema) {
		JPanel pSalida = new JPanel();
		pSalida.setBounds(0, 0, 784, 539);
		pSalida.setBackground(new Color(18, 18, 18));
		pSalida.setLayout(null);
		
		listarDepartamentosS = new JComboBox<String>();
		listarDepartamentosS.setBounds(245, 86, 310, 22);
		pSalida.add(listarDepartamentosS);
		
		listarActividades = new JComboBox<String>();
		listarActividades.setBounds(245, 126, 310, 22);
		pSalida.add(listarActividades);
		
		JLabel lblNomSalida = new JLabel("Nombre:");
		lblNomSalida.setForeground(Color.WHITE);
		lblNomSalida.setBounds(245, 169, 150, 20);
		pSalida.add(lblNomSalida);
		
		JLabel lblFechaSalida = new JLabel("Fecha:");
		lblFechaSalida.setForeground(Color.WHITE);
		lblFechaSalida.setBounds(245, 209, 150, 20);
		pSalida.add(lblFechaSalida);
		
		JLabel lblHoraSalida = new JLabel("Hora:");
		lblHoraSalida.setForeground(Color.WHITE);
		lblHoraSalida.setBounds(245, 249, 150, 20);
		pSalida.add(lblHoraSalida);
		
		JLabel lblLugarSalida = new JLabel("Lugar:");
		lblLugarSalida.setForeground(Color.WHITE);
		lblLugarSalida.setBounds(245, 289, 150, 20);
		pSalida.add(lblLugarSalida);
		
		JLabel lblCantMaxSalida = new JLabel("Cant. Máxima:");
		lblCantMaxSalida.setForeground(Color.WHITE);
		lblCantMaxSalida.setBounds(245, 329, 150, 20);
		pSalida.add(lblCantMaxSalida);
		
		tFCantMaxSalida = new JTextField();
		tFCantMaxSalida.setBounds(403, 330, 152, 19);
		pSalida.add(tFCantMaxSalida);
		tFCantMaxSalida.setColumns(10);
		
		tFLugarSalida = new JTextField();
		tFLugarSalida.setColumns(10);
		tFLugarSalida.setBounds(403, 290, 152, 19);
		pSalida.add(tFLugarSalida);
		
		tFHoraSalida = new JTextField();
		tFHoraSalida.setColumns(10);
		tFHoraSalida.setBounds(405, 250, 150, 19);
		pSalida.add(tFHoraSalida);
		
		tFNameSalida = new JTextField();
		tFNameSalida.setColumns(10);
		tFNameSalida.setBounds(405, 170, 150, 19);
		pSalida.add(tFNameSalida);
		
		tFFechaSalida = new JTextField();
		tFFechaSalida.setColumns(10);
		tFFechaSalida.setBounds(405, 210, 150, 20);
		pSalida.add(tFFechaSalida);
		
		JLabel lblTituloAltaSalida = new JLabel("Alta Salida");
		lblTituloAltaSalida.setBounds(10, 10, 764, 64);
		lblTituloAltaSalida.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloAltaSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAltaSalida.setForeground(Color.white);
		pSalida.add(lblTituloAltaSalida);
		
		tFFechaSalida.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (tFFechaSalida.getText().equals("dd-MM-yyyy")) {
		        	tFFechaSalida.setText("");
		        	tFFechaSalida.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (tFFechaSalida.getText().isEmpty()) {
		        	tFFechaSalida.setForeground(Color.GRAY);
		        	tFFechaSalida.setText("dd-MM-yyyy");
		        }
		    }
		});
		
		tFHoraSalida.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (tFHoraSalida.getText().equals("hh:mm")) {
		        	tFHoraSalida.setText("");
		        	tFHoraSalida.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (tFHoraSalida.getText().isEmpty()) {
		        	tFHoraSalida.setForeground(Color.GRAY);
		        	tFHoraSalida.setText("hh:mm");
		        }
		    }
		});
		
		listarDepartamentosS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarDepartamentosS.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarDepartamentosS.getSelectedIndex() == 0) {
                	listarActividades.removeAllItems();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				List<String> lista = new ArrayList<String>();
				try {
					lista = sis.seleccionarDepartamentoConf(selectedItem);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
				listarActividades.removeAllItems();
				listarActividades.addItem("Actividades");
				for (String elem : lista) 
					listarActividades.addItem(elem);
            }
        });

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(300, 480, 90, 25);
		pSalida.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 480, 90, 25);
		pSalida.add(btnCancelar);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaSalida();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		return pSalida;
	
	}
	
	private JPanel iniciarPanelConsSalida(JPanel sistema) {
		JPanel pConsSalida = new JPanel();
		pConsSalida.setBounds(0, 0, 784, 539);
		pConsSalida.setBackground(new Color(18, 18, 18));
		pConsSalida.setLayout(null);
		
		listarDepartamentosCS = new JComboBox<String>();
		listarDepartamentosCS.setBounds(245, 86, 310, 22);
		pConsSalida.add(listarDepartamentosCS);
		
		listarActividadesCS = new JComboBox<String>();
		listarActividadesCS.setBounds(245, 126, 310, 22);
		pConsSalida.add(listarActividadesCS);
		
		listarSalidasCS = new JComboBox<String>();
		listarSalidasCS.setBounds(245, 166, 310, 22);
		pConsSalida.add(listarSalidasCS);
		
		JLabel lblNomSalida = new JLabel("Nombre:");
		lblNomSalida.setForeground(Color.WHITE);
		lblNomSalida.setBounds(245, 209, 150, 20);
		pConsSalida.add(lblNomSalida);
		
		JLabel lblFechaSalida = new JLabel("Fecha:");
		lblFechaSalida.setForeground(Color.WHITE);
		lblFechaSalida.setBounds(245, 249, 150, 20);
		pConsSalida.add(lblFechaSalida);
		
		JLabel lblHoraSalida = new JLabel("Hora:");
		lblHoraSalida.setForeground(Color.WHITE);
		lblHoraSalida.setBounds(245, 289, 150, 20);
		pConsSalida.add(lblHoraSalida);
		
		JLabel lblLugarSalida = new JLabel("Lugar:");
		lblLugarSalida.setForeground(Color.WHITE);
		lblLugarSalida.setBounds(245, 329, 150, 20);
		pConsSalida.add(lblLugarSalida);
		
		JLabel lblCantMaxSalida = new JLabel("Cant. Máxima:");
		lblCantMaxSalida.setForeground(Color.WHITE);
		lblCantMaxSalida.setBounds(245, 369, 150, 20);
		pConsSalida.add(lblCantMaxSalida);
		
		tFCantMaxSalidaCS = new JTextField();
		tFCantMaxSalidaCS.setBounds(403, 370, 152, 19);
		tFCantMaxSalidaCS.setColumns(10);
		tFCantMaxSalidaCS.setEditable(false);
		pConsSalida.add(tFCantMaxSalidaCS);
		
		tFLugarSalidaCS = new JTextField();
		tFLugarSalidaCS.setColumns(10);
		tFLugarSalidaCS.setBounds(403, 330, 152, 19);
		tFLugarSalidaCS.setEditable(false);
		pConsSalida.add(tFLugarSalidaCS);
		
		tFHoraSalidaCS = new JTextField();
		tFHoraSalidaCS.setColumns(10);
		tFHoraSalidaCS.setBounds(405, 290, 150, 19);
		tFHoraSalidaCS.setEditable(false);
		pConsSalida.add(tFHoraSalidaCS);
		
		tFNameSalidaCS = new JTextField();
		tFNameSalidaCS.setColumns(10);
		tFNameSalidaCS.setBounds(405, 210, 150, 19);
		tFNameSalidaCS.setEditable(false);
		pConsSalida.add(tFNameSalidaCS);
		
		tFFechaSalidaCS = new JTextField();
		tFFechaSalidaCS.setColumns(10);
		tFFechaSalidaCS.setBounds(405, 250, 150, 20);
		tFFechaSalidaCS.setEditable(false);
		pConsSalida.add(tFFechaSalidaCS);
		
		JLabel lblTituloAltaSalida = new JLabel("Consulta Salida");
		lblTituloAltaSalida.setBounds(10, 10, 764, 64);
		lblTituloAltaSalida.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloAltaSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAltaSalida.setForeground(Color.white);
		pConsSalida.add(lblTituloAltaSalida);
		
		
		listarDepartamentosCS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarDepartamentosCS.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarDepartamentosCS.getSelectedIndex() == 0) {
                	listarActividadesCS.removeAllItems();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				List<String> lista = new ArrayList<String>();
				try {
					lista = sis.seleccionarDepartamentoConf(selectedItem);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				listarActividadesCS.removeAllItems();
				listarActividadesCS.addItem("Actividades");
				for (String elem : lista) 
					listarActividadesCS.addItem(elem);
            }
        });
		
		listarActividadesCS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarActividadesCS.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarActividadesCS.getSelectedIndex() == 0) {
                	listarSalidasCS.removeAllItems();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				List<String> lista = new ArrayList<String>();
				try {
					lista = sis.seleccionarActividadST(selectedItem);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
				listarSalidasCS.removeAllItems();
				listarSalidasCS.addItem("Salidas");
				for (String elem : lista) 
					listarSalidasCS.addItem(elem);
            }
        });
		
		listarSalidasCS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarSalidasCS.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarSalidasCS.getSelectedIndex() == 0) {
                	limpiarCamposSalidaCS();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
                DtSalidaTuristica salida = sis.seleccionarSalida(selectedItem);
                tFNameSalidaCS.setText(salida.getNombreSalida());
                tFFechaSalidaCS.setText(salida.getFechaSalida().format(formatoFecha));
                tFHoraSalidaCS.setText(salida.getHoraSalida().format(formatoHora));
                tFLugarSalidaCS.setText(salida.getLugarSalida());
                tFCantMaxSalidaCS.setText(String.valueOf(salida.getCantMaxTur()));
            }
        });

		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(355, 480, 90, 25);
		pConsSalida.add(btnSalir);
			
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		return pConsSalida;
	
	}
	
	private void altaActividad() {
        String depar = (String) listarDepartamentosA.getSelectedItem();
        if(depar == "" || depar == null || listarDepartamentosA.getSelectedIndex() == 0) {
        	JOptionPane.showMessageDialog(null, "Debe seleccionar un Departamento.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        String prov = (String) listarProveedores.getSelectedItem();
        if(prov == "" || prov == null || listarProveedores.getSelectedIndex() == 0) {
        	JOptionPane.showMessageDialog(null, "Debe seleccionar un Proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
		String nuevoNom = tFNomActividad.getText();
		if (testearLargo(nuevoNom))
			return;
		if (nuevoNom.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
        Integer nuevaDuracion;
        try {
        	nuevaDuracion = Integer.parseInt(tFDuracionActividad.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La Duracion en horas debe ser un numero entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (testearLargoNum(nuevaDuracion))
			return;
        Float nuevoCosto;
        try {
        	nuevoCosto = Float.parseFloat(tFCostoActividad.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "El costo debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (testearLargoNum(Math.round(nuevoCosto)))
			return;
		String nuevaCiudad = tFCiudadActividad.getText();
		if (nuevaCiudad.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La ciudad no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}		
        if (testearLargo(nuevaCiudad))
			return;
		String nuevaDescripcion = tADescActividad.getText();
        if (testearLargo(nuevaDescripcion))
			return;
        if (nuevaDescripcion.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		DtActTur act = new DtActTur(nuevoNom,nuevaDescripcion,nuevaDuracion,nuevoCosto,nuevaCiudad, LocalDate.now() );
		ISistema sis = Fabrica.getSistema();
		List<String> cates = new ArrayList<String>();
        for (int i = 0; i < listarCategoriasSelectA.getItemCount(); i++) {
            cates.add((String) listarCategoriasSelectA.getItemAt(i));
        }
		try {
			sis.altaActividad(prov, depar, act, cates);
			JOptionPane.showMessageDialog(null,  "Actividad ingresada correctamente", "Inscripcion completa", JOptionPane.INFORMATION_MESSAGE);
			limpiarCamposActividad();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en el alta", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private JPanel iniciarPanelActividad(JPanel sistema) {
		JPanel pActividad = new JPanel();
		pActividad.setBounds(0, 0, 784, 539);
		pActividad.setBackground(new Color(18, 18, 18));
		pActividad.setLayout(null);
		
		listarCategoriasA = new JComboBox<String>();
		listarCategoriasA.setBounds(245, 66, 310, 22);
		pActividad.add(listarCategoriasA);
		
		listarDepartamentosA = new JComboBox<String>();
		listarDepartamentosA.setBounds(245, 96, 310, 22);
		pActividad.add(listarDepartamentosA);
		
		listarProveedores = new JComboBox<String>();
		listarProveedores.setBounds(245, 126, 310, 22);
		pActividad.add(listarProveedores);
		
		JLabel lblNomActividad = new JLabel("Nombre:");
		lblNomActividad.setForeground(Color.WHITE);
		lblNomActividad.setBounds(245, 169, 150, 20);
		pActividad.add(lblNomActividad);
		
		JLabel lblDuracionActividad = new JLabel("Duración:");
		lblDuracionActividad.setForeground(Color.WHITE);
		lblDuracionActividad.setBounds(245, 209, 150, 20);
		pActividad.add(lblDuracionActividad);
		
		JLabel lblCostoActividad = new JLabel("Costo:");
		lblCostoActividad.setForeground(Color.WHITE);
		lblCostoActividad.setBounds(245, 249, 150, 20);
		pActividad.add(lblCostoActividad);
		
		JLabel lblCiudadActividad = new JLabel("Ciudad:");
		lblCiudadActividad.setForeground(Color.WHITE);
		lblCiudadActividad.setBounds(245, 289, 150, 20);
		pActividad.add(lblCiudadActividad);
		
		JLabel lblDescripcionActividad = new JLabel("Descripción:");
		lblDescripcionActividad.setForeground(Color.WHITE);
		lblDescripcionActividad.setBounds(245, 329, 150, 20);
		pActividad.add(lblDescripcionActividad);
		
		tADescActividad= new JTextArea();
		tADescActividad.setBounds(405, 330, 150, 80);
		pActividad.add(tADescActividad);
		
		tFCiudadActividad = new JTextField();
		tFCiudadActividad.setColumns(10);
		tFCiudadActividad.setBounds(405, 290, 152, 19);
		pActividad.add(tFCiudadActividad);
		
		tFCostoActividad = new JTextField();
		tFCostoActividad.setColumns(10);
		tFCostoActividad.setBounds(405, 250, 150, 19);
		pActividad.add(tFCostoActividad);
		
		tFNomActividad = new JTextField();
		tFNomActividad.setColumns(10);
		tFNomActividad.setBounds(405, 170, 150, 19);
		pActividad.add(tFNomActividad);
		
		tFDuracionActividad = new JTextField();
		tFDuracionActividad.setColumns(10);
		tFDuracionActividad.setBounds(405, 210, 150, 19);
		pActividad.add(tFDuracionActividad);

		listarCategoriasSelectA = new JComboBox<String>();
		listarCategoriasSelectA.setBounds(575, 66, 120, 22);
		pActividad.add(listarCategoriasSelectA);
		
		JLabel lblTituloAltaActividad = new JLabel("Alta Actividad");
		lblTituloAltaActividad.setBounds(10, 10, 764, 64);
		lblTituloAltaActividad.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloAltaActividad.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAltaActividad.setForeground(Color.white);
		pActividad.add(lblTituloAltaActividad);

		JButton btnCruz = new JButton("X");
		btnCruz.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCruz.setBounds(700, 66, 40, 22);
		pActividad.add(btnCruz);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(300, 480, 90, 25);
		pActividad.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 480, 90, 25);
		pActividad.add(btnCancelar);
		
		listarCategoriasA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarCategoriasA.getSelectedItem();
                if(selectedItem == "Categorias" || selectedItem == null || listarCategoriasA.getSelectedIndex() == 0) {
                	return;
                }
				listarCategoriasSelectA.addItem(selectedItem);
				listarCategoriasA.removeItem(selectedItem);
            }
        });
		
		btnCruz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCategoria();
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaActividad();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		return pActividad;
	
	}
	
	private void mostrarInfoActividad(String nombreAct) {
        ISistema sis = Fabrica.getSistema();
        
        List<String> listaS = new ArrayList<String>();
        try {
        	sis.seleccionarDepartamentoSegunActividad(nombreAct);
        	listaS = sis.seleccionarActividadST(nombreAct);
        } catch (Exception e1) {
			System.err.println("Exception: " + e1.getMessage());
		}
        listarSalidasCA.removeAllItems();
		listarSalidasCA.addItem("Salidas");
		for (String elem : listaS)
			listarSalidasCA.addItem(elem);
		
		List<String> listaP = new ArrayList<String>();
        try {
        	listaP = sis.verProveedorCA(nombreAct);
        } catch (Exception e1) {
			e1.printStackTrace();
		}
        listarPaquetesCA.removeAllItems();
		listarPaquetesCA.addItem("Paquetes");
		for (String elem : listaP) 
			listarPaquetesCA.addItem(elem);
        
        DtActTurEst actividad = sis.seleccionarActividadDtEst(nombreAct);
        tFNomCActividad.setText(actividad.getNombre());
        tADescCActividad.setText(actividad.getDesc());
        tFCiudadCActividad.setText(actividad.getCiudad());
        tFCostoCActividad.setText(String.valueOf(actividad.getCosto()));
        tFDuracionCActividad.setText(String.valueOf(actividad.getDuracion()));
        tFFechaActividad.setText(actividad.getFecha().format(formatoFecha));
        tFEstadoAct.setText(String.valueOf(actividad.getEstado()));
	}
	
	private JPanel iniciarPanelConsPaquete(JPanel sistema) {
		JPanel pConsPaquete = new JPanel();
		pConsPaquete.setBounds(0, 0, 784, 539);
		pConsPaquete.setBackground(new Color(18, 18, 18));
		pConsPaquete.setLayout(null);
		
		listarPaquetes = new JComboBox<String>();
		listarPaquetes.setBounds(245, 66, 310, 22);
		pConsPaquete.add(listarPaquetes);
		
		listarActividadesCP = new JComboBox<String>();
		listarActividadesCP.setBounds(245, 100, 310, 22);
		pConsPaquete.add(listarActividadesCP);
		
		listarCategoriasCP = new JComboBox<String>();
		listarCategoriasCP.setBounds(245, 134, 310, 22);
		pConsPaquete.add(listarCategoriasCP);
		
		JLabel lblNomPaquete = new JLabel("Nombre:");
		lblNomPaquete.setForeground(Color.WHITE);
		lblNomPaquete.setBounds(245, 169, 150, 20);
		pConsPaquete.add(lblNomPaquete);
		
		JLabel lblPerValP = new JLabel("Per. Validez:");
		lblPerValP.setForeground(Color.WHITE);
		lblPerValP.setBounds(245, 209, 150, 20);
		pConsPaquete.add(lblPerValP);
		
		JLabel lblDescuentoP = new JLabel("Descuento:");
		lblDescuentoP.setForeground(Color.WHITE);
		lblDescuentoP.setBounds(245, 249, 150, 20);
		pConsPaquete.add(lblDescuentoP);
		
		JLabel lblFechaAltaP = new JLabel("Fecha de Alta:");
		lblFechaAltaP.setForeground(Color.WHITE);
		lblFechaAltaP.setBounds(245, 289, 150, 20);
		pConsPaquete.add(lblFechaAltaP);
		
		JLabel lblDescripcionP = new JLabel("Descripción:");
		lblDescripcionP.setForeground(Color.WHITE);
		lblDescripcionP.setBounds(245, 329, 150, 20);
		pConsPaquete.add(lblDescripcionP);

		
		tADescripcionP = new JTextArea();
		tADescripcionP.setBounds(405, 330, 150, 80);
		tADescripcionP.setEditable(false);
		pConsPaquete.add(tADescripcionP);
		
		tFFechaAltaP = new JTextField();
		tFFechaAltaP.setColumns(10);
		tFFechaAltaP.setBounds(403, 290, 152, 19);
		tFFechaAltaP.setEditable(false);
		pConsPaquete.add(tFFechaAltaP);
		
		tFDescuentoP = new JTextField();
		tFDescuentoP.setColumns(10);
		tFDescuentoP.setBounds(405, 250, 150, 19);
		tFDescuentoP.setEditable(false);
		pConsPaquete.add(tFDescuentoP);
		
		tFNomPaquete = new JTextField();
		tFNomPaquete.setColumns(10);
		tFNomPaquete.setBounds(405, 170, 150, 19);
		tFNomPaquete.setEditable(false);
		pConsPaquete.add(tFNomPaquete);
		
		tFPerValP = new JTextField();
		tFPerValP.setColumns(10);
		tFPerValP.setBounds(405, 210, 150, 20);
		tFPerValP.setEditable(false);
		pConsPaquete.add(tFPerValP);
		
		JLabel lblTituloAltaSalida = new JLabel("Consultar Paquete");
		lblTituloAltaSalida.setBounds(10, 10, 764, 64);
		lblTituloAltaSalida.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloAltaSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAltaSalida.setForeground(Color.white);
		pConsPaquete.add(lblTituloAltaSalida);
		
		listarPaquetes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarPaquetes.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarPaquetes.getSelectedIndex() == 0) {
                	limpiarCamposSalidaCP();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				DtPaquete data = null;
				try {
					data = sis.imprimirInfoPaquete(selectedItem);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				listarActividadesCP.removeAllItems();
				listarActividadesCP.addItem("Actividades");
				for (String elem : data.getNombreActividades()) 
					listarActividadesCP.addItem(elem);
				List<String> lis = sis.verCategoriasCP(selectedItem);
				listarCategoriasCP.removeAllItems();
				listarCategoriasCP.addItem("Categorias");
				for (String elem : lis) 
					listarCategoriasCP.addItem(elem);
				tADescripcionP.setText(data.getDesc());
				tFFechaAltaP.setText(data.getFechaAlta().format(formatoFecha));
				tFDescuentoP.setText(Float.toString(data.getDescuento()));
				tFNomPaquete.setText(data.getNombre());
				tFPerValP.setText(Integer.toString(data.getPeriodoValidez()));
				
            }
        });
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(360, 480, 90, 25);
		pConsPaquete.add(btnSalir);
	

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		
		return pConsPaquete;
	
	}

	private JPanel iniciarPanelAgregarActAPaquete(JPanel sistema) {
		JPanel pAgregarActAPaquete = new JPanel();
		pAgregarActAPaquete.setBounds(0, 0, 784, 539);
		pAgregarActAPaquete.setBackground(new Color(18, 18, 18));
		pAgregarActAPaquete.setLayout(null);
		
		JLabel lblTituloAgregarActAPaquete = new JLabel("Agregar Actividad a Paquete");
		lblTituloAgregarActAPaquete.setBounds(10, 10, 764, 64);
		lblTituloAgregarActAPaquete.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloAgregarActAPaquete.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAgregarActAPaquete.setForeground(Color.white);
		pAgregarActAPaquete.add(lblTituloAgregarActAPaquete);
						
		listarPaquetesAAP = new JComboBox<String>();
		listarPaquetesAAP.setBounds(245, 86, 310, 22);
		pAgregarActAPaquete.add(listarPaquetesAAP);
		
		listarDepartamentosAAP = new JComboBox<String>();
		listarDepartamentosAAP.setBounds(245, 126, 310, 22);
		pAgregarActAPaquete.add(listarDepartamentosAAP);
		
		listarActividadesAAP = new JComboBox<String>();
		listarActividadesAAP.setBounds(245, 166, 310, 22);
		pAgregarActAPaquete.add(listarActividadesAAP);
		
		listarPaquetesAAP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(listarPaquetesAAP.getSelectedIndex() == 0 || listarPaquetesAAP.getSelectedIndex() == -1) {
                	limpiarCamposAAP();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				String nombrePaquete = listarPaquetesAAP.getSelectedItem().toString();
				try {
					sis.seleccionarPaquete(nombrePaquete);
				} catch (Exception err) {
					System.err.println(err);
				}
				if (listarDepartamentosAAP.getSelectedIndex() == 0) 
					return;
				listarActividadesAAP.removeAllItems();
				listarActividadesAAP.addItem("Actividades");
				sis.listarActividadesDepartamentoConf().forEach(t -> listarActividadesAAP.addItem(t));
			}
		});
		
		listarDepartamentosAAP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(listarDepartamentosAAP.getSelectedIndex() == 0 || listarDepartamentosAAP.getSelectedIndex() == -1) {
                	limpiarCamposAAP();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				String nombreDepartamento = listarDepartamentosAAP.getSelectedItem().toString();
				try {
					sis.seleccionarDepartamento(nombreDepartamento);
				} catch (Exception err) {
					System.err.println(err);
				}
				if (listarPaquetesAAP.getSelectedIndex() == 0)
					return;
				listarActividadesAAP.removeAllItems();
				listarActividadesAAP.addItem("Actividades");
				sis.listarActividadesDepartamentoConf().forEach(t -> listarActividadesAAP.addItem(t));

			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(300, 480, 90, 25);
		pAgregarActAPaquete.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 480, 90, 25);
		pAgregarActAPaquete.add(btnCancelar);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listarPaquetesAAP.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un paquete", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (listarDepartamentosAAP.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un departamento", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (listarActividadesAAP.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una actividad", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ISistema sis = Fabrica.getSistema();
				String nombreActividad = listarActividadesAAP.getSelectedItem().toString();
				try {
					sis.seleccionarActividadParaPaquete(nombreActividad);
					JOptionPane.showMessageDialog(null, "Se agrego correctamente la actividad al paquete", "Finaliza correctamente", JOptionPane.INFORMATION_MESSAGE);
					listarActividadesAAP.removeItemAt(listarActividadesAAP.getSelectedIndex());
					listarActividadesAAP.setSelectedIndex(0);
				} catch (Exception er) {
					JOptionPane.showMessageDialog(null, er.getMessage(), "Error al agregar", JOptionPane.ERROR_MESSAGE);					
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		
		return pAgregarActAPaquete;
	}
	
	private void mostrarInfoPaquete(String nombrePaq) {
		ISistema sis = Fabrica.getSistema();
		cargarListaPaquetes(listarPaquetes);
		DtPaquete data = null;
		try {
			data = sis.imprimirInfoPaquete(nombrePaq);
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		listarActividadesCP.removeAllItems();
		listarActividadesCP.addItem("Actividades");
		for (String elem : data.getNombreActividades()) 
			listarActividadesCP.addItem(elem);
		tADescripcionP.setText(data.getDesc());
		tFFechaAltaP.setText(data.getFechaAlta().format(formatoFecha));
		tFDescuentoP.setText(Float.toString(data.getDescuento()));
		tFNomPaquete.setText(data.getNombre());
		tFPerValP.setText(Integer.toString(data.getPeriodoValidez()));
	}
	
	private void mostrarInfoSalida(String nombreSal, String nombreAct, String nombreDep) {
		ISistema sis = Fabrica.getSistema();
		cargarListaDepartamentos(listarDepartamentosCS);
		
		DtSalidaTuristica salida = sis.seleccionarSalidaA(nombreSal,nombreAct,nombreDep);
        tFNameSalidaCS.setText(salida.getNombreSalida());
        tFFechaSalidaCS.setText(salida.getFechaSalida().format(formatoFecha));
        tFHoraSalidaCS.setText(salida.getHoraSalida().format(formatoHora));
        tFLugarSalidaCS.setText(salida.getLugarSalida());
        tFCantMaxSalidaCS.setText(String.valueOf(salida.getCantMaxTur()));
	}
	
	private void mostrarInfoSalidaO(String nombreSal) {
		ISistema sis = Fabrica.getSistema();
		cargarListaDepartamentos(listarDepartamentosCS);
		
		
		sis.guardarDepartamentoYActividadSegunSalida(nombreSal);
		DtSalidaTuristica salida = sis.seleccionarSalida(nombreSal);
        tFNameSalidaCS.setText(salida.getNombreSalida());
        tFFechaSalidaCS.setText(salida.getFechaSalida().format(formatoFecha));
        tFHoraSalidaCS.setText(salida.getHoraSalida().format(formatoHora));
        tFLugarSalidaCS.setText(salida.getLugarSalida());
        tFCantMaxSalidaCS.setText(String.valueOf(salida.getCantMaxTur()));
	}
	
//	private void mostrarInfoSalidaCU(String nombreSal) {
//		ISistema sis = Fabrica.getSistema();
//		cargarUsuariosCU(listarUsuariosCU);
//		
//		DtSalidaTuristica salida = sis.seleccionarSalida(nombreSal);
//        tFNameSalidaCS.setText(salida.getNombreSalida());
//        tFFechaSalidaCS.setText(salida.getFechaSalida().format(formatoFecha));
//        tFHoraSalidaCS.setText(salida.getHoraSalida().format(formatoHora));
//        tFLugarSalidaCS.setText(salida.getLugarSalida());
//        tFCantMaxSalidaCS.setText(String.valueOf(salida.getCantMaxTur()));
//	}
	
	private void mostrarInfoActividadCU(String nombreAct) {
		ISistema sis = Fabrica.getSistema();
		cargarListaDepartamentos(listarDepartamentosCA);
		
//		List<String> listaS = new ArrayList<String>() ;
//        try {
//        	listaS = sis.seleccionarActividadST(nombreAct);
//        } catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
//        DtActTur actividad = sis.seleccionarActividadDt(nombreAct);
        DtActTurEst actividad = sis.seleccionarActividadDtEst(nombreAct);

        tFNomCActividad.setText(actividad.getNombre());
        tADescCActividad.setText(actividad.getDesc());
        tFCiudadCActividad.setText(actividad.getCiudad());
        tFCostoCActividad.setText(String.valueOf(actividad.getCosto()));
        tFDuracionCActividad.setText(String.valueOf(actividad.getDuracion()));
        tFFechaActividad.setText(actividad.getFecha().format(formatoFecha));
        tFEstadoAct.setText(String.valueOf(actividad.getEstado()));
	}
	
	private JPanel iniciarPanelConsActividad(JPanel sistema, JPanel paquete, JPanel salida) {
		JPanel pConsActividad = new JPanel();
		pConsActividad.setBounds(0, 0, 784, 539);
		pConsActividad.setBackground(new Color(18, 18, 18));
		pConsActividad.setLayout(null);
		
		JLabel lblTituloConsAct = new JLabel("Consulta Actividad");
		lblTituloConsAct.setBounds(10, -10, 764, 64);
		lblTituloConsAct.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloConsAct.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloConsAct.setForeground(Color.white);
		pConsActividad.add(lblTituloConsAct);
		
		listarDepartamentosCA = new JComboBox<String>();
		listarDepartamentosCA.setBounds(245, 36, 310, 22);
		pConsActividad.add(listarDepartamentosCA);
			
		
		listarActividadesCA = new JComboBox<String>();
		listarActividadesCA.setBounds(245, 68, 310, 22);
		pConsActividad.add(listarActividadesCA);
		
		JLabel lblNomActividad = new JLabel("Nombre:");
		lblNomActividad.setForeground(Color.WHITE);
		lblNomActividad.setBounds(245, 190, 150, 20);
		pConsActividad.add(lblNomActividad);
		
		JLabel lblDuracionActividad = new JLabel("Duración:");
		lblDuracionActividad.setForeground(Color.WHITE);
		lblDuracionActividad.setBounds(245, 225, 150, 20);
		pConsActividad.add(lblDuracionActividad);
		
		JLabel lblCostoActividad = new JLabel("Costo:");
		lblCostoActividad.setForeground(Color.WHITE);
		lblCostoActividad.setBounds(245, 260, 150, 20);
		pConsActividad.add(lblCostoActividad);
		
		JLabel lblCiudadActividad = new JLabel("Ciudad:");
		lblCiudadActividad.setForeground(Color.WHITE);
		lblCiudadActividad.setBounds(245, 295, 150, 20);
		pConsActividad.add(lblCiudadActividad);
		
		JLabel lblDescripcionActividad = new JLabel("Descripción:");
		lblDescripcionActividad.setForeground(Color.WHITE);
		lblDescripcionActividad.setBounds(245, 330, 150, 20);
		pConsActividad.add(lblDescripcionActividad);
		
		tFNomCActividad = new JTextField();
		tFNomCActividad.setColumns(10);
		tFNomCActividad.setBounds(405, 190, 150, 19);
		tFNomCActividad.setEditable(false);
		pConsActividad.add(tFNomCActividad);
		
		tFDuracionCActividad = new JTextField();
		tFDuracionCActividad.setColumns(10);
		tFDuracionCActividad.setBounds(405, 225, 150, 19);
		tFDuracionCActividad.setEditable(false);
		pConsActividad.add(tFDuracionCActividad);
		
		tFCostoCActividad = new JTextField();
		tFCostoCActividad.setColumns(10);
		tFCostoCActividad.setBounds(405, 260, 150, 19);
		tFCostoCActividad.setEditable(false);
		pConsActividad.add(tFCostoCActividad);
		
		tFCiudadCActividad = new JTextField();
		tFCiudadCActividad.setColumns(10);
		tFCiudadCActividad.setBounds(405, 295, 152, 19);
		tFCiudadCActividad.setEditable(false);
		pConsActividad.add(tFCiudadCActividad);
		
		tADescCActividad = new JTextArea();
		tADescCActividad.setBounds(405, 330, 150, 80);
		tADescCActividad.setEditable(false);
		pConsActividad.add(tADescCActividad);
		
		tFFechaActividad = new JTextField();
		tFFechaActividad.setColumns(10);
		tFFechaActividad.setBounds(405, 427, 152, 19);
		tFFechaActividad.setEditable(false);
		pConsActividad.add(tFFechaActividad);
		
		JLabel lblFechaActividad = new JLabel("Fecha:");
		lblFechaActividad.setForeground(Color.WHITE);
		lblFechaActividad.setBounds(245, 427, 150, 20);
		pConsActividad.add(lblFechaActividad);
				
		tFEstadoAct = new JTextField();
		tFEstadoAct.setColumns(10);
		tFEstadoAct.setBounds(405, 462, 152, 19);
		tFEstadoAct.setEditable(false);
		pConsActividad.add(tFEstadoAct);
		
		JLabel lblEstadoAct = new JLabel("Estado:");
		lblEstadoAct.setForeground(Color.WHITE);
		lblEstadoAct.setBounds(245, 462, 150, 20);
		pConsActividad.add(lblEstadoAct);
		
		listarSalidasCA = new JComboBox<String>();
		listarSalidasCA.setBounds(245, 100, 310, 22);
		pConsActividad.add(listarSalidasCA);
		
		listarPaquetesCA = new JComboBox<String>();
		listarPaquetesCA.setBounds(245, 132, 310, 22);
		pConsActividad.add(listarPaquetesCA);
		
		listarCategoriasCA = new JComboBox<String>();
		listarCategoriasCA.setBounds(245, 164, 310, 22);
		pConsActividad.add(listarCategoriasCA);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(467, 495, 90, 25);
		pConsActividad.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
				listarDepartamentosCA.removeAllItems();
				listarActividadesCA.removeAllItems();
				listarSalidasCA.removeAllItems();
				listarPaquetesCA.removeAllItems();
				tADescCActividad.setText("");
				tFCiudadCActividad.setText("");
				tFCostoCActividad.setText("");
				tFNomCActividad.setText("");
				tFDuracionCActividad.setText("");
				tFFechaActividad.setText("");
				tFEstadoAct.setText("");
			}
		});
		
		
		listarDepartamentosCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarDepartamentosCA.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarDepartamentosCA.getSelectedIndex() == 0) {
                	listarActividadesCA.removeAllItems();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				List<String> lista = null;
				try {
					lista = sis.seleccionarDepartamento(selectedItem);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
				listarActividadesCA.removeAllItems();
				listarActividadesCA.addItem("Actividades");
				for (String elem : lista) 
					listarActividadesCA.addItem(elem);
            }
        });
		
		listarActividadesCA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) listarActividadesCA.getSelectedItem();
            	if(selectedItem == "" || selectedItem == null || listarActividadesCA.getSelectedIndex() == 0) {
            		limpiarCamposCActividad();
                	return;
                }
	            ISistema sis = Fabrica.getSistema();
	            
	            List<String> listaS = new ArrayList<String>();
	            try {
	            	listaS = sis.seleccionarActividadST(selectedItem);
	            } catch (Exception e1) {
					e1.printStackTrace();
				}
	            listarSalidasCA.removeAllItems();
				listarSalidasCA.addItem("Salidas");
				for (String elem : listaS) 
					listarSalidasCA.addItem(elem);
				
				List<String> listaP = new ArrayList<String>();
	            try {
	            	listaP = sis.verProveedorCA(selectedItem);
	            } catch (Exception e1) {
					e1.printStackTrace();
				}
	            listarPaquetesCA.removeAllItems();
				listarPaquetesCA.addItem("Paquetes");
				for (String elem : listaP) 
					listarPaquetesCA.addItem(elem);
				List<String> listaC = null;
	            try {
	            	listaC = sis.verCategoriasCA(selectedItem);
	            } catch (Exception e1) {
					e1.printStackTrace();
				}
	            listarCategoriasCA.removeAllItems();
				listarCategoriasCA.addItem("Categorias");
				for (String elem : listaC) 
					listarCategoriasCA.addItem(elem);
	            
	            DtActTurEst actividad = sis.seleccionarActividadDtEst(selectedItem);
	            tFNomCActividad.setText(actividad.getNombre());
	            tADescCActividad.setText(actividad.getDesc());
	            tFCiudadCActividad.setText(actividad.getCiudad());
	            tFCostoCActividad.setText(String.valueOf(actividad.getCosto()));
	            tFDuracionCActividad.setText(String.valueOf(actividad.getDuracion()));
	            tFFechaActividad.setText(actividad.getFecha().format(formatoFecha));
	            tFEstadoAct.setText(String.valueOf(actividad.getEstado()));
			}
		});

		listarPaquetesCA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listarPaquetesCA.getSelectedIndex() == 0 || listarPaquetesCA.getSelectedItem() == null) {
					return;
				}
				String nombrePaquete = listarPaquetesCA.getSelectedItem().toString();
				cambiarVentanaPrincipal(paquete);
				mostrarInfoPaquete(nombrePaquete);
				listarDepartamentosCA.removeAllItems();
				listarActividadesCA.removeAllItems();
				listarSalidasCA.removeAllItems();
				listarPaquetesCA.removeAllItems();
				tADescCActividad.setText("");
				tFCiudadCActividad.setText("");
				tFCostoCActividad.setText("");
				tFNomCActividad.setText("");
				tFDuracionCActividad.setText("");
				tFFechaActividad.setText("");
				tFEstadoAct.setText("");
			}
		});
		
		listarSalidasCA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listarSalidasCA.getSelectedIndex() == 0 || listarSalidasCA.getSelectedItem() == null) {
					return;
				}
				String nombreSal = listarSalidasCA.getSelectedItem().toString();
				String nombreDep = listarDepartamentosCA.getSelectedItem().toString();
				String nombreAct = listarActividadesCA.getSelectedItem().toString();
				cambiarVentanaPrincipal(salida);
				mostrarInfoSalida(nombreSal,nombreAct,nombreDep);
				
				listarDepartamentosCA.removeAllItems();
				listarActividadesCA.removeAllItems();
				listarSalidasCA.removeAllItems();
				listarPaquetesCA.removeAllItems();
				tADescCActividad.setText("");
				tFCiudadCActividad.setText("");
				tFCostoCActividad.setText("");
				tFNomCActividad.setText("");
				tFDuracionCActividad.setText("");
				tFFechaActividad.setText("");
				tFEstadoAct.setText("");
			}
		});

		return pConsActividad;	
	}
		
	private JPanel iniciarPanelConsUsuario(JPanel sistema, JPanel pConsActividad, JPanel pConsSalida) {
		
		JPanel pConsUsuario = new JPanel();
		pConsUsuario.setBounds(0, 0, 784, 539);
		pConsUsuario.setBackground(new Color(18, 18, 18));
		pConsUsuario.setLayout(null);
		
		
		lblTituloConsultaU = new JLabel("Consulta Usuario");		
		lblTituloConsultaU.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloConsultaU.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloConsultaU.setForeground(new Color(255, 255, 255));
		lblTituloConsultaU.setBounds(10, 10, 764, 64);
		pConsUsuario.add(lblTituloConsultaU);

		listarActividadesCU = new JComboBox<String>();
		listarActividadesCU.setBounds(245, 465, 310, 22);
		pConsUsuario.add(listarActividadesCU);
		
		listarSalidasCU = new JComboBox<String>();
		listarSalidasCU.setBounds(245, 497, 310, 22);
		pConsUsuario.add(listarSalidasCU);
		
		JLabel lblNickUsuario = new JLabel("Nickname:");
		lblNickUsuario.setForeground(Color.WHITE);
		lblNickUsuario.setBounds(245, 140, 150, 20);
		pConsUsuario.add(lblNickUsuario);
		tFNickUsuario = new JTextField();
		tFNickUsuario.setColumns(10);
		tFNickUsuario.setBounds(405, 140, 150, 20);
		tFNickUsuario.setEditable(false);
		pConsUsuario.add(tFNickUsuario);
		
		JLabel lblEmailUsuario = new JLabel("Email:");
		lblEmailUsuario.setForeground(Color.WHITE);
		lblEmailUsuario.setBounds(245, 180, 150, 20);
		pConsUsuario.add(lblEmailUsuario);
		tFEmailUsuario = new JTextField();
		tFEmailUsuario.setColumns(10);
		tFEmailUsuario.setBounds(405, 180, 150, 20);
		tFEmailUsuario.setEditable(false);
		pConsUsuario.add(tFEmailUsuario);
		
		JLabel lblNameUsuario = new JLabel("Nombre:");
		lblNameUsuario.setForeground(new Color(255, 255, 255));
		lblNameUsuario.setBounds(245, 220, 150, 20);
		pConsUsuario.add(lblNameUsuario);
		tFNameUsuario = new JTextField();
		tFNameUsuario.setColumns(10);
		tFNameUsuario.setBounds(405, 220, 150, 20);
		tFNameUsuario.setEditable(false);
		pConsUsuario.add(tFNameUsuario);

		JLabel lblApeUsuario = new JLabel("Apellido:");
		lblApeUsuario.setForeground(new Color(255, 255, 255));
		lblApeUsuario.setBounds(245, 260, 150, 20);
		pConsUsuario.add(lblApeUsuario);
		tFApeUsuario = new JTextField();
		tFApeUsuario.setColumns(10);
		tFApeUsuario.setBounds(405, 260, 150, 20);
		tFApeUsuario.setEditable(false);
		pConsUsuario.add(tFApeUsuario);

		JLabel lblFechaNacUsuario = new JLabel("Fecha de Nacimiento:");
		lblFechaNacUsuario.setForeground(new Color(255, 255, 255));
		lblFechaNacUsuario.setBounds(245, 300, 150, 20);
		pConsUsuario.add(lblFechaNacUsuario);
		tFFechaNacUsuario = new JTextField("dd-MM-yyyy");
        tFFechaNacUsuario.setForeground(Color.GRAY);
		tFFechaNacUsuario.setColumns(10);
		tFFechaNacUsuario.setBounds(405, 300, 150, 20);
		tFFechaNacUsuario.setEditable(false);
		pConsUsuario.add(tFFechaNacUsuario);

		
		lblNacionUsuario = new JLabel("Nacionalidad:");
		lblNacionUsuario.setForeground(new Color(255, 255, 255));
		lblNacionUsuario.setBounds(245, 340, 150, 20);
		pConsUsuario.add(lblNacionUsuario);
		tFNacionUsuario = new JTextField();
		tFNacionUsuario.setColumns(10);
		tFNacionUsuario.setBounds(405, 340, 150, 20);
		tFNacionUsuario.setEditable(false);
		pConsUsuario.add(tFNacionUsuario);
		
		lblNacionUsuario.setVisible(false);
		tFNacionUsuario.setVisible(false);
		

		lblLinkUsuario = new JLabel("Enlace:");
		lblLinkUsuario.setForeground(new Color(255, 255, 255));
		lblLinkUsuario.setBounds(245, 340, 150, 20);
		pConsUsuario.add(lblLinkUsuario);
		tFLinkUsuario = new JTextField();
		tFLinkUsuario.setColumns(10);
		tFLinkUsuario.setBounds(405, 340, 150, 20);
		tFLinkUsuario.setEditable(false);
		pConsUsuario.add(tFLinkUsuario);
		
		lblLinkUsuario.setVisible(false);
		tFLinkUsuario.setVisible(false);

		lblDescUsuario = new JLabel("Descripción:");
		lblDescUsuario.setForeground(new Color(255, 255, 255));
		lblDescUsuario.setBounds(245, 380, 150, 20);
		pConsUsuario.add(lblDescUsuario);
		tADescUsuario = new JTextArea();
		tADescUsuario.setBounds(405, 380, 150, 80);
		tADescUsuario.setEditable(false);
		pConsUsuario.add(tADescUsuario);
		
		lblDescUsuario.setVisible(false);
		tADescUsuario.setVisible(false);
		
		listarInsc = new JComboBox<String>();
		listarInsc.setBounds(245, 380, 310, 22);
		pConsUsuario.add(listarInsc);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(584, 495, 90, 25);
		pConsUsuario.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		listarUsuariosCU = new JComboBox<String>();
		listarUsuariosCU.setBounds(245, 86, 310, 22);
		pConsUsuario.add(listarUsuariosCU);
		
		JButton btnVerActividad = new JButton("Ver Actividad");
		btnVerActividad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVerActividad.setBounds(584, 463, 107, 25);
		pConsUsuario.add(btnVerActividad);
		
//		Apretando este boton se ve la pantalla de la actividad seleccionada
		btnVerActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombreAct = listarActividadesCU.getSelectedItem().toString();
				cambiarVentanaPrincipal(pConsActividad);
				
				mostrarInfoActividadCU(nombreAct);
				
				listarUsuariosCU.removeAllItems();
				listarActividadesCU.removeAllItems();
				listarSalidasCU.removeAllItems();
				listarInsc.removeAllItems();
				tFNickUsuario.setText("");
				tFEmailUsuario.setText("");
				tFNameUsuario.setText("");
				tFApeUsuario.setText("");
				tFFechaNacUsuario.setText("");
				tFLinkUsuario.setText("");
				tADescUsuario.setText("");
				tFNacionUsuario.setText("");
			}
		});
		
		btnVerActividad.setVisible(false);
		listarActividadesCU.setVisible(false);
		listarSalidasCU.setVisible(false);
		listarInsc.setVisible(false);
		
		listarUsuariosCU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) listarUsuariosCU.getSelectedItem();
	            	if(selectedItem == "" || selectedItem == null || listarUsuariosCU.getSelectedIndex() == 0) {
	            		cambiarPlantillaProveedorCU(false);
	    				cambiarPlantillaUsuarioCU(false);
	    				listarActividadesCU.setVisible(false);
						listarSalidasCU.setVisible(false);
						listarInsc.setVisible(false);
						btnVerActividad.setVisible(false);
	            		limpiarCamposUsuariosCU();
	                	return;
	                }
	            
	            	ISistema sis = Fabrica.getSistema();
					DtUsuario data = null;
					try {
						data = sis.obtenerDataUsuario(selectedItem);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					tFNickUsuario.setText(data.getNick());
					tFEmailUsuario.setText(data.getEmail());
					tFNameUsuario.setText(data.getNom());
					tFApeUsuario.setText(data.getApe());
					tFFechaNacUsuario.setText(data.getFechaNac().format(formatoFecha));
					tFFechaNacUsuario.setForeground(Color.BLACK);
//					Si es proveedor
					if (data.getDesc() != null) {
						cambiarPlantillaUsuarioCU(false);
						cambiarPlantillaProveedorCU(true);
						listarActividadesCU.setVisible(true);
						listarSalidasCU.setVisible(true);
						tFLinkUsuario.setText(data.getLink());
						tADescUsuario.setText(data.getDesc());
						tFNacionUsuario.setText(null);
						
						List<String> lista = new ArrayList<String>();
						try {
							lista = sis.verActividadesCU(selectedItem);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						listarActividadesCU.removeAllItems();
						listarActividadesCU.addItem("Actividades");
						for (String elem : lista) 
							listarActividadesCU.addItem(elem);;
						
					}
					else {
						listarActividadesCU.setVisible(false);
						listarSalidasCU.setVisible(false);
					}
//					Si es turista
					if (data.getNacion() != null) {
						listarInsc.setVisible(true);
						btnVerActividad.setVisible(false);
						cambiarPlantillaProveedorCU(false);
						cambiarPlantillaUsuarioCU(true);
						tFNacionUsuario.setText(data.getNacion());
						
						List<String> lista = new ArrayList<String>();
						try {
							lista = sis.verInscripcionesCU(selectedItem);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						listarInsc.removeAllItems();
						listarInsc.addItem("Salidas");
						for (String elem : lista) 
							listarInsc.addItem(elem);
					}
					else {
						listarInsc.setVisible(false);
					}
            }
		});
		
		listarActividadesCU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarActividadesCU.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarActividadesCU.getSelectedIndex() == 0) {
                	btnVerActividad.setVisible(false);
                	listarSalidasCU.removeAllItems();
                	return;
                }
                else {
                	btnVerActividad.setVisible(true);
                }
				ISistema sis = Fabrica.getSistema();
				List<String> lista = new ArrayList<String>();
				try {
					lista = sis.verSalidasCU(selectedItem);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
				listarSalidasCU.removeAllItems();
				listarSalidasCU.addItem("Salidas");
				for (String elem : lista) 
					listarSalidasCU.addItem(elem);
            }
        });
		
//		Cuando se selecciona una salida, se muestra la pantalla de consulta salida con la salida seleccionada
		listarSalidasCU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listarSalidasCU.getSelectedIndex() == 0 || listarSalidasCU.getSelectedItem() == null) {
					return;
				}
				
				String nombreSal = listarSalidasCU.getSelectedItem().toString();
//				String nombreDep = listarUsuariosCU.getSelectedItem().toString();
//				String nombreAct = listarActividadesCU.getSelectedItem().toString();
				
				cambiarVentanaPrincipal(pConsSalida);
				mostrarInfoSalidaO(nombreSal);
				
				listarUsuariosCU.removeAllItems();
				listarActividadesCU.removeAllItems();
				listarSalidasCU.removeAllItems();
				listarInsc.removeAllItems();
				tFNickUsuario.setText("");
				tFEmailUsuario.setText("");
				tFNameUsuario.setText("");
				tFApeUsuario.setText("");
				tFFechaNacUsuario.setText("");
				tFLinkUsuario.setText("");
				tADescUsuario.setText("");
				tFNacionUsuario.setText("");
			}
		});
//		Cuando se selecciona una salida, se muestra la pantalla de consulta salida con la salida seleccionada
		listarInsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listarInsc.getSelectedIndex() == 0 || listarInsc.getSelectedItem() == null) {
					return;
				}
				
				String nombreSal = listarInsc.getSelectedItem().toString();
//				String nombreDep = listarUsuariosCU.getSelectedItem().toString();
//				String nombreAct = listarActividadesCU.getSelectedItem().toString();
				
				cambiarVentanaPrincipal(pConsSalida);
				mostrarInfoSalidaO(nombreSal);
				
				listarUsuariosCU.removeAllItems();
				listarActividadesCU.removeAllItems();
				listarSalidasCU.removeAllItems();
				listarInsc.removeAllItems();
				tFNickUsuario.setText("");
				tFEmailUsuario.setText("");
				tFNameUsuario.setText("");
				tFApeUsuario.setText("");
				tFFechaNacUsuario.setText("");
				tFLinkUsuario.setText("");
				tADescUsuario.setText("");
				tFNacionUsuario.setText("");
			}
		});

		return pConsUsuario;		
	}
	
	private void altaInscripcion() {
		String dep = (String) listarDepartamentosIST.getSelectedItem();
		String act = (String) listarActividadesIST.getSelectedItem();
		String sal = (String) listarSalidasIST.getSelectedItem();
		String tur = (String) listarTuristasIST.getSelectedItem();
		
		if (dep == "" || dep == null || listarDepartamentosIST.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un departamento, una actividad y finalmente una salida.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
		}
		if (act == "" || act == null || listarActividadesIST.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una actividad y luego una salida.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
		}
		if (sal == "" || sal == null || listarSalidasIST.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una salida.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
		}
		if (tur == "" || tur == null || listarTuristasIST.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un turista.", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
		}

		Integer cant;
		try {
        	cant = Integer.parseInt(tFCantTur.getText());
        	if (cant < 0) {
        		throw new Exception("Cantidad mayor a 0");
        	}
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un numero entero mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }		
        if (testearLargoNum(cant))
			return;
		ISistema sis = Fabrica.getSistema();
		try {
			sis.altaInscripcion(tur, sal, cant);
			JOptionPane.showMessageDialog(null,  "Inscripcion ingresada correctamente", "Inscripcion completa", JOptionPane.INFORMATION_MESSAGE);
			limpiarCamposInscripcion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Error en la inscripcion", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	private JPanel iniciarPanelInscSalTur(JPanel sistema) {
		JPanel pInscSalTur = new JPanel();
		pInscSalTur.setBounds(0, 0, 784, 539);
		pInscSalTur.setBackground(new Color(18, 18, 18));
		pInscSalTur.setLayout(null);
		
		JLabel lblTituloInscSalTur = new JLabel("Inscribir a Salida Turistica");
		lblTituloInscSalTur.setBounds(10, 10, 764, 64);
		lblTituloInscSalTur.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloInscSalTur.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloInscSalTur.setForeground(Color.white);
		pInscSalTur.add(lblTituloInscSalTur);
						
		listarDepartamentosIST = new JComboBox<String>();
		listarDepartamentosIST.setBounds(245, 86, 310, 22);
		pInscSalTur.add(listarDepartamentosIST);
		
		listarActividadesIST = new JComboBox<String>();
		listarActividadesIST.setBounds(245, 126, 310, 22);
		pInscSalTur.add(listarActividadesIST);
		
		listarSalidasIST = new JComboBox<String>();
		listarSalidasIST.setBounds(245, 166, 310, 22);
		pInscSalTur.add(listarSalidasIST);
		
		listarTuristasIST = new JComboBox<String>();
		listarTuristasIST.setBounds(245, 206, 310, 22);
		pInscSalTur.add(listarTuristasIST);
		
		JLabel lblCantTur = new JLabel("Cantidad de turistas:");
		lblCantTur.setForeground(new Color(255, 255, 255));
		lblCantTur.setBounds(245, 246, 150, 20);
		pInscSalTur.add(lblCantTur);
		tFCantTur = new JTextField("");
        tFCantTur.setForeground(Color.BLACK);
		tFCantTur.setColumns(10);
		tFCantTur.setBounds(405, 246, 150, 20);
		pInscSalTur.add(tFCantTur);
		
		JLabel lblFechaInsc = new JLabel("Fecha de Inscripcion:");
		lblFechaInsc.setForeground(new Color(255, 255, 255));
		lblFechaInsc.setBounds(245, 286, 150, 20);
		pInscSalTur.add(lblFechaInsc);
		tFFechaInsc = new JTextField("dd-MM-yyyy");
        tFFechaInsc.setForeground(Color.GRAY);
		tFFechaInsc.setColumns(10);
		tFFechaInsc.setBounds(405, 286, 150, 20);
		tFFechaInsc.setText(String.valueOf(LocalDate.now()));
		tFFechaInsc.setEditable(false);
		pInscSalTur.add(tFFechaInsc);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(300, 480, 90, 25);
		pInscSalTur.add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaInscripcion();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 480, 90, 25);
		pInscSalTur.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposInscripcion();
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		listarDepartamentosIST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarDepartamentosIST.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarDepartamentosIST.getSelectedIndex() == 0) {
                	listarActividadesIST.removeAllItems();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				List<String> lista = new ArrayList<String>();
				try {
					lista = sis.seleccionarDepartamentoConf(selectedItem); //Aca
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
				listarActividadesIST.removeAllItems();
				listarActividadesIST.addItem("Actividades");
				for (String elem : lista) 
					listarActividadesIST.addItem(elem);
            }
        });
		
		listarActividadesIST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) listarActividadesIST.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarActividadesIST.getSelectedIndex() == 0) {
                	listarSalidasIST.removeAllItems();
                	return;
                }
				ISistema sis = Fabrica.getSistema();
				List<String> lista = new ArrayList<String>();
				try {
					lista = sis.salidasVigentesDeAct(selectedItem);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
				listarSalidasIST.removeAllItems();
				listarSalidasIST.addItem("Salidas");
				for (String elem : lista) 
					listarSalidasIST.addItem(elem);
            }
        });
		
		return pInscSalTur;
	}
	
	private void confirmarActividad(String nombreAct) {		
		ISistema sis = Fabrica.getSistema();
		try {
			sis.acepRecActividad(nombreAct, "CONFIRMADA");
			JOptionPane.showMessageDialog(null,  "Actividad confirmada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
//			limpiarCamposInscripcion();
			listarActividadesAg.removeAllItems();
			cargarListaActAgregadas(listarActividadesAg);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "No se pudo aceptar la actividad", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void rechazarActividad(String nombreAct) {		
		ISistema sis = Fabrica.getSistema();
		try {
			sis.acepRecActividad(nombreAct, "RECHAZADA");
			JOptionPane.showMessageDialog(null,  "Actividad rechazada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
			listarActividadesAg.removeAllItems();
			cargarListaActAgregadas(listarActividadesAg);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "No se pudo rechazar la actividad", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JPanel iniciarPanelAceptarRechazarAct(JPanel sistema) {
		JPanel pAceptarRechazarAct = new JPanel();
		pAceptarRechazarAct.setBounds(0, 0, 784, 539);
		pAceptarRechazarAct.setBackground(new Color(18, 18, 18));
		pAceptarRechazarAct.setLayout(null);
		
		JLabel lblTituloAcepRecAct = new JLabel("Aceptar/Rechazar Actividad Turistica");
		lblTituloAcepRecAct.setBounds(10, 10, 764, 64);
		lblTituloAcepRecAct.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloAcepRecAct.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAcepRecAct.setForeground(Color.white);
		pAceptarRechazarAct.add(lblTituloAcepRecAct);
		
		listarActividadesAg = new JComboBox<String>();
		listarActividadesAg.setBounds(245, 86, 310, 22);
		pAceptarRechazarAct.add(listarActividadesAg);
	
		JButton btnConfirmar = new JButton("Aceptar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirmar.setBounds(300, 400, 90, 25);
		pAceptarRechazarAct.add(btnConfirmar);
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) listarActividadesAg.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarActividadesAg.getSelectedIndex() == 0) {
                	return;
                }
				confirmarActividad(selectedItem);
			}
		});
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRechazar.setBounds(410, 400, 90, 25);
		pAceptarRechazarAct.add(btnRechazar);
		
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) listarActividadesAg.getSelectedItem();
                if(selectedItem == "" || selectedItem == null || listarActividadesAg.getSelectedIndex() == 0) {
                	return;
                }
				rechazarActividad(selectedItem);
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 440, 90, 25);
		pAceptarRechazarAct.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				limpiarCamposInscripcion();
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		return pAceptarRechazarAct;
	}

	private void altaCategoria() {
		String nuevoNom = tFNombreCategoria.getText();
		if (testearLargo(nuevoNom))
			return;
		if (nuevoNom.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
       		ISistema sis = Fabrica.getSistema();
		try {
			sis.altaCategoria(nuevoNom);
			JOptionPane.showMessageDialog(null,  "Categoria ingresada correctamente", "Alta completada", JOptionPane.INFORMATION_MESSAGE);
			tFNomActividad.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en el alta", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private JPanel iniciarPanelCategoria(JPanel sistema) {
		JPanel pCategoria = new JPanel();
		pCategoria.setBounds(0, 0, 784, 539);
		pCategoria.setLayout(null);
		pCategoria.setBackground(new Color(18, 18, 18));
		
		JLabel lblTituloCategoria = new JLabel("Agregar Categoria");
		lblTituloCategoria.setBounds(10, 10, 764, 64);
		lblTituloCategoria.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloCategoria.setForeground(Color.white);
		pCategoria.add(lblTituloCategoria);
		
		JLabel lblNombreCategoria= new JLabel("Nombre:");
		lblNombreCategoria.setForeground(Color.WHITE);
		lblNombreCategoria.setBounds(245, 140, 150, 20);
		pCategoria.add(lblNombreCategoria);
		tFNombreCategoria= new JTextField();
		tFNombreCategoria.setColumns(10);
		tFNombreCategoria.setBounds(405, 140, 150, 20);
		pCategoria.add(tFNombreCategoria);
				
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(300, 480, 90, 25);
		pCategoria.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(410, 480, 90, 25);
		pCategoria.add(btnCancelar);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaCategoria();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(sistema);
			}
		});
		
		return pCategoria;
	}
		
		
	private void limpiarCamposActividad() {
		tFNomActividad.setText("");
		tFDuracionActividad.setText("");
		tFCostoActividad.setText("");
		tFCiudadActividad.setText("");
		tADescActividad.setText("");
	}
	
	private void limpiarCamposCActividad() {
		tFNomActividad.setText("");
		tFDuracionActividad.setText("");
		tFCostoActividad.setText("");
		tFCiudadActividad.setText("");
		tADescActividad.setText("");
	}
	
	private void limpiarCamposInscripcion() {
		tFCantTur.setText("");
	}

	private void limpiarCamposSalida() {
		tFCantMaxSalida.setText("");
		tFLugarSalida.setText("");
		tFHoraSalida.setText("");
		tFFechaSalida.setText("dd-MM-yyyy");
		tFFechaSalida.setForeground(Color.GRAY);
		tFHoraSalida.setText("hh:mm");
		tFHoraSalida.setForeground(Color.GRAY);
		tFNameSalida.setText("");
	}

	private void limpiarCamposSalidaCS() {
		tFCantMaxSalidaCS.setText("");
		tFLugarSalidaCS.setText("");
		tFHoraSalidaCS.setText("");
		tFFechaSalidaCS.setText("");
		tFHoraSalidaCS.setText("");
		tFNameSalidaCS.setText("");
	}

	public void cargarListaModEmpleados() {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarUsuarios();		
		listarUsuarios.removeAllItems();
		listarUsuarios.addItem("Usuarios");
		for (String elem : lista) 
			listarUsuarios.addItem(elem);
	}
	
	public void cargarListaPaquetes(JComboBox<String> combo) {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarPaquetesExistentes();		
		combo.removeAllItems();
		combo.addItem("Paquetes");
		for (String elem : lista) 
			combo.addItem(elem);
	}
	
	public void cargarUsuariosCU(JComboBox<String> combo) {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarUsuarios();
		combo.removeAllItems();
		combo.addItem("Usuarios");
		for (String elem : lista) 
			combo.addItem(elem);
	}
	
	public void cargarTuristas(JComboBox<String> combo) {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarTuris();
		combo.removeAllItems();
		combo.addItem("Turistas");
		for (String elem : lista) 
			combo.addItem(elem);
	}
	
	public void cargarListaDepartamentos(JComboBox<String> combo) {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarDepartamentos();		
		combo.removeAllItems();
		combo.addItem("Departamentos");
		for (String elem : lista) 
			combo.addItem(elem);
	}
	
	public void cargarListaCategorias() {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarCategorias();		
		listarCategoriasA.removeAllItems();
		listarCategoriasA.addItem("Categorias");
		for (String elem : lista) 
			listarCategoriasA.addItem(elem);
	}

	public void cargarListaActAgregadas(JComboBox<String> combo) {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarActAgregadas();		
		combo.removeAllItems();
		combo.addItem("Actividades");
		for (String elem : lista) 
			combo.addItem(elem);
	}
	
	public void cargarListaProveedores() {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarProv();		
		listarProveedores.removeAllItems();
		listarProveedores.addItem("Proveedores");
		for (String elem : lista) 
			listarProveedores.addItem(elem);
	}
	
	public void cargarListaActividadesSalida() {
		ISistema sis = Fabrica.getSistema();
		List<String> lista = new ArrayList<String>();
		try {
			lista = sis.seleccionarDepartamento(listarUsuarios.getSelectedItem().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		listarDepartamentosS.removeAllItems();
		listarDepartamentosS.addItem("Departamentos");
		for (String elem : lista) 
			listarDepartamentosS.addItem(elem);
	}
	
	private void cargarListaAgregarEmpleados() {
		listarUsuarios.removeAllItems();
		listarUsuarios.addItem("Tipos de Usuarios");
		listarUsuarios.addItem("Proveedor");
		listarUsuarios.addItem("Turista");
	}

	private void limpiarCategoria() {
		listarCategoriasSelectA.removeAllItems();
		cargarListaCategorias();
	}
	
	public Pantalla() {
		setTitle("Taller de Programacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(560, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		JMenu mnActividad = new JMenu("Actividades");
		menuBar.add(mnActividad);
		JMenuItem mntmActividad = new JMenuItem("Agregar Actividad");
		mnActividad.add(mntmActividad);
		JMenuItem mntmConsultaAct = new JMenuItem("Consulta Actividad");
		mnActividad.add(mntmConsultaAct);
		JMenu mnSalida = new JMenu("Salidas");
		menuBar.add(mnSalida);
		JMenuItem mntmSalida = new JMenuItem("Agregar Salida");
		mnSalida.add(mntmSalida);
		JMenuItem mntmConsSalida = new JMenuItem("Consultar Salida");
		mnSalida.add(mntmConsSalida);
		JMenuItem mntmInscST = new JMenuItem("Inscripción a Salida");
		mnSalida.add(mntmInscST);
		JMenuItem mntmSistema = new JMenuItem("Sistema");
		mnAyuda.add(mntmSistema);
		JMenuItem mntmAjustes = new JMenuItem("Ajustes");
		mnAyuda.add(mntmAjustes);
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnAyuda.add(mntmSalir);
		JMenu mnEmpleados = new JMenu("Usuarios");
		menuBar.add(mnEmpleados);
		JMenuItem mntmAgregarEmpleado = new JMenuItem("Agregar Usuario");
		mnEmpleados.add(mntmAgregarEmpleado);
		JMenuItem mntmModificarEmpleado = new JMenuItem("Modificar Usuario");
		mnEmpleados.add(mntmModificarEmpleado);
		JMenuItem mntmConsultaUsuario = new JMenuItem("Consulta Usuario");
		mnEmpleados.add(mntmConsultaUsuario);
		JMenu mnPaquetes = new JMenu("Paquetes");
		menuBar.add(mnPaquetes);
		JMenuItem mntmAgregarPaquete = new JMenuItem("Agregar Paquete");
		mnPaquetes.add(mntmAgregarPaquete);
		JMenuItem mntmConsultarPaquete = new JMenuItem("Consultar Paquete");
		mnPaquetes.add(mntmConsultarPaquete);
		JMenuItem mntmAgregarActAPaquete = new JMenuItem("Agregar Actividad a Paquete");
		mnPaquetes.add(mntmAgregarActAPaquete);		
		JMenuItem mntmAceptarRechazarAct = new JMenuItem("Aceptar/Rechazar Actividad");
		mnActividad.add(mntmAceptarRechazarAct);
		JMenu mnCategorias = new JMenu("Categorias");
		menuBar.add(mnCategorias);
		JMenuItem mntmAgregarCategoria = new JMenuItem("Agregar Categoria");
		mnCategorias.add(mntmAgregarCategoria);
		

		JPanel pSistema = iniciarPanelSistema();
		JPanel pAjustes = iniciarPanelAjustes();
		JPanel pEmpleado = iniciarPanelEmpleado(pSistema);
		JPanel pPaquete = iniciarPanelPaquete(pSistema);
		JPanel pSalida = iniciarPanelSalida(pSistema);
		JPanel pActividad = iniciarPanelActividad(pSistema);
		JPanel pConsSalida = iniciarPanelConsSalida(pSistema);
		JPanel pConsPaquete = iniciarPanelConsPaquete(pSistema);
		JPanel pConsActividad = iniciarPanelConsActividad(pSistema, pConsPaquete, pConsSalida);
		JPanel pAgregarActAPaquete = iniciarPanelAgregarActAPaquete(pSistema);
		JPanel pConsUsuario = iniciarPanelConsUsuario(pSistema,pConsActividad,pConsSalida);
		JPanel pInscSalTur = iniciarPanelInscSalTur(pSistema);
		JPanel pCategoria = iniciarPanelCategoria(pSistema);
		JPanel pAceptarRechazarAct = iniciarPanelAceptarRechazarAct(pSistema);

		
		pCategoria.setVisible(false);
		pConsPaquete.setVisible(false);
		pSistema.setVisible(false);
		pAjustes.setVisible(false);
		pEmpleado.setVisible(false);
		pPaquete.setVisible(false);
		pSalida.setVisible(false);
		pActividad.setVisible(false);
		pConsSalida.setVisible(false);
		pAgregarActAPaquete.setVisible(false);
		pConsActividad.setVisible(false);
		pConsUsuario.setVisible(false);
		pInscSalTur.setVisible(false);
		
		pAceptarRechazarAct.setVisible(false);		

		getContentPane().add(pConsUsuario);
		getContentPane().add(pInscSalTur);
		getContentPane().add(pCategoria);
		getContentPane().add(pEmpleado);
		getContentPane().add(pConsPaquete);
		getContentPane().add(pConsActividad);
		getContentPane().add(pConsSalida);
		getContentPane().add(pSistema);
		getContentPane().add(pActividad);
		getContentPane().add(pSalida);
		getContentPane().add(pPaquete);
		getContentPane().add(pAgregarActAPaquete);
		getContentPane().add(pAjustes);
		
		getContentPane().add(pAceptarRechazarAct);
		
		
		
		cambiarVentanaPrincipal(pSistema);
		
		listarActividadesCP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listarActividadesCP.getSelectedIndex() == 0 || listarActividadesCP.getSelectedItem() == null) {
					return;
				}
				String nombreActividad = listarActividadesCP.getSelectedItem().toString();
				mostrarInfoActividad(nombreActividad);
				cargarListaDepartamentos(listarDepartamentosCA);
				cambiarVentanaPrincipal(pConsActividad);
			}
		});
		mntmSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(pSistema);
			}
		});
		mntmConsultaAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaDepartamentos(listarDepartamentosCA);
				limpiarCamposActividad();
				cambiarVentanaPrincipal(pConsActividad);				
			}
		});
		mntmAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(pAjustes);
			}
		});
		mntmModificarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoUsuarioActual = TIPOS_USUARIOS.NONE;
				vistaUsuarioActual = TIPOS_VISTA_USUARIOS.MODIFICAR;
				actualizarPanelUsuario();
				cambiarVentanaPrincipal(pEmpleado);
			}
		});
		mntmAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoUsuarioActual = TIPOS_USUARIOS.NONE;
				vistaUsuarioActual = TIPOS_VISTA_USUARIOS.AGREGAR;
				actualizarPanelUsuario();
				cambiarVentanaPrincipal(pEmpleado);
			}
		});
		mntmAgregarPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(pPaquete);
			}
		});
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaDepartamentos(listarDepartamentosS);
				limpiarCamposSalida();
				cambiarVentanaPrincipal(pSalida);
			}
		});
		mntmConsSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaDepartamentos(listarDepartamentosCS);
				limpiarCamposSalidaCS();
				cambiarVentanaPrincipal(pConsSalida);
			}
		});
		mntmActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaDepartamentos(listarDepartamentosA);
				cargarListaProveedores();
				cargarListaCategorias();
				limpiarCamposActividad();
				limpiarCategoria();
				cambiarVentanaPrincipal(pActividad);
			}
		});
		mntmConsultarPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaPaquetes(listarPaquetes);
				listarCategoriasCP.removeAllItems();
				cambiarVentanaPrincipal(pConsPaquete);
			}
		});
		mntmAgregarActAPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaPaquetes(listarPaquetesAAP);
				cargarListaDepartamentos(listarDepartamentosAAP);
				cambiarVentanaPrincipal(pAgregarActAPaquete);
			}
		});
		mntmConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarUsuariosCU(listarUsuariosCU);
				actualizarPanelUsuario();
				cambiarVentanaPrincipal(pConsUsuario);
			}
		});
		mntmInscST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaDepartamentos(listarDepartamentosIST);
				cargarTuristas(listarTuristasIST);
				cambiarVentanaPrincipal(pInscSalTur);
			}
		});
		mntmAceptarRechazarAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListaActAgregadas(listarActividadesAg);
				cambiarVentanaPrincipal(pAceptarRechazarAct);
			}
		});
			
		mntmAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentanaPrincipal(pCategoria);
			}
		});
	}
}
