package br.com.avaliacao.cadastro.entity.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import br.com.avaliacao.cadastro.entity.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PessoaEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -1810034244669257498L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_generator")
	@SequenceGenerator(name="pessoa_generator", sequenceName = "pessoa_seq", allocationSize=1)
	@Column(updatable = false, nullable = false)
	private Integer id;

	public Integer getId() {
		return id;
	}
	
}
