package org.khanal.testapptalent.domains;

import javax.persistence.*;

@Entity
@Table(name = "domains")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "domain_name")
    private String domainName;
}
