package br.com.siscon.model.usuario;

import br.com.siscon.model.venda.Venda;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="usuario")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Identificador do usuário")
    private Long id;

    @Column(unique = true)
    @NotNull
    @ApiModelProperty(notes = "Username do usuário")
    private String usuario;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty(notes = "Matrícula do usuário")
    private String matricula;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(notes = "Senha do usuário")
    private String senha;

    @ApiModelProperty(notes = "Data de cadastro do usuário")
    private Date data;

    @ApiModelProperty(notes = "Data de atualização do cadastro do usuário")
    private Date data_atualizacao;

    @ElementCollection(targetClass = Role.class, fetch=FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "role", nullable=false)
    private Set<Role> role = new HashSet<>();

    @OneToMany(mappedBy = "usuario_id")
    @ApiModelProperty(notes = "Vendas realizadas pelo usuário")
    private Set<Venda> vendas;

    public Usuario(String usuario, String matricula, String senha, Date data) {
        this.usuario = usuario;
        this.matricula = matricula;
        this.senha = senha;
        this.data = data;
    }
}
