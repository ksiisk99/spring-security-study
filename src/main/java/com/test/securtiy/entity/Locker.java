package com.test.securtiy.entity;

import javax.persistence.*;

@Entity
public class Locker{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id")
    Long Id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
