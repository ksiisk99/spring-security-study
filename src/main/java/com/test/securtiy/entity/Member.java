package com.test.securtiy.entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "member_id")
    Long Id;


}
