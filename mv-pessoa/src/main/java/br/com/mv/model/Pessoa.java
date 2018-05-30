package br.com.mv.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "pessoa")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome", nullable = false)
	@NotBlank
	private String nome;

	@Column(name = "cpf", unique = true, length = 11, nullable = false)
	@NotBlank
	private String cpf;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;

	@Column(name = "email")
	private String email;

	@JsonManagedReference(value = "pessoa-telefone")
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, fetch = FetchType.LAZY)
	@Cascade({
        CascadeType.ALL
    })
	private List<Telefone> telefones;
	
	public void setTelefones(List<Telefone> telefones) {
		if(this.telefones == null) {
			this.telefones = telefones;
		} else {
			this.telefones.clear();
			this.telefones.addAll(telefones);
		}
	}
}
