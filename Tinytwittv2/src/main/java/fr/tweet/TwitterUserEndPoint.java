package fr.tweet;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.DatastoreService;

import com.googlecode.objectify.ObjectifyService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.http.*;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.annotation.*;



@Api (name = "tinytwitt")
public class TwitterUserEndPoint{
	
	static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	static SimpleDateFormat heure = new SimpleDateFormat("hh:mm");
	static {
		ObjectifyService.register(TwitterUser.class);				
	}
	
	@ApiMethod(name = "nouvelutilisateur", path = "newuser")
	public TwitterUser nouvelUtilisateur(@Named("name") String name){
			TwitterUser newuser = new TwitterUser(name);
			newuser.setFollowers(new ArrayList<String>());
			newuser.setTimeline(new ArrayList<String>());
			ofy().save().entities(newuser).now();
			return newuser;
	}
	
	@ApiMethod(name = "follow", description = "Permet de suivre l'actualité de quelqu'un", path = "follow")
	public ArrayList<String> follow(@Named("pseudo") String pseudo, @Named("followed") String followed) {
		System.out.println(pseudo + " essaie de follow" + followed);
		if(ofy().load().type(TwitterUser.class).id(followed).now() != null && ofy().load().type(TwitterUser.class).id(pseudo).now() !=null) {	
			
			if(ofy().load().type(TwitterUser.class).id(followed).now().getFollowers() == null) {
				
				ofy().load().type(TwitterUser.class).id(followed).now().setFollowers(new ArrayList<String>());
				
			}
			
			TwitterUser nouveau = ofy().load().type(TwitterUser.class).id(followed).now();
			nouveau.addFollower(pseudo);
			ofy().save().entity(nouveau);		
			
			return ofy().load().type(TwitterUser.class).id(followed).now().getFollowers();
			
		} else {
			
			System.out.println("Un ou plusieurs des utilisateurs n'existe pas.");
			return null;
		}
	
	
	}
	
	@ApiMethod(name = "users", description ="Affiche la liste des utilisateurs enregistrés sur l'application", httpMethod = HttpMethod.GET, path="liste")
	public List<TwitterUser> getUsers(){
		
	return ofy().load().type(TwitterUser.class).list();

	}
	
	@ApiMethod(name = "tweeter",description = "ajout d'un tweet", path ="tweet")
	public ArrayList<String> postTweet(@Named("pseudo")String pseudo, @Named("tweet")String tweet){
		tweet = pseudo+" le "+ format.format(new Date()) + " à "+ heure.format(new Date()) +" : "+tweet;
		if(ofy().load().type(TwitterUser.class).id(pseudo).now().getTweets() == null) {
			
		ofy().load().type(TwitterUser.class).id(pseudo).now().setTimeline(new ArrayList<String>());
		
		}
		
		TwitterUser nouveau = ofy().load().type(TwitterUser.class).id(pseudo).now();
		nouveau.tweeter(tweet);
		ofy().save().entity(nouveau);
		
		// ajout du tweet sur la timeline de chaque follower si l'utilisateur en a
		if(ofy().load().type(TwitterUser.class).id(pseudo).now().getFollowers() != null) {
			
			for(String f : ofy().load().type(TwitterUser.class).id(pseudo).now().getFollowers()) {				
				TwitterUser refresh = ofy().load().type(TwitterUser.class).id(f).now();
				refresh.tweeter(tweet);
				ofy().save().entity(refresh);
				
			}
		}
		return ofy().load().type(TwitterUser.class).id(pseudo).now().getTweets();
		
	}
	

	@ApiMethod(name = "scrolling", description = "affiche la timeline de l'utilisateur",path ="timeline")
	public ArrayList<String> scrollingTweet(@Named("pseudo")String pseudo){
		return ofy().load().type(TwitterUser.class).id(pseudo).now().getTweets();		
		
	}
	
	public void prepareTest(@Named("pseudo") String pseudo, @Named("nbfollower") int nbfollower) {
		
		for(int i = 0; i< nbfollower; i++) {
			String nom = "test"+i;
			nouvelUtilisateur(nom);
			follow(nom, pseudo);			
		}		
		
	}
	

}


