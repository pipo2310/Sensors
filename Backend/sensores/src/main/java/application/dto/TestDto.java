package application.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test" , schema = "public")
public class TestDto {

    @Id
    @Column(name = "test_pk")
    private Integer testPK;
    @Column(name = "description")
    private String description;

    public Integer getTestPK() {
        return testPK;
    }

    public void setTestPK(Integer testPK) {
        this.testPK = testPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
