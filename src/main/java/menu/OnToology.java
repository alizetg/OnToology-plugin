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
	JFrame formulario = new JFrame();
	JPanel panel = new JPanel();
	
	

	public void initialise() throws Exception {
    
	}

	

	public void dispose() throws Exception {
	}
	
	
	public void actionPerformed(ActionEvent event) {
		
		//StringBuilder message = new StringBuilder("This example OnToooooology item iiiis under the Tools menu.\n");
		view.setVisible(true);             // hacemos visible la ventana creada
		if (event.getActionCommand() == "btnCancel") {
			view.actionPerformedBtnCancel(event);
		}
		if (event.getActionCommand()== "btnSend") {
			user=(view.actionPerformedBtnSend(event));
			System.out.print(user.getUser());
			System.out.print(user.getPassword());
			System.out.print(user.getLocalPath());
			System.out.print(user.getRemotePath());
		}
	
		
		/*
		message.append("The currently selected class is ");
		message.append(getOWLWorkspace().getOWLSelectionModel().getLastSelectedClass());
		message.append(".");
		JOptionPane.showMessageDialog(getOWLWorkspace(), message.toString());
		
		*
		*
		*
		*
		***
		 * Launch the application.
		 
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						WindowOnToology frame = new WindowOnToology();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
			*
			*/
			
		
		
		
	}



	
}
