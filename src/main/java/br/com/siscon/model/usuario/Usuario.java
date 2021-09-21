package br.com.siscon.model.usuario;

import br.com.siscon.model.venda.Venda;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Long id;

    @Column(unique = true)
    @NotNull
    private String usuario;

    @NotNull
    @Column(unique = true)
    private String matricula;

    @NotNull
    private String senha;

    private Date data;

    private Date data_atualizacao;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "role", nullable=false)
    private Set<Role> role = new HashSet<>();

    @OneToMany(mappedBy = "usuario_id")
    private Set<Venda> venda_id;
}
