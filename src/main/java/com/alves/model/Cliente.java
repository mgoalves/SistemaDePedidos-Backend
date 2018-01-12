package com.alves.model;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
 
import com.alves.model.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
 
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    //Atributos ----------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @NotBlank
    @NotNull
    @Column(length = 40)
    private String nome;
    
    @Email @NotNull @NotBlank
    @Column(unique = true, length = 40)
    private String email;
    
    @NotNull
    @Size(min = 11, max = 14)
    @Column(length = 14, name = "cpf_cnpj", unique = true)
    private String cpfOuCnpj;
    private Integer tipo;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos =  new ArrayList<>();
    
 
    //Constructors -------------------------------
    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo.getId();
        System.out.println("VALOR: "+this.tipo);
    }
    public Cliente() {
    }
    
    //Getters and Setters --------------------------
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }
    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }
 
    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }
    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getId();
    }
    
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public Set<String> getTelefones() {
        return telefones;
    }
    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    //HashCode and Equals: ID, Email, CPF-CNPJ -----------------------------------
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpfOuCnpj == null) ? 0 : cpfOuCnpj.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (cpfOuCnpj == null) {
            if (other.cpfOuCnpj != null)
                return false;
        } else if (!cpfOuCnpj.equals(other.cpfOuCnpj))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}