package com.example.social.friendship;

import com.example.social.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendship")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "friend1",
            referencedColumnName = "id"
    )
    UserEntity friend1;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "friend2",
            referencedColumnName = "id"
    )
    UserEntity  friend2;
}
