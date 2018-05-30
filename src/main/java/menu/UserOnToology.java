package menu;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;



public class UserOnToology {
	
	private String localPath, remotePath;
    private Repository localRepo;
    private Git git;
    //private CredentialsProvider cp;
    private String user;
    private char [] password;

    
    public UserOnToology(){}
    
    public UserOnToology(String user, String password, String localPath, String remotePath)  {
        this.localPath = localPath;
        this.remotePath = remotePath;
        try {
			this.localRepo = new FileRepository(localPath + "/.git");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        git = new Git(localRepo);
    }

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public Repository getLocalRepo() {
		return localRepo;
	}

	public void setLocalRepo(Repository localRepo) {
		this.localRepo = localRepo;
	}

	public Git getGit() {
		return git;
	}

	public void setGit(Git git) {
		this.git = git;
	}
	
	/*

	public CredentialsProvider getCp() {
		return cp;
	}

	public void setCp(CredentialsProvider cp) {
		this.cp = cp;
	}
	
	*/

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] cs) {
		this.password = cs;
	}

   
	
	

}
