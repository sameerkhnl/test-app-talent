package org.khanal.testapptalent.domains;

import javax.persistence.*;

@Entity
@Table(name = "domains")
public class Domain {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "domain_name")
    private String domainName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
