package io.github.jetqin.upscale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.eclipse.persistence.annotations.Multitenant;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Person")
@AllArgsConstructor
@NoArgsConstructor
@Multitenant
@ToString
@ApiModel(description = "Class representing a person tracked by the application.")
public class Person {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique identifier of the person. No two persons can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @Column(name="NAME", nullable = false)
    @ApiModelProperty(notes = "Person name.", example = "Jet", required = true, position = 1)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "person")
    private List<Address> addresses;

}
