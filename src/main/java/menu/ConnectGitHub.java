package menu;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
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
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;



public class ConnectGitHub {
	

	
    //metodo que obtiene las credenciales del usuario de Git
	public void connecRepo(UserOnToology user){
		System.out.println("estoy en connect");
		user.setCp(new UsernamePasswordCredentialsProvider(user.getUser(),user.getPassword()));
    	System.out.println("pasar las credenciales de github " + "" +  user.getCp().getDefault());
	}
    
	//metodo que clona el repositorio a un directorio local
	public boolean cloneRepo(UserOnToology user) throws IOException,  JGitInternalException, InvalidRemoteException, TransportException, GitAPIException {
        boolean exit=true;
		try {
	        	System.out.println("estoy en clone" + "" +  user.getRemotePath());
	            Git.cloneRepository()
	                    .setURI(user.getRemotePath())
	                    .setDirectory(new File(user.getLocalPath()))
	                    .call();
		} catch (JGitInternalException | InvalidRemoteException  e ) {
			JOptionPane.showMessageDialog(null, "El directorio local o el directorio remoto están erroneos, recuerde que el repositorio local no debe existir o si lo está , asegurese que este vacio, intentelo de nuevo ", "Alerta", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			exit= false;
		}
		return exit;
    }
	
	//metodo que añade los cambios del repositorio local al repositorio remoto
	public void addToRepo(UserOnToology user) throws IOException, NoFilepatternException, GitAPIException {
		System.out.println("estoy en add");
        AddCommand add = user.getGit().add();
        add.addFilepattern(".").call();
    }
    
	//metodo que realiza el commit al repositorio remoto
    public void commitToRepo(UserOnToology user,String message) throws IOException, NoHeadException,
            NoMessageException, ConcurrentRefUpdateException,
            JGitInternalException, WrongRepositoryStateException, GitAPIException {
    	    System.out.println("estoy en commit");
    		user.getGit().commit().setMessage(message).call();
    }

    //metodo que realiza el push al repositorio remoto
    public void pushToRepo(UserOnToology user) throws IOException, JGitInternalException,
            InvalidRemoteException, GitAPIException {
    	System.out.println("estoy en push");
        PushCommand pc = user.getGit().push();
        pc.setCredentialsProvider(user.getCp())
                .setForce(true)
                .setPushAll();
        try {
            Iterator<PushResult> it = pc.call().iterator();
            if (it.hasNext()) {
                System.out.println(it.next().toString());
            }
        } catch (InvalidRemoteException e) {
            e.printStackTrace();
        }
    }

    //metodo que realiza un pull request al repositorio remoto
    public void pullFromRepo(UserOnToology user) throws IOException, WrongRepositoryStateException,
            InvalidConfigurationException, DetachedHeadException,
            InvalidRemoteException, CanceledException, RefNotFoundException,
            NoHeadException, GitAPIException {
    	    System.out.println("estoy en pull");
            user.getGit().pull().call();
    }
    
    

    public void postCredentialsOnToology(UserOnToology user){
    	@SuppressWarnings("deprecation")
    	String userCredentials=user.getUser() +"&"+ user.getPassword();
    	System.out.println("usuario pasado a OnToology"+ userCredentials);
    	
    	
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
    	WebTarget webTarget = client.target("http://ontoology.linkeddata.es/api?").path("login=");
   
    	 
    	Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
    	Response response = invocationBuilder.post(Entity.entity(user.getUser(), MediaType.APPLICATION_XML));
    	 
    	System.out.println(response.getStatus());
    	System.out.println(response.readEntity(String.class));
    	 
    	
    	
    	
    	
    }

}
