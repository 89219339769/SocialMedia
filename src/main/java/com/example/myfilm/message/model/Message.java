package com.example.myfilm.message.model;

import com.example.myfilm.user.UserEntity;
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
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "sender",
            referencedColumnName = "id"
    )
    UserEntity sender;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "recipient",
            referencedColumnName = "id"
    )
    UserEntity  recipient;

    @Column(length = 1000)
    private String text;
}
