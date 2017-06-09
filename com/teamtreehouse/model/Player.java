package com.teamtreehouse.model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
  private static final long serialVersionUID = 1L;

  private String firstName;
  private String lastName;
  private int heightInInches;
  private boolean previousExperience;

  public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.heightInInches = heightInInches;
    this.previousExperience = previousExperience;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getHeightInInches() {
    return heightInInches;
  }

  public boolean isPreviousExperience() {
    return previousExperience;
  }

  @Override
  public int compareTo(Player other) {
    if(equals(other)){
    return 0;
    }
    int lastNameComp = lastName.compareTo(other.getLastName());
    if (lastNameComp==0){
      return firstName.compareTo(other.getFirstName());
    }
      return lastName.compareTo(other.getLastName());
   // We always want to sort by last name then first name    
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Player)) return false;

    Player player = (Player) o;

    if (heightInInches != player.heightInInches) return false;
    if (previousExperience != player.previousExperience) return false;
    if (!firstName.equals(player.firstName)) return false;
    return lastName.equals(player.lastName);

  }

  @Override
  public int hashCode() {
    int result = firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + heightInInches;
    result = 31 * result + (previousExperience ? 1 : 0);
    return result;
  }
  @Override
	public String toString(){
    String experience;
      if (previousExperience == true){
        experience = "experienced";
      }else{
        experience = "not experienced";
      }
		return String.format("%s %s, height:%d, %s ", lastName, firstName, heightInInches, experience);
  }
  
  public String getHeightRange(Player player){
      String heightRange = "";
    if (player.getHeightInInches() >=35 && player.getHeightInInches()<=40){
      heightRange = "35-40";
    }
    if (player.getHeightInInches() >=41 && player.getHeightInInches()<=46){
      heightRange = "41-46";  
    }
    if (player.getHeightInInches() >=47 && player.getHeightInInches()<=60){
      heightRange = "47-60";  
    }
    return heightRange;
  }
}
