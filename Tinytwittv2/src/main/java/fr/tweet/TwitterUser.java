package fr.tweet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;


@Entity
public class TwitterUser implements Serializable{
	
/* Attributs */
	

/**
	 * 
	 */


private @Id String pseudo;

private ArrayList<String> followers;

private ArrayList<String> tweets;

/*Constructeur pour Objectify*/

public TwitterUser(){}

/* Constructeur */
public TwitterUser(String nom){
	
	this.pseudo = nom;
	this.followers = new ArrayList<String>(); 
	this.tweets = new ArrayList<String>();
}


public String getPseudo(){
	return this.pseudo;
}

public ArrayList<String> getFollowers(){
	return this.followers;
}

public void setFollowers(ArrayList<String> init) {
	
	this.followers = init;
	
}

public ArrayList<String> getTweets(){
	return this.tweets;
}

public void setTimeline(ArrayList<String> init) {
	this.tweets = init;
}

public void addFollower(String cible){
	
	this.followers.add(cible);
	
}

public void tweeter(String letweet){
	if (this.tweets == null) {
		this.setTimeline(new ArrayList<String>());
	}
	if(letweet != null) {
	this.tweets.add(0, letweet);
	} else {
		
		System.out.println("On a échoué, chef !");
		
	}
}


}