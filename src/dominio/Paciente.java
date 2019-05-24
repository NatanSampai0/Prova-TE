package dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;

	public Paciente() {
		
	}

	public Paciente(Integer id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "paciente [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
