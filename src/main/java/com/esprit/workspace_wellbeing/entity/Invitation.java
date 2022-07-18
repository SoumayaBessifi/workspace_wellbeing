 
package com.esprit.workspace_wellbeing.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Invitation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_invitation ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "invitation_etats", joinColumns = @JoinColumn(name = "id_invitation"), inverseJoinColumns = @JoinColumn(name = "etat_id"))
    private Set<Etat> validation = new HashSet<>();


    private String favoris ;

    private String participation ;


    @ManyToOne
    @JoinColumn(name = "receiver_user")
    private User receiver_user ; 
  
    @ManyToOne
    @JoinColumn(name = "sender_user")
    private User sender_user ; 

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event ; 

    
    public Invitation(String favoris, String participation) {
        this.favoris = favoris;
        this.participation = participation;
    }

}
