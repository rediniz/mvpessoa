package br.com.mv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "telefone", uniqueConstraints = {@UniqueConstraint(columnNames = {"ddd", "numero", "pessoa_id"})})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ddd", nullable = false)
	private String ddd;

	@Column(name = "numero", nullable = false)
	private String numero;

	@JsonBackReference(value="pessoa-telefone")
	@ManyToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	private Pessoa pessoa;

}
