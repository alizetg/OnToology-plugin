package menu;


import java.io.File;

import java.io.IOException;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





import org.protege.editor.owl.ui.action.ProtegeOWLAction;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.DetachedHeadException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;



public class OnToology extends ProtegeOWLAction implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	WindowOnToology view= new WindowOnToology();
	UserOnToology user = new UserOnToology();
	ConnectGitHub connection = new ConnectGitHub();

	public void actionPerformed(ActionEvent event) {
		
		//StringBuilder message = new StringBuilder("This example OnToooooology item iiiis under the Tools menu.\n");
		view.setVisible(true);             // hacemos visible la ventana creada
		if (event.getActionCommand() == "btnCancel") {
			view.actionPerformedBtnCancel(event);
		}
		 System.out.println("estoy aqui en OnToology");
		if (event.getActionCommand()== "btnSend") {
		    try {
				user=view.actionPerformedBtnSend(event);
			} catch (JGitInternalException | IOException | GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    /*System.out.println("estoy aqui en OnToology"+ ""+ user.getUser());
		    connection.connecRepo(user); 
		    try {
				connection.cloneRepo(user);
			} catch (IOException | GitAPIException e2) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "El directorio en el que se desea clonar el repositorio ya existe y no esta vacio, intentelo de nuevo ", "Alerta", JOptionPane.WARNING_MESSAGE);
				e2.printStackTrace();
			}
		    
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
			} catch (JGitInternalException | IOException | GitAPIException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No ha introducido los datos de acceso correctamente, vuelva a intentarlo ", "Alerta", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		    
		    
		    
		    
				*/
		}	
	}



	@Override
	public void initialise() throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub
		
	}



	
}
