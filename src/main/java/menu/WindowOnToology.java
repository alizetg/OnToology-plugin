package menu;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.TransportException;


import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;





public class WindowOnToology extends JFrame implements ActionListener {


	private JPanel contentPanel;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblLocalPath;
	private JLabel lblRemotePath;
	private JButton btnSend;
	private JButton btnCancel;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JTextField txtLocalPath;
	private JTextField txtRemotePath;
    UserOnToology user= new UserOnToology();
	ConnectGitHub connection = new ConnectGitHub();
	private boolean exit;

	/**
	 * Create the frame.
	 */
	public WindowOnToology() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setTitle("OnToology");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		txtUser = new JTextField("",30);
		txtUser.setBounds(179, 22, 203, 39);
		txtUser.setColumns(10);
		txtUser.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1,true));
		txtUser.requestFocus();
		txtUser.setCaretColor(Color.BLUE);
		contentPanel.add(txtUser);
		
		txtPassword = new JPasswordField("",30);
		txtPassword.setBounds(179, 73, 203, 39);
		txtPassword.setColumns(10);
		txtPassword.setEchoChar('\u2022');
		contentPanel.add(txtPassword);
		
		txtLocalPath = new JTextField("",30);
		txtLocalPath.setBounds(179, 125, 203, 39);
		txtLocalPath.setColumns(10);
		txtLocalPath.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1,true));
		contentPanel.add(txtLocalPath);
		
		txtRemotePath = new JTextField("",30);
		txtRemotePath.setBounds(179, 176, 203, 39);
		txtRemotePath.setColumns(10);
		txtRemotePath.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1,true));
		contentPanel.add(txtRemotePath);
		
		lblUser = new JLabel("User");
		lblUser.setBounds(51, 33, 61, 16);
		contentPanel.add(lblUser);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(51, 83, 61, 16);
		contentPanel.add(lblPassword);
		
		lblLocalPath = new JLabel("Local path");
		lblLocalPath.setBounds(51, 136, 81, 16);
		contentPanel.add(lblLocalPath);
		
		lblRemotePath = new JLabel("Remote Path");
		lblRemotePath.setBounds(51, 187, 99, 16);
		contentPanel.add(lblRemotePath);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(87, 227, 117, 29);
		btnSend.addActionListener(this);
		btnSend.setBackground(SystemColor.activeCaptionBorder);
		contentPanel.add(btnSend);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(228, 227, 117, 29);
		btnCancel.addActionListener(this);
		contentPanel.add(btnCancel);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnSend) {
				try {
					actionPerformedBtnSend(e);
				} catch (JGitInternalException | IOException | GitAPIException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
	

	protected UserOnToology actionPerformedBtnSend(ActionEvent e) throws JGitInternalException, InvalidRemoteException, TransportException, IOException, GitAPIException {
	  
		user= new UserOnToology(txtUser.getText(),txtPassword.getPassword(), txtLocalPath.getText(),txtRemotePath.getText());
		if(user.getUser().isEmpty() || user.getPassword().length==0 || user.getLocalPath().isEmpty() || user.getRemotePath().isEmpty()){
			JOptionPane.showMessageDialog(null, "No ha introducido todos los datos, vuelva a intentarlo ", "Alerta", JOptionPane.WARNING_MESSAGE);
			actionPerformedBtnCancel(e);
		}	
		
	    connection.connecRepo(user); 
	    exit=connection.cloneRepo(user);
		System.out.println("esta es exit" + exit);
		if(!exit){
			actionPerformedBtnCancel(e);
		}else{
		    try {
				connection.addToRepo(user);
			} catch (IOException | GitAPIException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "No ha introducido los datos del repositorio correctamente, vuelva a intentarlo ", "Alerta", JOptionPane.WARNING_MESSAGE);
	
			}
		    try {
				connection.commitToRepo(user,"modificacion-Rpo");
			} catch (JGitInternalException | IOException | GitAPIException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No ha introducido los datos de acceso correctamente, vuelva a intentarlo ", "Alerta", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
	    
		    
		    try {
				connection.pushToRepo(user);
			} catch (JGitInternalException | IOException | GitAPIException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No ha introducido los datos de acceso correctamente, vuelva a intentarlo ", "Alerta", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
	   	
		    
		     
		}    
		   return user;
	}
	
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		txtUser.setText("");
		txtPassword.setText("");
		txtLocalPath.setText("");
		txtRemotePath.setText("");
		setVisible(false);
	}
}
